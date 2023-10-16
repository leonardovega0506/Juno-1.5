package mx.com.ananda.cronos.juno.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "detalle_orden")
public class DetalleCompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalleOrden;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "item_name")
    private String itemDescription;

    @Column(name = "cantidad")
    private double cantidad;

    @Column(name = "precio")
    private double precio;

    @Column(name = "resurtido")
    private String resurtido;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private OrdenCompraModel orden;

    @JsonIgnore
    @ManyToOne
    @Nullable
    private ItemModel itemModel;

}
