package mx.com.ananda.cronos.juno.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "item_foto")
public class ItemFotoModel {

    @Id
    private Long idFoto;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "date_foto")
    private LocalDate dateFoto;

    @Column(name = "time_foto")
    private LocalTime timeFoto;

    @Column(name = "foto_tomada")
    private boolean fotoFomada;

    @ManyToOne
    private OrdenCompraModel orden;

}
