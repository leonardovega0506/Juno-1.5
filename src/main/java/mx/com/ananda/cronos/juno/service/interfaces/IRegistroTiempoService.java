package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.RegistroTiempoDTO;
import mx.com.ananda.cronos.juno.response.RegistroTiempoResponse;

import java.util.Optional;

public interface IRegistroTiempoService {

    RegistroTiempoResponse getAllRegistros(int numeroPagina,int sizePagina, String area);

    RegistroTiempoResponse getAllRegistroByCardName(int numeroPagina, int sizePagina, String area, String cardName);

    RegistroTiempoDTO saveRegistroTiempo(RegistroTiempoDTO registroTiempoDTO);

    Optional<RegistroTiempoDTO> updateRegistroTiempo(RegistroTiempoDTO registroTiempoDTO);

    Optional<RegistroTiempoDTO> getRegistroById(Long idRegistro);

    void deleteRegistroById(Long idRegistro);
}
