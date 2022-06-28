package com.unicauca.carrito.controller;

import com.unicauca.carrito.dao.IClienteRepository;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Compra;
import com.unicauca.carrito.service.IClienteService;
import com.unicauca.carrito.service.ICompraService;
import com.unicauca.carrito.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    ICompraService compraService;

    @Autowired
    IClienteService clienteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create")
    public ResponseEntity<Compra> addCompra(@RequestBody Compra compra){

        Cliente verificarCliente = clienteService.encontrarPorId(compra.getCliente().getIdCliente());
        if(verificarCliente == null){
            System.out.println("El cliente no existe ");
            return ResponseEntity.noContent().build();
        }
        compra.setCliente(verificarCliente);
        Compra newComp= compraService.guardar(compra);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newComp);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllCompras(){
        List<Compra> compras = compraService.buscarTodos();

        if (compras.size()==0){
            return ResponseEntity.noContent().build();
        }
        compras.removeIf(comp-> comp.getEstado()==false);
        return new ResponseEntity<>(compras,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<Compra> getCategoryById(@PathVariable("id") String id){
        Compra comp = compraService.encontrarPorId(id);

        if (comp==null){
            return ResponseEntity.ok(Compra.builder().build());
        }
        return ResponseEntity.ok(comp);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody Compra compra ){

        Compra comp= compraService.actualizar(compra);

        if (comp==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comp);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value="/delete/{idCompra}")
    public ResponseEntity<?> delete(@PathVariable("idCompra") String id ){
        Compra comp = compraService.eliminar(id);
        if (comp ==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comp);
    }
}
