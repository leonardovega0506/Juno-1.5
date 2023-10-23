package mx.com.ananda.cronos.juno.service.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.exception.ResourceNotFoundException;
import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;
import mx.com.ananda.cronos.juno.model.dto.http.RegistroTiempoDTO;
import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;
import mx.com.ananda.cronos.juno.model.entity.RegistroTiempoModel;
import mx.com.ananda.cronos.juno.repository.IOrdenCompraRepository;
import mx.com.ananda.cronos.juno.repository.IRegistroTiempoRepository;
import mx.com.ananda.cronos.juno.response.OrdenCompraResponse;
import mx.com.ananda.cronos.juno.response.RegistroTiempoResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IOrdenCompraService;
import mx.com.ananda.cronos.juno.service.interfaces.IRegistroTiempoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistroTiempoServiceImpl implements IRegistroTiempoService {

    @Autowired
    private IRegistroTiempoRepository iRegistro;

    @Autowired
    private IOrdenCompraService sOrden;

    @Autowired
    private IOrdenCompraRepository iOrden;

    @Autowired
    private ModelMapper modelMapper;

    //Obtener los registros completos y por area
    @Override
    public RegistroTiempoResponse getAllRegistros(int numeroPagina, int sizePagina, String orderBy, String sortDir, String area) {
        //Generamos la paginacion
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numeroPagina, sizePagina, sort);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numeroPagina);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePagina);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA ORGANZAZCION, {}", orderBy);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA FORMA, {}", sortDir);
        //Buscamos y mapeamos a nuestro DTO
        Page<RegistroTiempoModel> registros = iRegistro.findAll(pageable);
        List<RegistroTiempoModel> listaRegistro = registros.getContent();
        if (area != null || area != "") {
            List<RegistroTiempoDTO> contenido =
                    listaRegistro.stream()
                            .filter(registro -> registro.getArea().equals(area))
                            .map(registro -> mapearDTOEntidad(registro))
                            .collect(Collectors.toList());
            log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
            //Agregamos el contenido a nuestro response
            RegistroTiempoResponse registroTiempoResponse = new RegistroTiempoResponse();
            registroTiempoResponse.setContent(contenido);
            registroTiempoResponse.setNumPage(registros.getNumber());
            registroTiempoResponse.setSizePage(registros.getSize());
            registroTiempoResponse.setAllPage(registroTiempoResponse.getAllPage());
            registroTiempoResponse.setAllElements(registroTiempoResponse.getAllElements());
            registroTiempoResponse.setLast(registros.isLast());
            log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}", registroTiempoResponse);
            return registroTiempoResponse;
        } else {
            List<RegistroTiempoDTO> contenido =
                    listaRegistro.stream()
                            .map(registro -> mapearDTOEntidad(registro))
                            .collect(Collectors.toList());
            log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
            //Agregamos el contenido a nuestro response
            RegistroTiempoResponse registroTiempoResponse = new RegistroTiempoResponse();
            registroTiempoResponse.setContent(contenido);
            registroTiempoResponse.setNumPage(registros.getNumber());
            registroTiempoResponse.setSizePage(registros.getSize());
            registroTiempoResponse.setAllPage(registroTiempoResponse.getAllPage());
            registroTiempoResponse.setAllElements(registroTiempoResponse.getAllElements());
            registroTiempoResponse.setLast(registros.isLast());
            log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}", registroTiempoResponse);
            return registroTiempoResponse;
        }
    }

    //Obtener los registros por proveedor
    @Override
    public RegistroTiempoResponse getAllRegistroByCardName(int numeroPagina, int sizePagina, String area, String orderBy, String sortDir, String cardName) {
        //Generamos la paginacion
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numeroPagina, sizePagina, sort);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL NUMERO DE PAGINA, {}", numeroPagina);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE EL TAMAÑO DE PAGINA, {}", sizePagina);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA ORGANZAZCION, {}", orderBy);
        log.info("SE EMPEZARA A BUSCAR EL MEDIANTE LA FORMA, {}", sortDir);
        //Buscamos y mapeamos a nuestro DTO
        Page<RegistroTiempoModel> registros = iRegistro.findAll(pageable);
        List<RegistroTiempoModel> listaRegistro = registros.getContent();
        if (area != null || area != "") {
            List<RegistroTiempoDTO> contenido =
                    listaRegistro.stream()
                            .filter(registro -> registro.getArea().equals(area) && registro.getProveedor().contains(cardName))
                            .map(registro -> mapearDTOEntidad(registro))
                            .collect(Collectors.toList());
            log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
            //Agregamos el contenido a nuestro response
            RegistroTiempoResponse registroTiempoResponse = new RegistroTiempoResponse();
            registroTiempoResponse.setContent(contenido);
            registroTiempoResponse.setNumPage(registros.getNumber());
            registroTiempoResponse.setSizePage(registros.getSize());
            registroTiempoResponse.setAllPage(registroTiempoResponse.getAllPage());
            registroTiempoResponse.setAllElements(registroTiempoResponse.getAllElements());
            registroTiempoResponse.setLast(registros.isLast());
            log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}", registroTiempoResponse);
            return registroTiempoResponse;
        } else {
            List<RegistroTiempoDTO> contenido =
                    listaRegistro.stream()
                            .filter(registro -> registro.getProveedor().contains(cardName))
                            .map(registro -> mapearDTOEntidad(registro))
                            .collect(Collectors.toList());
            log.info("OBTENEMOS LA LISTA MAPEADA A DTO");
            //Agregamos el contenido a nuestro response
            RegistroTiempoResponse registroTiempoResponse = new RegistroTiempoResponse();
            registroTiempoResponse.setContent(contenido);
            registroTiempoResponse.setNumPage(registros.getNumber());
            registroTiempoResponse.setSizePage(registros.getSize());
            registroTiempoResponse.setAllPage(registroTiempoResponse.getAllPage());
            registroTiempoResponse.setAllElements(registroTiempoResponse.getAllElements());
            registroTiempoResponse.setLast(registros.isLast());
            log.info("SE REGRESA LA INFORMACION DEL RESPONSE: {}", registroTiempoResponse);
            return registroTiempoResponse;
        }
    }

    //Guardar los regsitros con diferentes opciones que se puedan guardar
    @Override
    public RegistroTiempoDTO saveRegistroTiempo(RegistroTiempoDTO registroTiempoDTO, String tipoPedido) {
        List<RegistroTiempoModel> listaRegistros = iRegistro.findByDocNum(registroTiempoDTO.getDocNum());
        RegistroTiempoDTO registroGuardado = new RegistroTiempoDTO();
        RegistroTiempoModel registroNuevo = new RegistroTiempoModel();
        Calendar calendar = Calendar.getInstance();
        OrdenCompraServiceImpl impOrden = new OrdenCompraServiceImpl();
        switch (tipoPedido) {
            //Caso donde el registro es especial
            case "Especial":
                if (listaRegistros.size() == 0) {
                    //Crear y pasar parametros de creacion de pedido especial
                    registroNuevo.setComplementoOrden(1);
                    registroNuevo.setArea("Recibo");
                    registroNuevo.setDateReciboOCLlegada(LocalDate.now());
                    registroNuevo.setTimeReciboOCLlegada(LocalTime.now());
                    registroNuevo.setEstatus("Llegada a Recibo, Pedido Especial");
                    registroNuevo.setProgreso("33.33%");
                    registroNuevo.setCuadrante(registroTiempoDTO.getCuadrante());
                    registroNuevo.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
                    log.info("LOS PARAMETROS HAN SDO ASIGNADOS");

                    //Obtener la orden asociada
                    OrdenCompraDTO orden = sOrden.getOrderByDocNum(registroTiempoDTO.getDocNum());
                    orden.setCuadrante(registroTiempoDTO.getCuadrante());
                    iOrden.save(impOrden.mapearDTOEntidad(orden));
                    registroNuevo.setOrdenCompra(impOrden.mapearDTOEntidad(orden));
                    log.info("SE HA GUARDADO EL REGISTRO NUEVO");
                } else {

                    //Crear y pasar parametros de creacion de pedido especial
                    registroNuevo.setComplementoOrden(listaRegistros.size() + 1);
                    registroNuevo.setArea("Recibo");
                    registroNuevo.setDateReciboOCLlegada(LocalDate.now());
                    registroNuevo.setTimeReciboOCLlegada(LocalTime.now());
                    registroNuevo.setEstatus("Llegada a Recibo, Pedido Especial");
                    registroNuevo.setProgreso("33.33%");
                    registroNuevo.setCuadrante(registroTiempoDTO.getCuadrante());
                    registroNuevo.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
                    log.info("LOS PARAMETROS HAN SDO ASIGNADOS");

                    //Obtener la orden asociada
                    OrdenCompraDTO orden = sOrden.getOrderByDocNum(registroTiempoDTO.getDocNum());
                    orden.setCuadrante(registroTiempoDTO.getCuadrante());
                    iOrden.save(impOrden.mapearDTOEntidad(orden));
                    registroNuevo.setOrdenCompra(impOrden.mapearDTOEntidad(orden));
                    log.info("SE HA GUARDADO EL REGISTRO NUEVO");
                }
                registroGuardado = mapearDTOEntidad(iRegistro.save(registroNuevo));
                return registroGuardado;

            //Caso donde hay orden ya hecha
            case "Orden":
                if (listaRegistros.size() == 0) {
                    //Crear y pasar parametros de creacion de pedido con orden
                    registroNuevo.setComplementoOrden(1);
                    registroNuevo.setArea("Recibo");
                    registroNuevo.setDateReciboOCLlegada(LocalDate.now());
                    registroNuevo.setTimeReciboOCLlegada(LocalTime.now());
                    registroNuevo.setEstatus("Llegada a Recibo, con Orden");
                    registroNuevo.setProgreso("16.6%");
                    registroNuevo.setCuadrante(registroTiempoDTO.getCuadrante());
                    registroNuevo.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
                    log.info("LOS PARAMETROS HAN SDO ASIGNADOS");

                    //Obtener la orden asociada
                    OrdenCompraDTO orden = sOrden.getOrderByDocNum(registroTiempoDTO.getDocNum());
                    orden.setCuadrante(registroTiempoDTO.getCuadrante());
                    iOrden.save(impOrden.mapearDTOEntidad(orden));
                    registroNuevo.setOrdenCompra(impOrden.mapearDTOEntidad(orden));
                    log.info("SE HA GUARDADO EL REGISTRO NUEVO");
                } else {
                    //Crear y pasar parametros de creacion de pedido especial
                    registroNuevo.setComplementoOrden(listaRegistros.size() + 1);
                    registroNuevo.setArea("Recibo");
                    registroNuevo.setDateReciboOCLlegada(LocalDate.now());
                    registroNuevo.setTimeReciboOCLlegada(LocalTime.now());
                    registroNuevo.setEstatus("Llegada a Recibo, Pedido Especial");
                    registroNuevo.setProgreso("16.6%");
                    registroNuevo.setCuadrante(registroTiempoDTO.getCuadrante());
                    registroNuevo.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
                    log.info("LOS PARAMETROS HAN SDO ASIGNADOS");

                    //Obtener la orden asociada
                    OrdenCompraDTO orden = sOrden.getOrderByDocNum(registroTiempoDTO.getDocNum());
                    orden.setCuadrante(registroTiempoDTO.getCuadrante());
                    iOrden.save(impOrden.mapearDTOEntidad(orden));
                    registroNuevo.setOrdenCompra(impOrden.mapearDTOEntidad(orden));
                    log.info("SE HA GUARDADO EL REGISTRO NUEVO");
                }
                registroGuardado = mapearDTOEntidad(iRegistro.save(registroNuevo));
                return registroGuardado;

            //Caso donde no hay orden hecha
            case "Proveedor":

                //Crear y pasar parametros de creacion de pedido con nombre de proveedor
                registroNuevo = new RegistroTiempoModel();
                registroNuevo.setComplementoOrden(1);
                registroNuevo.setArea("Recibo");
                registroNuevo.setDateReciboOCLlegada(LocalDate.now());
                registroNuevo.setTimeReciboOCLlegada(LocalTime.now());
                registroNuevo.setEstatus("Llegada a Recibo");
                registroNuevo.setProgreso("16.6%");
                registroNuevo.setCuadrante(registroTiempoDTO.getCuadrante());
                registroNuevo.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
                log.info("LOS PARAMETROS HAN SDO ASIGNADOS");

                //Obtener la orden asociada
                OrdenCompraDTO orden = new OrdenCompraDTO();
                orden.setCuadrante(registroTiempoDTO.getCuadrante());
                orden.setCardName(registroTiempoDTO.getProveedor());
                orden.setDocNum(registroNuevo.getDocNum());
                iOrden.save(impOrden.mapearDTOEntidad(orden));
                registroNuevo.setOrdenCompra(impOrden.mapearDTOEntidad(orden));
                registroGuardado = mapearDTOEntidad(iRegistro.save(registroNuevo));
                log.info("SE HA GUARDADO EL REGISTRO NUEVO");
                return registroGuardado;
            default:
                //Crear y pasar parametros de creacion de pedido solo
                registroNuevo = new RegistroTiempoModel();
                registroNuevo.setComplementoOrden(1);
                registroNuevo.setArea("Recibo");
                registroNuevo.setDateReciboOCLlegada(LocalDate.now());
                registroNuevo.setTimeReciboOCLlegada(LocalTime.now());
                registroNuevo.setEstatus("Llegada a Recibo");
                registroNuevo.setProgreso("16.6%");
                registroNuevo.setCuadrante(registroTiempoDTO.getCuadrante());
                registroNuevo.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
                log.info("LOS PARAMETROS HAN SDO ASIGNADOS");

                //Obtener la orden asociada
                orden = new OrdenCompraDTO();
                orden.setCuadrante(registroTiempoDTO.getCuadrante());
                orden.setDocNum(registroNuevo.getDocNum());
                iOrden.save(impOrden.mapearDTOEntidad(orden));
                registroNuevo.setOrdenCompra(impOrden.mapearDTOEntidad(orden));
                registroGuardado = mapearDTOEntidad(iRegistro.save(registroNuevo));
                log.info("SE HA GUARDADO EL REGISTRO NUEVO");
                return registroGuardado;
        }
    }

    //Actualizar un registro
    @Override
    public RegistroTiempoDTO updateRegistroTiempo(RegistroTiempoDTO registroTiempoDTO) {

        RegistroTiempoModel registradoBuscado = iRegistro.findById(registroTiempoDTO.getIdRegistro()).orElseThrow(()-> new ResourceNotFoundException("Registro","Id",registroTiempoDTO.getIdRegistro()));
        log.info("SE HA ENCONTRADO CORRECTAMENTE EL REGISTRO");
        RegistroTiempoDTO registroActualizado = mapearDTOEntidad(registradoBuscado);
        log.info("REGISTRO ACTUALIZADO CORRECTAMENTE");
        return registroActualizado;
    }

    //Obtener un regsitro por id
    @Override
    public RegistroTiempoDTO getRegistroById(Long idRegistro) {

        RegistroTiempoModel registroBuscado = iRegistro.findById(idRegistro).orElseThrow(() -> new ResourceNotFoundException("Registro", "Id", idRegistro));
        log.info("SE HA ENCONTRADO CORRECTAMENTE EL REGISTRO");

        return mapearDTOEntidad(registroBuscado);
    }

    //Borrar un registro
    @Override
    public void deleteRegistroById(Long idRegistro) {

        RegistroTiempoModel registradoBuscado = iRegistro.findById(idRegistro).orElseThrow(()-> new ResourceNotFoundException("Registro","Id",idRegistro));
        log.info("SE HA ENCONTRADO CORRECTAMENTE EL REGISTRO");

        iRegistro.delete(registradoBuscado);
        log.info("SE HA BORRADO CORRECTAMENTE EL REGISTRO");
    }

    //Mapear el registro de entidad a dto
    private RegistroTiempoDTO mapearDTOEntidad(RegistroTiempoModel registroTiempoModel) {
        RegistroTiempoDTO registroTiempoDTO = modelMapper.map(registroTiempoModel, RegistroTiempoDTO.class);
        return registroTiempoDTO;
    }


    //Mapear el registro de dto a entidad
    private RegistroTiempoModel mapearEntidadDTO(RegistroTiempoDTO registroTiempoDTO) {
        RegistroTiempoModel registroTiempoModel = modelMapper.map(registroTiempoDTO, RegistroTiempoModel.class);
        return registroTiempoModel;
    }
}
