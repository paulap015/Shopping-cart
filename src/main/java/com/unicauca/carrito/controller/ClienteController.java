package com.unicauca.carrito.controller;


import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="cliente")
public class ClienteController {

    @Autowired
    IClienteService clienteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente){

        Cliente newcli= clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newcli);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllClientes(){
        List<Cliente> clientes = clienteService.buscarTodos();

        if (clientes.size()==0){
            return ResponseEntity.noContent().build();
        }
        clientes.removeIf(cli-> cli.getEstado()==false);
        return new ResponseEntity<>(clientes,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<Cliente> getCategoryById(@PathVariable("id") String id){
        Cliente cli= clienteService.encontrarPorId(id);

        if (cli==null){
            System.out.println("no existe el cliente con ese id ");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cli);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody Cliente cliente ){

        Cliente cli= clienteService.actualizar(cliente);
        if (cli==null){

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cli);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value="/delete/{idCliente}")
    public ResponseEntity<?> delete(@PathVariable("idCliente") String id ){
        Cliente cli= clienteService.eliminar(id);
        if (cli==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cli);
    }
}
