package mx.com.ananda.cronos.juno.response;

import lombok.Data;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;

import java.util.List;

@Data
public class ItemFotoResponse {

    private List<ItemFotoDTO> content;
    private int numPage;
    private int sizePage;
    private long allElements;
    private int allPage;
    private boolean last;
}
