package mx.com.ananda.cronos.juno.model.dto.sap;

import lombok.Data;

@Data
public class DocumentLines {
    //Codigo del producto
    private String itemCode;

    //Nombre del producto
    private String itemDescription;

    //Cantidad del producto
    private double quantity;

    //Precio del producto
    private double price;


    public DocumentLines() {
    }

    public DocumentLines(String itemCode, String itemDescription, double quantity, double price) {
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.price = price;
    }
}
