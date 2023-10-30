package mx.com.ananda.cronos.juno.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.response.ItemFotoResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IFotoItemService;
import mx.com.ananda.cronos.juno.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("ananda/cronos1_3/juno1_5/foto")
public class ItemFotoController {

    @Autowired
    private IFotoItemService sFoto;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO')")
    @GetMapping("")
    public ResponseEntity<ItemFotoResponse> traertemsFotos(
            @RequestParam(value = "pageNO",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value= "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = GlobalConstants.ORDENAR_DEFECTO) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sFoto.getAllItems(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO')")
    @GetMapping("/{id}")
    public ResponseEntity<ItemFotoDTO> traerItemFotoById(@PathVariable(value = "id") Long idFoto){
        return new ResponseEntity<>(sFoto.getItemById(idFoto),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO')")
    @GetMapping("/foto")
    public ResponseEntity<ItemFotoDTO> traerItemFotoByItemCode(@RequestParam(value = "itemCode") String itemCode){
        return new ResponseEntity<>(sFoto.getItemByItemCode(itemCode),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO')")
    @PostMapping("")
    public ResponseEntity<ItemFotoDTO> guardarFoto(@RequestBody ItemFotoDTO itemFotoDTO){
        return new ResponseEntity<>(sFoto.saveItem(itemFotoDTO),HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO')")
    @PutMapping("")
    public ResponseEntity<HttpStatus> actualizarFoto(@RequestBody ItemFotoDTO itemFotoDTO){
        sFoto.updateItemFoto(itemFotoDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
