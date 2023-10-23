package mx.com.ananda.cronos.juno.service.implementation;


import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.exception.ResourceNotFoundException;
import mx.com.ananda.cronos.juno.model.dto.http.DetalleCompraDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemDTO;
import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;
import mx.com.ananda.cronos.juno.model.entity.DetalleCompraModel;
import mx.com.ananda.cronos.juno.model.entity.ItemModel;
import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;
import mx.com.ananda.cronos.juno.repository.IDetalleCompraRepository;
import mx.com.ananda.cronos.juno.repository.IOrdenCompraRepository;
import mx.com.ananda.cronos.juno.repository.IRepositoryItem;
import mx.com.ananda.cronos.juno.response.ItemResponse;
import mx.com.ananda.cronos.juno.response.OrdenCompraResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IOrdenCompraService;
import mx.com.ananda.cronos.juno.service.interfaces.IServiceItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private IServiceItem sItem;


    //Obtener todas las ordenes
    @Override
    public OrdenCompraResponse getAllOrders(int numPage, int sizePage, String orderBy, String sortDir) {
        //Generamos la paginacion
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
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
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}",ordenCompraResponse);
        return ordenCompraResponse;
    }

    @Override
    public OrdenCompraResponse getAllOrdersByCardName(int numPage, int sizePage, String cardName) {
        //Generamos la paginacion
        Pageable pageable = PageRequest.of(numPage,sizePage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numPage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePage);
        //Buscamos y mapeamos a nuestro DTO
        Page<OrdenCompraModel> orders = iOrden.findAll(pageable);
        List<OrdenCompraModel> listaOrdenes = orders.getContent();
        List<OrdenCompraDTO> contenido =listaOrdenes
                .stream()
                .filter(ordenCompra -> ordenCompra.getCardName().equals(cardName))
                .map(ordenCompra -> mapearEndidadDTO(ordenCompra))
                .collect(Collectors.toList());

        log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
        //Agregamos el contenido a nuestro response
        OrdenCompraResponse ordenCompraResponse = new OrdenCompraResponse();
        ordenCompraResponse.setContent(contenido);
        ordenCompraResponse.setNumPage(orders.getNumber());
        ordenCompraResponse.setSizePage(orders.getSize());
        ordenCompraResponse.setAllElements(orders.getTotalElements());
        ordenCompraResponse.setAllPage(orders.getTotalPages());
        ordenCompraResponse.setLast(ordenCompraResponse.isLast());
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}",ordenCompraResponse);
        return ordenCompraResponse;
    }

    @Override
    public OrdenCompraResponse getAllOrdersByCardCode(int numPage, int sizePage, String cardCode) {
        //Generamos la paginacion
        Pageable pageable = PageRequest.of(numPage,sizePage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numPage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePage);
        //Buscamos y mapeamos a nuestro DTO
        Page<OrdenCompraModel> orders = iOrden.findAll(pageable);
        List<OrdenCompraModel> listaOrdenes = orders.getContent();
        List<OrdenCompraDTO> contenido =listaOrdenes
                .stream()
                .filter(ordenCompra -> ordenCompra.getCardCode().equals(cardCode))
                .map(ordenCompra -> mapearEndidadDTO(ordenCompra))
                .collect(Collectors.toList());

        log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
        //Agregamos el contenido a nuestro response
        OrdenCompraResponse ordenCompraResponse = new OrdenCompraResponse();
        ordenCompraResponse.setContent(contenido);
        ordenCompraResponse.setNumPage(orders.getNumber());
        ordenCompraResponse.setSizePage(orders.getSize());
        ordenCompraResponse.setAllElements(orders.getTotalElements());
        ordenCompraResponse.setAllPage(orders.getTotalPages());
        ordenCompraResponse.setLast(orders.isLast());
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}",ordenCompraResponse);
        return ordenCompraResponse;
    }

    @Override
    public OrdenCompraDTO getOrderById(Long idOrdenCompra) {
        //Obtener y parsear el producto
        OrdenCompraModel ordenBuscada = iOrden.findById(idOrdenCompra).orElseThrow(
                ()->new ResourceNotFoundException("Orden","id",idOrdenCompra));

        log.info("SE HA ENCONTRADO CORRECTMENTE LA ORDEN BUSCADA Y PROCEDERA A DEVOLVER EN FORMA DE DTO");
        return mapearEndidadDTO(ordenBuscada);
    }

    @Override
    public OrdenCompraDTO getOrderByDocNum(Long docNum) {
        //Obtener el docNum y buscamos en SAP
        log.info("EL DOC ENTRY A BUSCAR ES: {}", docNum);
        OrdenCompraModel ordenCompra =new OrdenCompraModel(); //mapearEntidad(restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + numOrden, PurchaseOrders.class));

        //log.info("LA ORDEN TRAIDA DESDE SAP ES: {}", ordenCompra);
        Optional<OrdenCompraModel> oOrden = null;
        if (ordenCompra.getDocNum() == null) {
            oOrden = iOrden.findByDocNum(docNum);
            if (oOrden.isEmpty()) {
                log.info("ORDEN NUEVA, SE CREARA");
                //PurchaseOrders po = restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + numOrden, PurchaseOrders.class);
                /*List<DetalleCompraModel> listaDetalles = new ArrayList<>();
                for (var detalle : po.getDocumentLines()) {

                    listaDetalles.add(mapearEntidadDetalles(detalle));
                    for (DetalleCompraModel dt : listaDetalles) {

                        String itemCode = dt.getItemCode();
                        Optional<ItemModel> itemTraido = iItem.findByItemCode(itemCode);
                        if (itemTraido.isPresent()) {
                            log.info("EL ITEM EXISTE ES: {}", itemTraido);
                            dt.setItemModel(itemTraido.get());
                            dt.setOrden(ordenCompra);
                            iDetalle.save(dt);

                        } else {
                            log.info("EL ITEM NO ES NUEVO, SE CREARA UNO");
                            ItemModel itemNuevo = null;
                            try {
                                itemNuevo = sItem.findByItemCode(itemCode);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            dt.setItemModel(itemNuevo);
                            dt.setOrden(ordenCompra);
                            iDetalle.save(dt);
                        }
                    }
                }*/
                OrdenCompraModel ordenGuarda = iOrden.save(ordenCompra);
                return mapearEndidadDTO(ordenGuarda);
            } else {
                log.info("ORDEN YA EXISTENTE, SE CREARA SOLO EL RESPONSE");
                OrdenCompraModel ordenExistente = iOrden.findByDocNum(docNum)
                        .orElseThrow(()->new ResourceNotFoundException("Orden","DocNum",docNum));
                return mapearEndidadDTO(ordenExistente);
            }
        }
        return null;
    }

    @Override
    public OrdenCompraDTO assignOrderSAP(Long idOrder, Long docNum) {
        OrdenCompraModel ordenBuscada = iOrden.findById(idOrder).orElseThrow(()->new ResourceNotFoundException("Orden","id",docNum));

            OrdenCompraModel ordenCompra = new OrdenCompraModel();//mapearEntidad(restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + numOrden, PurchaseOrders.class));
            if (ordenCompra.getDocNum() != null) {

                ordenBuscada.setDocTotal(ordenCompra.getDocTotal());
                ordenBuscada.setCardName(ordenCompra.getCardName());
                ordenBuscada.setDocDate(ordenCompra.getDocDate());
                ordenBuscada.setDocNum(ordenCompra.getDocNum());
                ordenBuscada.setDocTotal(ordenCompra.getDocTotal());
                ordenBuscada.setDocTotalSys(ordenCompra.getDocTotalSys());
                ordenBuscada.setVatSum(ordenCompra.getVatSum());
                ordenBuscada.setNota_remision(ordenCompra.getNota_remision());
                ordenBuscada.setCardCode(ordenCompra.getCardCode());
                OrdenCompraModel ordenAsignada= iOrden.save(ordenBuscada);
                return mapearEndidadDTO(ordenAsignada);
            } else {
                return null;
            }
        }

    @Override
    public List<DetalleCompraDTO> getDetailsOrder(Long idOrder) {
        ItemServiceImpl itemImpl = new ItemServiceImpl();
        OrdenCompraModel po  = iOrden.findById(idOrder).orElseThrow(()->new ResourceNotFoundException("Orden","id",idOrder));//= restTemplate.getForObject(basePath + "/purchaseOrder/docNum?NumeroOrden=" + numOrden, PurchaseOrders.class);
                List<DetalleCompraModel> listaDetalles = new ArrayList<>();
                List<DetalleCompraModel> listaDetallesNueva = new ArrayList<>();
                for (var detalle : po.getDetallesOrdenes()) {
                    listaDetalles.add(detalle);
                    for (DetalleCompraModel dt : listaDetalles) {

                        String itemCode = dt.getItemCode();
                        ItemDTO itemNuevo = new ItemDTO();
                        Optional<ItemModel> itemTraido = iItem.findByItemCode(itemCode);
                        if (itemTraido.isPresent()) {
                            log.info("EL ITEM EXISTE ES: {}", itemTraido);
                            dt.setItemModel(itemTraido.get());
                            dt.setOrden(po);
                            iDetalle.save(dt);
                            listaDetallesNueva.add(dt);

                        } else {
                            log.info("EL ITEM NO ES NUEVO, SE CREARA UNO");
                            itemNuevo = sItem.getItemByItemcode(itemCode);
                            dt.setItemModel(itemImpl.mapearDTOEntidad(itemNuevo));
                            dt.setOrden(po);
                            iDetalle.save(dt);
                        }
                    }
                }
                List<DetalleCompraDTO> listaDevuelta = listaDetalles
                        .stream()
                        .map(detalle->mapearEndidadDTODetalles(detalle))
                        .collect(Collectors.toList());
                return listaDevuelta;
    }

    //Mapear los datos de dto a entidad
    protected OrdenCompraModel mapearDTOEntidad(OrdenCompraDTO ordenCompraDTO){
        OrdenCompraModel ordenEntidad = modelMapper.map(ordenCompraDTO,OrdenCompraModel.class);
        return ordenEntidad;
    }

    //Mapear los datos de entidad a dto
    protected  OrdenCompraDTO mapearEndidadDTO(OrdenCompraModel ordenCompraModel){
        OrdenCompraDTO ordenCompraDTO = modelMapper.map(ordenCompraModel, OrdenCompraDTO.class);
        return ordenCompraDTO;
    }

    //Mapear los datos de entidad a dto
    protected DetalleCompraDTO mapearEndidadDTODetalles(DetalleCompraModel detalleCompraModel){
        DetalleCompraDTO detalleCompraDTO = modelMapper.map(detalleCompraModel, DetalleCompraDTO.class);
        return detalleCompraDTO;
    }

    //Mapear los datos de dto a entidad
    public DetalleCompraModel mapearDTOEntidadDetalle(DetalleCompraDTO detalleCompraDTO){
        DetalleCompraModel detalleCompraModel = modelMapper.map(detalleCompraDTO,DetalleCompraModel.class);
        return detalleCompraModel;
    }
}
