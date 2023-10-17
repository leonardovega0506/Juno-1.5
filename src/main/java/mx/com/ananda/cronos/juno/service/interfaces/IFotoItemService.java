package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.response.ItemFotoResponse;

import java.util.Optional;

public interface IFotoItemService {

    ItemFotoResponse getAllItems(int numPage, int sizePage, String orderBy, String sortDir);

    ItemFotoDTO saveItem(ItemFotoDTO itemFotoDTO);

    ItemFotoDTO getItemById(Long idItemF);

    ItemFotoDTO getItemByItemCode(String itemCodeF);

    void updateItemFoto(ItemFotoDTO itemFotoDTO);

}
