package mx.com.ananda.cronos.juno.response;

import lombok.Data;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;

import java.util.List;

@Data
public class OrdenCompraResponse {

    private List<OrdenCompraDTO> content;
    private int numPage;
    private int sizePage;
    private long allElements;
    private int allPage;
    private boolean last;
}
