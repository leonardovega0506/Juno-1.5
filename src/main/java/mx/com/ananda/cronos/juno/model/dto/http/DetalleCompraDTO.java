package mx.com.ananda.cronos.juno.model.dto.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import mx.com.ananda.cronos.juno.model.entity.ItemModel;
import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;
import org.springframework.lang.Nullable;

import javax.persistence.ManyToOne;


@Data
public class DetalleCompraDTO {

    private Long idDetalleOrden;
    private String itemCode;
    private String itemDescription;
    private double cantidad;
    private double precio;
    private String resurtido;
    private OrdenCompraModel orden;
    private ItemModel itemModel;
}
