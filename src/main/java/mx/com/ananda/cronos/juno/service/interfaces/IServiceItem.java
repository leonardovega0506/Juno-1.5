package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.ItemDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.response.ItemFotoResponse;
import mx.com.ananda.cronos.juno.response.ItemResponse;

import java.util.Optional;

public interface IServiceItem {

    ItemResponse getAllItems(int numPage, int sizePage, String orderBy, String sortDir);

    ItemDTO saveItem(ItemDTO itemDTO);

    ItemDTO getItemById(Long idItem);

    ItemDTO getItemByItemcode(String itemCode);

    void updateItem(ItemDTO itemDTO);
}
