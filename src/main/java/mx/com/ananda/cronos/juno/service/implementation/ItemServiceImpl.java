package mx.com.ananda.cronos.juno.service.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.exception.ResourceNotFoundException;
import mx.com.ananda.cronos.juno.model.dto.http.ItemDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.model.dto.sap.ItemSAP;
import mx.com.ananda.cronos.juno.model.entity.ItemFotoModel;
import mx.com.ananda.cronos.juno.model.entity.ItemModel;
import mx.com.ananda.cronos.juno.repository.IRepositoryItem;
import mx.com.ananda.cronos.juno.response.ItemFotoResponse;
import mx.com.ananda.cronos.juno.response.ItemResponse;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemServiceImpl implements IServiceItem {

    @Autowired
    private IRepositoryItem iItem;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    //Valor asignado desde el properties para la url a consumor
    @Value("${spring.external.service.base-url}")
    private String basePath;


    //Obtener todos los items
    @Override
    public ItemResponse getAllItems(int numPage, int sizePage, String orderBy,String sortDir) {
        //Generamos la paginacion
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numPage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA ORGANZAZCION, {}", orderBy);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA FORMA, {}", sortDir);
        //Buscamos y mapeamos a nuestro DTO
        Page<ItemModel> items = iItem.findAll(pageable);
        List<ItemModel> listaItemsFM = items.getContent();
        List<ItemDTO> contenido = listaItemsFM.stream().map(itemFoto -> mapearEndidadDTO(itemFoto)).collect(Collectors.toList());
        log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
        //Agregamos el contenido a nuestro response
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setContent(contenido);
        itemResponse.setNumPage(items.getNumber());
        itemResponse.setSizePage(items.getSize());
        itemResponse.setAllElements(items.getTotalElements());
        itemResponse.setAllPage(items.getTotalPages());
        itemResponse.setLast(items.isLast());
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}",itemResponse);
        return itemResponse;
    }


    //Guardar un nuevo item
    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        log.info("HA ENTRADO A CREAR UN ITEM");
        iItem.save(mapearDTOEntidad(itemDTO));
        ItemDTO itemRegreso = mapearSAPDTO(restTemplate.postForObject(basePath + "/item", itemDTO, ItemSAP.class));
        log.info("EXITO! SE HA CREADO EXITOSAMENTE EL ITEM");
        return itemRegreso;
    }

    //Buscar item por id
    @Override
    public ItemDTO getItemById(Long idItem) {
        //Obtener y parsear el producto
        ItemModel itemBuscado = iItem.findById(idItem).orElseThrow(()->new ResourceNotFoundException("Producto","id",idItem));
        log.info("SE HA ENCONTRADO CORRECTMENTE EL PRODUCTO BUSCADO Y PROCEDERA A DEVOLVER EN FORMA DE DTO");
        return mapearEndidadDTO(itemBuscado);
    }

    //Buscar item por itemCode
    @Override
    public ItemDTO getItemByItemcode(String itemCode) {
        //Obtener y parsear el producto

        ItemDTO itemBuscadoDTO = mapearSAPDTO(restTemplate.getForObject(basePath+"/itemP/itemCode?itemCode="+itemCode,ItemSAP.class));

        if(itemBuscadoDTO.getItemCode() == null){
            return null;
        }
        else{
            ItemModel itemBuscadoItemCode = iItem.findByItemCode(itemBuscadoDTO.getItemCode()).orElseGet(()->{
                log.info("SE CREARA UNO NUEVO");
                 ItemModel itemNuevo = iItem.save(mapearDTOEntidad(itemBuscadoDTO));
                return itemNuevo;
            });
            log.info("SE HA ENCONTRADO CORRECTMENTE EL PRODUCTO BUSCADO Y PROCEDERA A DEVOLVER EN FORMA DE DTO");
            return mapearEndidadDTO(itemBuscadoItemCode);
        }
    }


    //Actualizar item
    @Override
    public void updateItem(ItemDTO itemDTO) {
        //Buscar el producto por el id
        ItemModel itemBuscado = iItem.findById(itemDTO.getIdItem()).orElseThrow(()->new ResourceNotFoundException("Producto","id",itemDTO.getIdItem()));
        log.info("SE HA ENCONTRADO CORRECTMENTE EL PRODUCTO BUSCADO");
        if(itemBuscado.getIdItem() == itemDTO.getIdItem()) {
            //Actualizar el producto
            restTemplate.put(basePath + "/item?itemCode="+itemBuscado.getItemCode(), itemDTO, ItemSAP.class);
            iItem.save(mapearDTOEntidad(itemDTO));
            log.info("SE HA ACTUALIZADO CORRECTAMENTE EL PRODUCTO");
        }
    }


    //Mapear los datos de dto a entidad
    protected  ItemModel mapearDTOEntidad(ItemDTO itemDTO){
        ItemModel itemEntidad = modelMapper.map(itemDTO,ItemModel.class);
        return itemEntidad;
    }


    //Mapear los datos de entidad a dto
    protected  ItemDTO mapearEndidadDTO(ItemModel itemModel){
        ItemDTO itemDTO = modelMapper.map(itemModel, ItemDTO.class);
        return itemDTO;
    }

    private ItemDTO mapearSAPDTO(ItemSAP itemSAP){
        ItemDTO itemDTO = modelMapper.map(itemSAP,ItemDTO.class);
        itemDTO.setItemCode(itemSAP.getItemCode());
        return itemDTO;
    }
}
