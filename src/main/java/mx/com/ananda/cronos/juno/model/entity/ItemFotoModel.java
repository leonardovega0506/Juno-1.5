package mx.com.ananda.cronos.juno.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "item_foto")
public class ItemFotoModel {

    @Id
    private Long idFoto;

    private String itemCode;

    private String itemName;

    private LocalDate dateFoto;

    private LocalTime timeFoto;

    private boolean fotoFomada;

    @ManyToOne
    private OrdenCompraModel orden;

}
