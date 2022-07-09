package com.unicauca.carrito.controller;

import com.unicauca.carrito.domain.model.Compra;
import com.unicauca.carrito.domain.model.CompraProducto;
import com.unicauca.carrito.domain.model.Producto;
import com.unicauca.carrito.seguridad.JWTUtil;
import com.unicauca.carrito.service.IClienteService;
import com.unicauca.carrito.service.ICompraProductoService;
import com.unicauca.carrito.service.ICompraService;
import com.unicauca.carrito.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/compraProducto")
public class CompraProductoController {
    @Autowired
    ICompraProductoService compraProductoService;

    @Autowired
    JWTUtil jwtUtil;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create")

    public ResponseEntity<CompraProducto> addCompraProducto(@RequestBody CompraProducto compraProducto){


        CompraProducto newCP= compraProductoService.guardar(compraProducto);
        if(newCP == null){
            return ResponseEntity.noContent().build();
        }
        compraProductoService.totalEnCompra(newCP);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCP);
    }

    @GetMapping(value="/allProducts/{idCompra}")
    public ResponseEntity<?> getAllProductsFromCompra(@PathVariable("idCompra") String idCompra){
        List<CompraProducto> compraProductos = compraProductoService.buscarProductosDeCompra(idCompra);
        if (compraProductos.size()==0){
            return ResponseEntity.noContent().build();
        }
        compraProductos.removeIf(cat-> cat.getEstado()==false);
        return new ResponseEntity<>(compraProductos,HttpStatus.OK);
    }
    @GetMapping(value="/all")
    public ResponseEntity<?> getAllCompraProductos(){
        List<CompraProducto> compraProductos = compraProductoService.buscarTodos();

        if (compraProductos.size()==0){
            return ResponseEntity.noContent().build();
        }
        System.out.println("Productos:" + compraProductos.toString());
        compraProductos.removeIf(cat-> cat.getEstado()==false);
        return new ResponseEntity<>(compraProductos,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<CompraProducto> getCategoryById(@PathVariable("id") String id){
        CompraProducto cat = compraProductoService.encontrarPorId(id);

        if (cat==null){
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cat);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestHeader(value = "Authorization") String token,@RequestBody CompraProducto compraProducto ){
        token= token.replace("Bearer ","");
        System.out.println("TOKEN "+token);
        String cliente = jwtUtil.extractUsername(token);
        System.out.println("El cliente que esta actualizando CompraProducto es :"+cliente);

        CompraProducto newCT = compraProductoService.actualizar(compraProducto,cliente);
        if (newCT ==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        compraProductoService.totalEnCompra(newCT);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCT);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value="/delete/{idCompraProducto}")
    public ResponseEntity<?> delete(@PathVariable("idCompraProducto") String id ){
        CompraProducto cat = compraProductoService.eliminar(id);
        if (cat ==null){
            return ResponseEntity.noContent().build();
        }
        compraProductoService.totalEnCompra(cat);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cat);
    }
}
