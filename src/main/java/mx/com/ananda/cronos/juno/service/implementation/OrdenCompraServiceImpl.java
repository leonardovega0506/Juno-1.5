package mx.com.ananda.cronos.juno.service.implementation;


import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.exception.ResourceNotFoundException;
import mx.com.ananda.cronos.juno.model.dto.http.DetalleCompraDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;
import mx.com.ananda.cronos.juno.model.dto.sap.OrdenCompraSAP;
import mx.com.ananda.cronos.juno.model.entity.DetalleCompraModel;
import mx.com.ananda.cronos.juno.model.entity.ItemFotoModel;
import mx.com.ananda.cronos.juno.model.entity.ItemModel;
import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;
import mx.com.ananda.cronos.juno.repository.IDetalleCompraRepository;
import mx.com.ananda.cronos.juno.repository.IFotoItemRepository;
import mx.com.ananda.cronos.juno.repository.IOrdenCompraRepository;
import mx.com.ananda.cronos.juno.repository.IRepositoryItem;
import mx.com.ananda.cronos.juno.response.ItemResponse;
import mx.com.ananda.cronos.juno.response.OrdenCompraResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IOrdenCompraService;
import mx.com.ananda.cronos.juno.service.interfaces.IServiceItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrdenCompraServiceImpl implements IOrdenCompraService {

    @Autowired
    private IOrdenCompraRepository iOrden;

    @Autowired
    private IRepositoryItem iItem;

    @Autowired
    private IDetalleCompraRepository iDetalle;

    @Autowired
    private IFotoItemRepository iFoto;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    //Valor asignado desde el properties para la url a consumor
    @Value("${spring.external.service.base-url}")
    private String basePath;

    @Autowired
    private IServiceItem sItem;


    //Obtener todas las ordenes
    @Override
    public OrdenCompraResponse getAllOrders(int numPage, int sizePage, String orderBy, String sortDir) {
        //Generamos la paginacion
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numPage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA ORGANZAZCION, {}", orderBy);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA FORMA, {}", sortDir);
        //Buscamos y mapeamos a nuestro DTO
        Page<OrdenCompraModel> orders = iOrden.findAll(pageable);
        List<OrdenCompraModel> listaOrdenes = orders.getContent();
        List<OrdenCompraDTO> contenido = listaOrdenes.stream().map(ordenCompraModel -> mapearEndidadDTO(ordenCompraModel)).collect(Collectors.toList());
        log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
        //Agregamos el contenido a nuestro response
        OrdenCompraResponse ordenCompraResponse = new OrdenCompraResponse();
        ordenCompraResponse.setContent(contenido);
        ordenCompraResponse.setNumPage(orders.getNumber());
        ordenCompraResponse.setSizePage(orders.getSize());
        ordenCompraResponse.setAllElements(orders.getTotalElements());
        ordenCompraResponse.setAllPage(orders.getTotalPages());
        ordenCompraResponse.setLast(ordenCompraResponse.isLast());
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}", ordenCompraResponse);
        return ordenCompraResponse;
    }

    @Override
    public OrdenCompraResponse getAllOrdersByCardName(int numPage, int sizePage, String cardName) {
        //Generamos la paginacion
        Pageable pageable = PageRequest.of(numPage, sizePage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numPage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePage);
        //Buscamos y mapeamos a nuestro DTO
        Page<OrdenCompraModel> orders = iOrden.findAll(pageable);
        List<OrdenCompraModel> listaOrdenes = orders.getContent();
        List<OrdenCompraDTO> contenido = listaOrdenes.stream().filter(ordenCompra -> ordenCompra.getCardName().equals(cardName)).map(ordenCompra -> mapearEndidadDTO(ordenCompra)).collect(Collectors.toList());

        log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
        //Agregamos el contenido a nuestro response
        OrdenCompraResponse ordenCompraResponse = new OrdenCompraResponse();
        ordenCompraResponse.setContent(contenido);
        ordenCompraResponse.setNumPage(orders.getNumber());
        ordenCompraResponse.setSizePage(orders.getSize());
        ordenCompraResponse.setAllElements(orders.getTotalElements());
        ordenCompraResponse.setAllPage(orders.getTotalPages());
        ordenCompraResponse.setLast(ordenCompraResponse.isLast());
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}", ordenCompraResponse);
        return ordenCompraResponse;
    }

    @Override
    public OrdenCompraResponse getAllOrdersByCardCode(int numPage, int sizePage, String cardCode) {
        //Generamos la paginacion
        Pageable pageable = PageRequest.of(numPage, sizePage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numPage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePage);
        //Buscamos y mapeamos a nuestro DTO
        Page<OrdenCompraModel> orders = iOrden.findAll(pageable);
        List<OrdenCompraModel> listaOrdenes = orders.getContent();
        List<OrdenCompraDTO> contenido = listaOrdenes.stream().filter(ordenCompra -> ordenCompra.getCardCode().equals(cardCode)).map(ordenCompra -> mapearEndidadDTO(ordenCompra)).collect(Collectors.toList());

        log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
        //Agregamos el contenido a nuestro response
        OrdenCompraResponse ordenCompraResponse = new OrdenCompraResponse();
        ordenCompraResponse.setContent(contenido);
        ordenCompraResponse.setNumPage(orders.getNumber());
        ordenCompraResponse.setSizePage(orders.getSize());
        ordenCompraResponse.setAllElements(orders.getTotalElements());
        ordenCompraResponse.setAllPage(orders.getTotalPages());
        ordenCompraResponse.setLast(orders.isLast());
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}", ordenCompraResponse);
        return ordenCompraResponse;
    }

    @Override
    public OrdenCompraDTO getOrderById(Long idOrdenCompra) {
        //Obtener y parsear el producto
        OrdenCompraModel ordenBuscada = iOrden.findById(idOrdenCompra).orElseThrow(() -> new ResourceNotFoundException("Orden", "id", idOrdenCompra));

        log.info("SE HA ENCONTRADO CORRECTMENTE LA ORDEN BUSCADA Y PROCEDERA A DEVOLVER EN FORMA DE DTO");
        return mapearEndidadDTO(ordenBuscada);
    }

    @Override
    public OrdenCompraDTO getOrderByDocNum(Long docNum) {
        //Obtener el docNum y buscamos en SAP
        log.info("EL DOC ENTRY A BUSCAR ES: {}", docNum);
        OrdenCompraDTO ordenCompra = mapearDTOSAP(restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + docNum, OrdenCompraSAP.class));

        log.info("LA ORDEN TRAIDA DESDE SAP ES: {}", ordenCompra);
        if (ordenCompra.getDocNum() != null) {
            OrdenCompraModel ordenCompraBuscada = iOrden.findByDocNum(ordenCompra.getDocNum()).orElseGet(() -> {
                log.info("ORDEN NUEVA, SE CREARA");
                OrdenCompraModel ordenGuarda = iOrden.save(mapearDTOEntidad(ordenCompra));
                return ordenGuarda;
            });
            return mapearEndidadDTO(ordenCompraBuscada);
        }
        return null;
    }

    @Override
    public OrdenCompraDTO assignOrderSAP(Long idOrder, Long docNum) {
        OrdenCompraModel ordenBuscada = iOrden.findById(idOrder).orElseThrow(() -> new ResourceNotFoundException("Orden", "id", idOrder));

        OrdenCompraDTO ordenCompra = mapearDTOSAP(restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + docNum, OrdenCompraSAP.class));
        if (ordenCompra.getDocNum() != null) {

            ordenBuscada.setDocTotal(ordenCompra.getDocTotal());
            ordenBuscada.setCardName(ordenCompra.getCardName());
            ordenBuscada.setDocDate(ordenCompra.getDocDate());
            ordenBuscada.setDocNum(ordenCompra.getDocNum());
            ordenBuscada.setDocTotal(ordenCompra.getDocTotal());
            ordenBuscada.setDocTotalSys(ordenCompra.getDocTotalSys());
            ordenBuscada.setVatSum(ordenCompra.getVatSum());
            ordenBuscada.setNotaRemision(ordenCompra.getNota_remision());
            ordenBuscada.setCardCode(ordenCompra.getCardCode());
            OrdenCompraModel ordenAsignada = iOrden.save(ordenBuscada);
            return mapearEndidadDTO(ordenAsignada);
        }
        return null;
    }

    @Override
    public List<DetalleCompraDTO> getDetailsOrder(Long idOrder) {
        ItemServiceImpl itemImpl = new ItemServiceImpl();
        OrdenCompraModel po = iOrden.findById(idOrder).orElseThrow(() -> new ResourceNotFoundException("Orden", "id", idOrder));
        OrdenCompraSAP ordenCompraSAP =restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + po.getDocNum(), OrdenCompraSAP.class);

        //Verifico la cantidad de detalle que posee la orden
        if(po.getDetallesOrdenes().size()==ordenCompraSAP.getDocumentLines().size()){
            log.info("Size de la lista: {}",ordenCompraSAP.getDocumentLines().size());
            List<DetalleCompraDTO> listaDevuelta = po.getDetallesOrdenes().stream().map(detalle -> mapearEndidadDTODetalles(detalle)).collect(Collectors.toList());
            return listaDevuelta;
        }
        else {
            log.info("Size de la lista: {}",ordenCompraSAP.getDocumentLines().size());
            log.info("Size de la lista de ordenes: {}",po.getDetallesOrdenes().size());
            //Obtengo la primera lista de los detalles

            //Obtengo la primera lista de los detalles
            DetalleCompraModel detalleCompra = new DetalleCompraModel();

            List<DetalleCompraModel> listaDetalles = ordenCompraSAP.getDocumentLines().stream().map(foto -> {
                detalleCompra.setItemCode(foto.getItemCode());
                detalleCompra.setItemDescription(foto.getItemDescription());
                detalleCompra.setCantidad(foto.getQuantity());
                detalleCompra.setPrecio(foto.getPrice());
                return detalleCompra;
            }).collect(Collectors.toList());

            //Asigno los atributos requridos al detalle
            List<DetalleCompraModel> listaDetallesNueva = listaDetalles.stream().map(detalleMapeado -> {
                ItemModel itemBuscado = iItem.findByItemCode(detalleMapeado.getItemCode()).orElseGet(() -> {
                    log.info("EL ITEM NO ES NUEVO, SE CREARA UNO");
                    ItemDTO itemNuevo = sItem.getItemByItemcode(detalleMapeado.getItemCode());
                    return mapearDTOEntidad(itemNuevo);
                });
                detalleMapeado.setItemModel(itemBuscado);
                detalleMapeado.setOrden(po);

                return detalleMapeado;
            }).collect(Collectors.toList());

            po.setDetallesOrdenes(listaDetallesNueva);
            //Guardo todos los detalles
            iDetalle.saveAll(listaDetallesNueva);
            iOrden.save(po);

            //Devuelvo en forma de lista los detalles
            List<DetalleCompraDTO> listaDevuelta = listaDetallesNueva.stream().map(detalle -> mapearEndidadDTODetalles(detalle)).collect(Collectors.toList());
            return listaDevuelta;
        }
    }

    @Override
    public List<ItemFotoDTO> getDetailsFoto(Long idOrder) {
        ItemServiceImpl itemImpl = new ItemServiceImpl();
        ItemFotoServiceImpl itemFotoService = new ItemFotoServiceImpl();
        OrdenCompraModel po = iOrden.findById(idOrder).orElseThrow(() -> new ResourceNotFoundException("Orden", "id", idOrder));
        OrdenCompraSAP ordenCompraSAP= restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + po.getDocNum(), OrdenCompraSAP.class);

        //Verifico la cantidad de detalle que posee la orden
        if(po.getDetallesOrdenes().size() == ordenCompraSAP.getDocumentLines().size()){
            List<ItemFotoDTO> listaDevuelta = po.getFotoOrdenes().stream().map(detalle -> itemFotoService.mapearEndidadDTO(detalle)).collect(Collectors.toList());
            return listaDevuelta;
        }
        else{
            //Obtengo la primera lista de los detalles
            ItemFotoModel itemFotoNuevo = new ItemFotoModel();
            List<ItemFotoModel> listaDetalles = ordenCompraSAP.getDocumentLines().stream().map(foto -> {
                itemFotoNuevo.setItemCode(foto.getItemCode());
                itemFotoNuevo.setItemName(foto.getItemDescription());
                return itemFotoNuevo;
            }).collect(Collectors.toList());


            //Asigno los atributos requridos al detalle
            List<ItemFotoModel> listaDetallesNueva = listaDetalles.stream().map(detalleMapeado -> {
                ItemModel itemBuscado = iItem.findByItemCode(detalleMapeado.getItemCode()).orElseGet(() -> {
                    log.info("EL ITEM NO ES NUEVO, SE CREARA UNO");
                    ItemDTO itemNuevo = sItem.getItemByItemcode(detalleMapeado.getItemCode());
                    return mapearDTOEntidad(itemNuevo);
                });
                detalleMapeado.setItemModel(itemBuscado);
                detalleMapeado.setOrden(po);
                return detalleMapeado;
            }).collect(Collectors.toList());

            po.setFotoOrdenes(listaDetallesNueva);
            //Guardo todos los detalles
            iFoto.saveAll(listaDetallesNueva);
            iOrden.save(po);

            //Devuelvo en forma de lista los detalles
            List<ItemFotoDTO> listaDevuelta = listaDetallesNueva.stream()
                    .map(detalle ->itemFotoService.mapearEndidadDTO(detalle))
                    .collect(Collectors.toList());
            return listaDevuelta;
        }
    }

    //Mapear los datos de dto a entidad
    protected OrdenCompraModel mapearDTOEntidad(OrdenCompraDTO ordenCompraDTO) {
        OrdenCompraModel ordenEntidad = modelMapper.map(ordenCompraDTO, OrdenCompraModel.class);
        return ordenEntidad;
    }

    //Mapear los datos de entidad a dto
    protected OrdenCompraDTO mapearEndidadDTO(OrdenCompraModel ordenCompraModel) {
        OrdenCompraDTO ordenCompraDTO = modelMapper.map(ordenCompraModel, OrdenCompraDTO.class);
        return ordenCompraDTO;
    }

    //Mapear los datos de entidad a dto
    protected DetalleCompraDTO mapearEndidadDTODetalles(DetalleCompraModel detalleCompraModel) {
        DetalleCompraDTO detalleCompraDTO = modelMapper.map(detalleCompraModel, DetalleCompraDTO.class);
        return detalleCompraDTO;
    }

    //Mapear los datos de dto a entidad
    public DetalleCompraModel mapearDTOEntidadDetalle(DetalleCompraDTO detalleCompraDTO) {
        DetalleCompraModel detalleCompraModel = modelMapper.map(detalleCompraDTO, DetalleCompraModel.class);
        return detalleCompraModel;
    }

    private OrdenCompraDTO mapearDTOSAP(OrdenCompraSAP ordenCompraSAP) {
        OrdenCompraDTO ordenCompraDTO = modelMapper.map(ordenCompraSAP, OrdenCompraDTO.class);
        return ordenCompraDTO;
    }

    private ItemModel mapearDTOEntidad(ItemDTO itemDTO){
        ItemModel itemEntidad = modelMapper.map(itemDTO,ItemModel.class);
        return itemEntidad;
    }
    private ItemFotoModel mapearDTOEntidad(ItemFotoDTO itemDTO){
        ItemFotoModel itemEntidad = modelMapper.map(itemDTO,ItemFotoModel.class);
        return itemEntidad;
    }

}
