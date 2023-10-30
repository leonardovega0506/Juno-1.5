package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.DetalleCompraDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;
import mx.com.ananda.cronos.juno.response.OrdenCompraResponse;

import java.util.List;
import java.util.Optional;

public interface IOrdenCompraService {

    OrdenCompraResponse getAllOrders(int numPage, int sizePage, String orderBy, String sortDir);

    OrdenCompraResponse getAllOrdersByCardName(int numPage, int sizePage, String cardName);

    OrdenCompraResponse getAllOrdersByCardCode(int numPage, int sizePage, String cardCode);

    OrdenCompraDTO getOrderById(Long idOrdenCompra);

    OrdenCompraDTO getOrderByDocNum(Long docNum);

    OrdenCompraDTO assignOrderSAP(Long idOrder, Long docNum);

    List<DetalleCompraDTO> getDetailsOrder(Long idOrder);

    List<ItemFotoDTO> getDetailsFoto(Long idOrder);

}
