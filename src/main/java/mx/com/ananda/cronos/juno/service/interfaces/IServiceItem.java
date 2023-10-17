package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.ItemDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.response.ItemFotoResponse;

import java.util.Optional;

public interface IServiceItem {

    ItemDTO getAllItems(int numPage, int sizePage);

    ItemDTO saveItem(ItemDTO itemDTO);

    Optional<ItemDTO> getItemById(Long idItem);

    Optional<ItemDTO> getItemByItemcode(String itemCode);

    void updateItem(ItemDTO itemDTO);
}
