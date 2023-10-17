package mx.com.ananda.cronos.juno.model.dto.http;

import lombok.Data;


@Data
public class OrdenCompraDTO {

    private Long idOrdenCompra;
    private Long docNum;
    private String docDate;
    private String nota_remision;
    private double docTotalSys;
    private double vatSum;
    private Double docTotal;
    private String cardName;
    private String cardCode;
    private String cuadrante;
}
