package mx.com.ananda.cronos.juno.model.dto.http;

import lombok.Data;
import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ItemFotoDTO {

    private Long idFoto;
    private String itemCode;
    private String itemName;
    private LocalDate dateFoto;
    private LocalTime timeFoto;
    private boolean fotoFomada;
    private OrdenCompraModel orden;
}
