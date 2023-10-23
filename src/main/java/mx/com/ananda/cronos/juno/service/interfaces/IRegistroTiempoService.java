package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.RegistroTiempoDTO;
import mx.com.ananda.cronos.juno.response.RegistroTiempoResponse;

import java.util.Optional;

public interface IRegistroTiempoService {

    RegistroTiempoResponse getAllRegistros(int numeroPagina,int sizePagina, String orderBy,String sortDir,String area);

    RegistroTiempoResponse getAllRegistroByCardName(int numeroPagina, int sizePagina, String area,String orderBy, String sortDir, String cardName);

    RegistroTiempoDTO saveRegistroTiempo(RegistroTiempoDTO registroTiempoDTO, String TipoPedido);

    RegistroTiempoDTO updateRegistroTiempo(RegistroTiempoDTO registroTiempoDTO);

    RegistroTiempoDTO getRegistroById(Long idRegistro);

    void deleteRegistroById(Long idRegistro);
}
