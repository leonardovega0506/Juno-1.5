package mx.com.ananda.cronos.juno.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.model.dto.http.RegistroTiempoDTO;
import mx.com.ananda.cronos.juno.model.entity.RegistroTiempoModel;
import mx.com.ananda.cronos.juno.response.RegistroTiempoResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IRegistroTiempoService;
import mx.com.ananda.cronos.juno.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Optional;

@Slf4j
@RequestMapping("ananda/cronos1_3/juno1_5/registro")
@RestController
public class RegistroTiempoController {

    @Autowired
    private IRegistroTiempoService sRegistro;

    @GetMapping("")
    public ResponseEntity<RegistroTiempoResponse> traerRegistros(
            @RequestParam(value = "pageNO",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value= "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = GlobalConstants.ORDENAR_DEFECTO) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "area",defaultValue = "") String area
    ){
        return new ResponseEntity<>(sRegistro.getAllRegistros(pageNumber,pageSize,orderBy,sortDir,area), HttpStatus.OK);
    }

    @GetMapping("/porveedor")
    public ResponseEntity<RegistroTiempoResponse> traerRegistrosProveedor(
            @RequestParam(value = "pageNO",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value= "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = GlobalConstants.ORDENAR_DEFECTO) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "area",defaultValue = "") String area,
            @RequestParam(value = "proveedor", defaultValue = "") String proveedor){
        return new ResponseEntity<>(sRegistro.getAllRegistroByCardName(pageNumber,pageSize,area,orderBy,sortDir,proveedor),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroTiempoDTO> traerRegistroById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(sRegistro.getRegistroById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<RegistroTiempoDTO> guardarRegistros(@RequestBody RegistroTiempoDTO registroTiempoDTO,
                                                              @RequestParam(value = "tipo") String tipo){
        return new ResponseEntity<>(sRegistro.saveRegistroTiempo(registroTiempoDTO,tipo),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarRegistroById(@PathVariable(value = "id") Long id){
        sRegistro.deleteRegistroById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/compra-m/{id}")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoCompraMM(@PathVariable(value = "id") Long id) {
        LocalTime tiempo = LocalTime.now();
        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        registroTraido.setEstatus("Muestra a Compras");
        registroTraido.setProgreso("33.33%");
        Calendar calendar = Calendar.getInstance();
        registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
        registroTraido.setDateReciboMuestraM(LocalDate.now());
        registroTraido.setTimeReciboMuestraM(tiempo);
        registroTraido.setArea("Compras");
        return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/recibo-l/{id}")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoReciboL(@PathVariable(value = "id") Long id) {
        LocalTime tiempo = LocalTime.now();
        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        registroTraido.setEstatus("Mercancia Liberada en Recibo");
        registroTraido.setDateReciboL(LocalDate.now());
        registroTraido.setProgreso("49.86%");
        Calendar calendar = Calendar.getInstance();
        registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
        registroTraido.setTimeReciboL(tiempo);
        registroTraido.setArea("Recibo");
        return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.OK);
    }

    @PutMapping("/transito/{id}")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoTransito(@PathVariable(value = "id") Long id, @RequestParam String maquila) {
        LocalTime tiempo = LocalTime.now();

        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        registroTraido.setEstatus("En Transito");
        registroTraido.setDateTransito(LocalDate.now());
        registroTraido.setProgreso("66.52%");
        Calendar calendar = Calendar.getInstance();
        registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
        registroTraido.setTimeTransito(tiempo);
        if (maquila.equals("si")) {
            registroTraido.setVerificadoMaquila(true);
        } else if (maquila.equals("no")) {
            registroTraido.setVerificadoMaquila(false);
        }
        registroTraido.setArea("Almacen");
        return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.OK);
    }


    @PutMapping("/almacen-s/{id}")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoAlmacen(@PathVariable(value = "id") Long id) {
        LocalTime tiempo = LocalTime.now();
        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        registroTraido.setEstatus("Finalizado");
        registroTraido.setProgreso("83.18%");
        Calendar calendar = Calendar.getInstance();
        registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
        registroTraido.setDateAlmacen(LocalDate.now());
        registroTraido.setTimeAlmacen(tiempo);
        registroTraido.setArea("Finalizado");
        return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.OK);
    }


    @PutMapping("/almacen-v/{id}")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoAlmacenVerificado(@PathVariable(value = "id") Long id, @RequestParam String comentarios) {
        LocalTime tiempo = LocalTime.now();
        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        if(registroTraido.getTime_fotos_orden()==null){
            return new ResponseEntity<>(null,HttpStatus.PRECONDITION_FAILED);
        }
        else {
            registroTraido.setEstatus("En Almacen, Liberado para unidades de negocio");
            registroTraido.setDateAlmacenVerificado(LocalDate.now());
            registroTraido.setArea("Finalizado");
            Calendar calendar = Calendar.getInstance();
            registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
            registroTraido.setTimeAlmacenVerificado(tiempo);
            registroTraido.setProgreso("100%");
            registroTraido.setArea("Finalizado");
            if (comentarios == null) {
                registroTraido.setComentariosAlmacen("Sin Comentarios");
            } else {
                registroTraido.setComentariosAlmacen(comentarios);
            }
            return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.OK);
        }
    }


    @PutMapping("/recibo-pedidoEspecial/{id}")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoPedidoEspecial(@PathVariable(value = "id") Long id) {
        LocalTime tiempo = LocalTime.now();
        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        registroTraido.setProgreso("66.66%");
        Calendar calendar = Calendar.getInstance();
        registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
        registroTraido.setEstatus("Mercancia Recibida, Pedido Especial");
        registroTraido.setDateReciboOCLlegada(LocalDate.now());
        registroTraido.setTimeReciboOCLlegada(tiempo);
        registroTraido.setArea("Recibo");
        return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.OK);
    }


    @PutMapping("/almacen-pedidoEspecial/{id}")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoPedidoEspecialLiberado(@PathVariable(value = "id") Long id) {
        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        registroTraido.setProgreso("100%");
        Calendar calendar = Calendar.getInstance();
        registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
        registroTraido.setEstatus("Mercancia Liberada, Pedido Especial");
        registroTraido.setDatePedidoEspecial(LocalDate.now());
        registroTraido.setTimePedidoEspecial(LocalTime.now());
        registroTraido.setArea("Finalizado");
        return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.OK);
    }

    @PutMapping("/foto-liberado")
    public ResponseEntity<RegistroTiempoDTO> generarTiempoFotoOrden(@PathVariable(value = "id") Long id) {
        RegistroTiempoDTO registroTraido = sRegistro.getRegistroById(id);
        if (registroTraido.getDate_fotos_registro()==null) {
            registroTraido.setProgreso("800%");
            Calendar calendar = Calendar.getInstance();
            registroTraido.setNumeroSemana(calendar.get(Calendar.WEEK_OF_YEAR));
            registroTraido.setEstatus("Fotos Tomadas de la orden");
            registroTraido.setDate_fotos_registro(LocalDate.now());
            registroTraido.setTime_fotos_orden(LocalTime.now());
            registroTraido.setArea("Foto");
            return new ResponseEntity<>(sRegistro.updateRegistroTiempo(registroTraido), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_REQUIRED);
        }
    }


}
