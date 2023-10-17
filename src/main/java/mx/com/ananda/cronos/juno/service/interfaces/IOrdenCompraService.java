package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;
import mx.com.ananda.cronos.juno.response.OrdenCompraResponse;

import java.util.Optional;

public interface IOrdenCompraService {

    OrdenCompraResponse getAllOrders(int numPage, int sizePage);

    OrdenCompraResponse getAllOrdersByCardName(int numPage, int sizePage, String cardName);

    OrdenCompraResponse getAllOrdersByCardCode(int numPage, int sizePage, String cardCode);

    Optional<OrdenCompraDTO> getOrderById(Long idOrdenCompra);

    Optional<OrdenCompraDTO> getOrderByDocNum(Long docNum);

    Optional<OrdenCompraDTO> assignOrderSAP(Long idOrder, Long docNum);

    void updateOrder(OrdenCompraDTO order);

}
