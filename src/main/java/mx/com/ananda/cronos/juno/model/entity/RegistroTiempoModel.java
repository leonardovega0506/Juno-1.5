package mx.com.ananda.cronos.juno.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroTiempoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro",nullable = false)
    private Long idRegistro;

    //Fecha recibo llegada
    @Column(name = "date_ReciboOC_Llegada",nullable = true)
    private LocalDate dateReciboOCLlegada;

    //Hora recibo Llegada
    @Column(name = "time_ReciboOC_Llegada",nullable = true)
    private LocalTime timeReciboOCLlegada;

    //Fecha recibo Muestra mandada
    @Column(name = "date_Recibo_MuestraM",nullable = true)
    private LocalDate dateReciboMuestraM;

    //Hora recibo muestra mandada
    @Column(name = "time_Recibo_MuestraM",nullable = true)
    private LocalTime timeReciboMuestraM;

    //Fecha compra liberada
    @Column(name = "date_ReciboL",nullable = true)
    private LocalDate dateReciboL;

    //Hora compra liberado
    @Column(name = "time_ReciboL",nullable = true)
    private LocalTime timeReciboL;

    //Fecha recibo mandar a transito
    @Column(name = "date_Transito",nullable = true)
    private LocalDate dateTransito;

    //Hora recibo mandar a transito
    @Column(name = "time_Transito",nullable = true)
    private LocalTime timeTransito;

    //Fecha asignar almacen
    @Column(name = "date_Almacen",nullable = true)
    private LocalDate dateAlmacen;

    //Hora asignar almacen
    @Column(name = "time_Almacen",nullable = true)
    private LocalTime timeAlmacen;

    //Fecha verificar producto
    @Column(name = "date_AlmacenV",nullable = true)
    private LocalDate dateAlmacenVerificado;

    //Hora verificar almacen
    @Column(name = "time_AlmacenV",nullable = true)
    private LocalTime timeAlmacenVerificado;

    //Fecha registro pedido especial
    @Column(name = "date_ReciboEspecial")
    private LocalDate datePedidoEspecial;

    //Hora registro pedido especial
    @Column(name = "time_ReciboEspecial")
    private LocalTime timePedidoEspecial;

    @Column(name = "date_fotos_registro")
    private LocalDate date_fotos_registro;

    @Column(name = "time_fotos_registro")
    private LocalTime time_fotos_orden;

    //Area donde se encuentra el registro
    @Column(name = "area")
    private String area;

    //Tipo de pedido
    @Column(name = "cuadrante")
    private String cuadrante;

    //Identificador
    @Column(name = "proveedor_recibo")
    private String proveedor;

    @Column(name = "progreso_registro")
    private String progreso;

    //Estatus del registro
    @Column(name = "estatus",nullable = false)
    private String estatus;

    //Requirio maquila
    @Column(name = "verficado_maquila",nullable = true)
    private boolean verificadoMaquila;

    //Generar comentarios
    @Column(name = "comentario_almacen",nullable = true)
    private String comentariosAlmacen;

    //Busca el numero de semana
    @Column(name = "numero_semana")
    private int numeroSemana;

    //Genera el numero del complemento de la orden
    @Column(name = "complemento")
    private int complementoOrden;

    //Asociacion con orden de compra
    @ManyToOne
    private OrdenCompraModel ordenCompra;


}
