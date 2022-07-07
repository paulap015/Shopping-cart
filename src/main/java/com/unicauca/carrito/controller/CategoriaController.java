package com.unicauca.carrito.controller;

import com.unicauca.carrito.domain.model.Categoria;
import com.unicauca.carrito.seguridad.JWTUtil;
import com.unicauca.carrito.service.CategoriaServiceImpl;
import com.unicauca.carrito.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    ICategoriaService categoriaService;
    @Autowired
    JWTUtil jwtUtil;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria){

        Categoria newCat= categoriaService.guardar(categoria);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCat);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllCategorias(){
        List<Categoria> categorias = categoriaService.buscarTodos();

        if (categorias.size()==0){
            return ResponseEntity.noContent().build();
        }
        categorias.removeIf(cat-> cat.getEstado()==false);
        return new ResponseEntity<>(categorias,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<Categoria> getCategoryById(@PathVariable("id") String id){
        Categoria cat = categoriaService.encontrarPorId(id);

        if (cat==null){
            return ResponseEntity.ok(Categoria.builder().build());
        }
        return ResponseEntity.ok(cat);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody Categoria categoria ){

        Categoria cat = categoriaService.actualizar(categoria);

        if (cat ==null){
            System.out.println("no paso");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        System.out.println("si paso");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cat);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value="/delete/{idCategoria}")
    public ResponseEntity<?> delete(@PathVariable("idCategoria") String id ){
        Categoria cat = categoriaService.eliminar(id);
        if (cat ==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cat);
    }


}
