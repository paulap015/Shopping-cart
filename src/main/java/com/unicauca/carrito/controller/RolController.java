package com.unicauca.carrito.controller;


import com.unicauca.carrito.domain.model.Rol;
import com.unicauca.carrito.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/rol")
public class RolController {

    @Autowired
    IRolService rolService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create")
    public ResponseEntity<Rol> addRol(@RequestBody Rol rol){
        Rol newRol = rolService.guardar(rol);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newRol);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllRoles(){
        List<Rol> roles = rolService.buscarTodos();

        if (roles.size()==0){
            return ResponseEntity.noContent().build();
        }
        roles.removeIf(rol-> rol.getEstado()==false);
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable("id") String id){
        Rol rol = rolService.encontrarPorId(id);

        if (rol==null){
            return ResponseEntity.ok(Rol.builder().build());
        }
        return ResponseEntity.ok(rol);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody Rol rol ){

        Rol newRol = rolService.actualizar(rol);

        if (newRol ==null){
            System.out.println("no paso");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        System.out.println("si paso");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newRol);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value="/delete/{idRol}")
    public ResponseEntity<?> delete(@PathVariable("idRol") String id ){
        Rol rol = rolService.eliminar(id);
        if (rol ==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rol);
    }
}
