package mx.com.ananda.cronos.juno.service.implementation;

import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.model.entity.ItemFotoModel;
import mx.com.ananda.cronos.juno.response.ItemFotoResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IFotoItemService;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ItemFotoServiceImplTest {


    @Test
    public void trajo_erroneamente_la_lista(){
        IFotoItemService iFoto = Mockito.mock(IFotoItemService.class);
        Mockito.when(iFoto.getAllItems(0,10,"id","asc")).thenReturn(new ItemFotoResponse());
        ItemFotoResponse fotoResponse = new ItemFotoResponse();
        boolean result = (fotoResponse.getContent()==null);
        assertTrue(result);
    }

}