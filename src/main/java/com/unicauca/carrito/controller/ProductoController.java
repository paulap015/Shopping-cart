package com.unicauca.carrito.controller;

import com.unicauca.carrito.domain.model.Categoria;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Producto;
import com.unicauca.carrito.service.ICategoriaService;
import com.unicauca.carrito.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    IProductoService productoService;
    @Autowired
    ICategoriaService categoriaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create")
    public ResponseEntity<Producto> addProducto(@RequestBody Producto producto){
        //verificar que exista la categoria para asignarlo a producto

        Categoria verificarCategoria = categoriaService.encontrarPorId(producto.getCategoria().getIdCategoria());

        if(verificarCategoria == null){
            System.out.println("La categoria no existe ");
            return ResponseEntity.noContent().build();
        }
        producto.setCategoria(verificarCategoria);
        Producto newComp= productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newComp);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllProductos(){
        List<Producto> productos = productoService.buscarTodos();

        if (productos.size()==0){
            return ResponseEntity.noContent().build();
        }
        productos.removeIf(comp-> comp.getEstado()==false);
        return new ResponseEntity<>(productos,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<Producto> getCategoryById(@PathVariable("id") String id){
        Producto comp = productoService.encontrarPorId(id);

        if (comp==null){
            System.out.println("no existe el producto con ese id");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comp);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody Producto producto ){

        Categoria verificarCategoria = categoriaService.encontrarPorId(producto.getCategoria().getIdCategoria());

        if(verificarCategoria == null){
            System.out.println("La categoria no existe ");
            return ResponseEntity.noContent().build();
        }
        producto.setCategoria(verificarCategoria);
        Producto prod= productoService.actualizar(producto);
        if (prod==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value="/delete/{idProducto}")
    public ResponseEntity<?> delete(@PathVariable("idProducto") String id ){
        Producto comp = productoService.eliminar(id);
        if (comp ==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comp);
    }
}
