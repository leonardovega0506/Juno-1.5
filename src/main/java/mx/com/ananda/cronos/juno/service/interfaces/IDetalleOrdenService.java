package mx.com.ananda.cronos.juno.service.interfaces;

import mx.com.ananda.cronos.juno.model.dto.http.DetalleCompraDTO;
import mx.com.ananda.cronos.juno.model.entity.DetalleCompraModel;

import java.util.List;

public interface IDetalleOrdenService {


    DetalleCompraDTO saveDetalles(DetalleCompraModel detalleCompraModel);

    List<DetalleCompraDTO> findDetallesByIDOrdenCompra(Long idOrdenCompra);

}
