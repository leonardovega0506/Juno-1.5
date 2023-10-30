package mx.com.ananda.cronos.juno.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.model.dto.http.ItemDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.response.ItemResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IServiceItem;
import mx.com.ananda.cronos.juno.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("ananda/cronos1_3/juno1_5/item")
public class ItemController {

    @Autowired
    private IServiceItem sItem;

    @GetMapping("")
    public ResponseEntity<ItemResponse> traeItems(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir
    ) {
        return new ResponseEntity<>(sItem.getAllItems(pageNumber, pageSize, orderBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> traerItemById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(sItem.getItemById(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPRAS') OR hasRole('FOTO')")
    @GetMapping("/itemCode")
    public ResponseEntity<ItemDTO> traerItemByItemCode(@RequestParam(value = "itemCode") String itemCode){
        return new ResponseEntity<>(sItem.getItemByItemcode(itemCode),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPRAS')")
    @PostMapping("")
    public ResponseEntity<ItemDTO> guardarNuevoItem(@RequestBody ItemDTO itemDTO){
        return new ResponseEntity<>(sItem.saveItem(itemDTO),HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPRAS')")
    @PutMapping()
    public ResponseEntity<HttpStatus> actualizarItem(@RequestBody ItemDTO itemDTO){
        sItem.updateItem(itemDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
