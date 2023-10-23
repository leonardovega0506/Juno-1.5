package mx.com.ananda.cronos.juno.service.implementation;

import mx.com.ananda.cronos.juno.model.dto.http.DetalleCompraDTO;
import mx.com.ananda.cronos.juno.model.entity.DetalleCompraModel;
import mx.com.ananda.cronos.juno.repository.IDetalleCompraRepository;
import mx.com.ananda.cronos.juno.service.interfaces.IDetalleOrdenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Service
public class DetalleOrdenServiceimplTest implements IDetalleOrdenService {

    @Autowired
    private IDetalleCompraRepository iDetalle;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    //Guardar el detalle
    @Override
    public DetalleCompraDTO saveDetalles(DetalleCompraDTO detalleCompraDTO) {
        DetalleCompraModel detalleNuevo = mapearEntidadDTO(detalleCompraDTO);
        DetalleCompraDTO detalleDevuelto = mapearDTOEntidad(iDetalle.save(detalleNuevo));
        return detalleDevuelto;
    }

    @Override
    public List<DetalleCompraDTO> findDetallesByIDOrdenCompra(Long idOrdenCompra) {

        List<DetalleCompraModel> listaDetalles = iDetalle.findAll();
        List<DetalleCompraDTO> listaDetalleDTO = listaDetalles
                .stream()
                .filter(detalle -> detalle.getOrden().getIdOrdenCompra().equals(idOrdenCompra))
                .map(detalle->mapearDTOEntidad(detalle)).collect(Collectors.toList());
        return listaDetalleDTO;
    }

    protected DetalleCompraDTO mapearDTOEntidad(DetalleCompraModel detalleCompraModel){
        DetalleCompraDTO detalleCompraDTO = modelMapper.map(detalleCompraModel,DetalleCompraDTO.class);
        return detalleCompraDTO;
    }

    protected  DetalleCompraModel mapearEntidadDTO(DetalleCompraDTO detalleCompraDTO){
        DetalleCompraModel detalleCompraModel = modelMapper.map(detalleCompraDTO,DetalleCompraModel.class);
        return detalleCompraModel;
    }
}