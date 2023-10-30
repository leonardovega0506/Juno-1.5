package mx.com.ananda.cronos.juno.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orden_compra")
public class OrdenCompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Long idOrdenCompra;

    @Column(name = "numero_orden")
    private Long docNum;

    @Column(name = "date_orden")
    private String docDate;

    @Column(name = "orden_nota")
    private String notaRemision;

    @Column(name = "total_Sys")
    private double docTotalSys;

    @Column(name = "orden_sumai")
    private double vatSum;

    @Column(name = "orden_total")
    private Double docTotal;

    @Column(name = "orden_nombre_p")
    private String cardName;

    @Column(name = "orden_codigo_p")
    private String cardCode;

    @Column(name = "orden_cuadrante_p")
    private String cuadrante;

    @OneToMany(mappedBy = "orden")
    private List<DetalleCompraModel> detallesOrdenes;


    @OneToMany(mappedBy = "orden")
    private List<ItemFotoModel> fotoOrdenes;

    @OneToMany(mappedBy = "orden")
    private List<RegistroTiempoModel> registros;

}
