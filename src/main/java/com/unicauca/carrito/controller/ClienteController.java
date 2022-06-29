package com.unicauca.carrito.controller;


import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Rol;
import com.unicauca.carrito.service.IClienteService;
import com.unicauca.carrito.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="cliente")
public class ClienteController {

    @Autowired
    IClienteService clienteService;

    @Autowired
    IRolService rolService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente){
        Rol verificarRol = rolService.encontrarPorId(cliente.getRol().getIdRol());
        if(verificarRol == null){
            System.out.println("no existe el Rol ");
            return ResponseEntity.noContent().build();
        }
        cliente.setRol(verificarRol);
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
        Rol verificarRol = rolService.encontrarPorId(cliente.getRol().getIdRol());
        //verificar que el rol exista
        if(verificarRol == null){
            System.out.println("no existe el Rol ");
            return ResponseEntity.noContent().build();
        }
        // verificarque elcliente exista
        cliente.setRol(verificarRol);
        Cliente cli= clienteService.actualizar(cliente);
        if (cli==null){
            System.out.println("no existe cliente con ese id ");
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
