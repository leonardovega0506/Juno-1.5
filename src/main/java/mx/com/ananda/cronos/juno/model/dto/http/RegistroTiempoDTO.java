package mx.com.ananda.cronos.juno.model.dto.http;

import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroTiempoDTO {

    private Long idRegistro;
    private LocalDate dateReciboOCLlegada;
    private LocalTime timeReciboOCLlegada;
    private LocalDate dateReciboMuestraM;
    private LocalTime timeReciboMuestraM;
    private LocalDate dateReciboL;
    private LocalTime timeReciboL;
    private LocalDate dateTransito;
    private LocalTime timeTransito;
    private LocalDate dateAlmacen;
    private LocalTime timeAlmacen;
    private LocalDate dateAlmacenVerificado;
    private LocalTime timeAlmacenVerificado;
    private LocalDate datePedidoEspecial;
    private LocalTime timePedidoEspecial;
    private LocalDate date_fotos_registro;
    private LocalTime time_fotos_orden;
    private String area;
    private String cuadrante;
    private String proveedor;
    private String progreso;
    private String estatus;
    private boolean verificadoMaquila;
    private String comentariosAlmacen;
    private int numeroSemana;
    private int complementoOrden;
    private OrdenCompraModel ordenCompra;

}
