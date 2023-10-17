package mx.com.ananda.cronos.juno.response;

import lombok.Data;
import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;
import mx.com.ananda.cronos.juno.model.dto.http.RegistroTiempoDTO;

import java.util.List;

@Data
public class RegistroTiempoResponse {

    private List<RegistroTiempoDTO> content;
    private int numPage;
    private int sizePage;
    private long allElements;
    private int allPage;
    private boolean last;
}
