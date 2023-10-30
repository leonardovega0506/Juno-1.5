package mx.com.ananda.cronos.juno.model.dto.sap;

import lombok.Data;

import java.util.List;

@Data
public class OrdenCompraSAP {


    private Long docNum;
    private String docDate;
    private double docTotalSys;
    private double vatSum;
    private Double docTotal;
    private String cardName;
    private String cardCode;
    private List<DocumentLines> documentLines;
}
