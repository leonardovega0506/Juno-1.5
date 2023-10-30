package mx.com.ananda.cronos.juno.service.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.exception.ResourceNotFoundException;
import mx.com.ananda.cronos.juno.model.dto.http.ItemDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.model.entity.ItemFotoModel;
import mx.com.ananda.cronos.juno.model.entity.ItemModel;
import mx.com.ananda.cronos.juno.repository.IFotoItemRepository;
import mx.com.ananda.cronos.juno.response.ItemFotoResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IFotoItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemFotoServiceImpl implements IFotoItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IFotoItemRepository iFoto;

    // **Obtener todos los items del area de foto
    @Override
    public ItemFotoResponse getAllItems(int numPage, int sizePage, String orderBy, String sortDir) {
        //Generamos la paginacion
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numPage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePage);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA ORGANZAZCION, {}", orderBy);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA FORMA, {}", sortDir);
        //Buscamos y mapeamos a nuestro DTO
        Page<ItemFotoModel> itemsFoto = iFoto.findAll(pageable);
        List<ItemFotoModel> listaItemsFM = itemsFoto.getContent();
        List<ItemFotoDTO> contenido = listaItemsFM.stream().map(itemFoto -> mapearEndidadDTO(itemFoto)).collect(Collectors.toList());
        log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
        //Agregamos el contenido a nuestro response
        ItemFotoResponse fotoResponse = new ItemFotoResponse();
        fotoResponse.setContent(contenido);
        fotoResponse.setNumPage(itemsFoto.getNumber());
        fotoResponse.setSizePage(itemsFoto.getSize());
        fotoResponse.setAllElements(itemsFoto.getTotalElements());
        fotoResponse.setAllPage(itemsFoto.getTotalPages());
        fotoResponse.setLast(itemsFoto.isLast());
        log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}",fotoResponse);
        return fotoResponse;
    }

    //Guardar un itemNuevo
    @Override
    public ItemFotoDTO saveItem(ItemFotoDTO itemFotoDTO) {
        //Mapear y generar el producto nuevo
        ItemFotoModel itemFotoModel = mapearDTOEntidad(itemFotoDTO);
        ItemFotoModel nuevoItem = iFoto.save(itemFotoModel);
        log.info("SE HA GUARDADO CORRECTAMENTE LA INFROMACION");
        ItemFotoDTO responseItem = mapearEndidadDTO(nuevoItem);
        log.info("SE DEVUELVE EL DTO");
        return responseItem;
    }

    //Buscar el item por id
    @Override
    public ItemFotoDTO getItemById(Long idItemF) {
        //Obtener y parsear el producto
        ItemFotoModel itemBuscado = iFoto.findById(idItemF).orElseThrow(()->new ResourceNotFoundException("Producto","id",idItemF));
        log.info("SE HA ENCONTRADO CORRECTMENTE EL PRODUCTO BUSCADO Y PROCEDERA A DEVOLVER EN FORMA DE DTO");
        return mapearEndidadDTO(itemBuscado);
    }

    //Buscar el item por item code
    @Override
    public ItemFotoDTO getItemByItemCode(String itemCodeF) {
        //Obtener y parsear el producto
        ItemFotoModel itemBuscadoItemCode = iFoto.findByItemCode(itemCodeF).orElseThrow(()-> new ResourceNotFoundException("Producto","codigo articulo",itemCodeF));
        log.info("SE HA ENCONTRADO CORRECTMENTE EL PRODUCTO BUSCADO Y PROCEDERA A DEVOLVER EN FORMA DE DTO");
        return mapearEndidadDTO(itemBuscadoItemCode);
    }

    //Actualizar el producto
    @Override
    public void updateItemFoto(ItemFotoDTO itemFotoDTO) {
        //Buscar el producto por el id
        ItemFotoModel itemFotoBuscado = iFoto.findById(itemFotoDTO.getIdFoto()).orElseThrow(()-> new ResourceNotFoundException("Producto","id",itemFotoDTO.getIdFoto()));
        log.info("SE HA ENCONTRADO CORRECTMENTE EL PRODUCTO BUSCADO");
        ItemFotoModel itemActualizado = mapearDTOEntidad(itemFotoDTO);
        //Actualizar el producto
        iFoto.save(itemFotoBuscado);
        log.info("SE HA ACTUALIZADO CORRECTAMENTE EL PRODUCTO");
    }

    //Mapear los datos de dto a entidad
    protected ItemFotoModel mapearDTOEntidad(ItemFotoDTO itemFotoDTO){
        ItemFotoModel itemFotoEntidad = modelMapper.map(itemFotoDTO,ItemFotoModel.class);
        return itemFotoEntidad;
    }


    //Mapear los datos de entidad a dto
    protected ItemFotoDTO mapearEndidadDTO(ItemFotoModel itemFotoModel){
        ItemFotoDTO itemFotoDTO = modelMapper.map(itemFotoModel, ItemFotoDTO.class);
        return itemFotoDTO;
    }


}
