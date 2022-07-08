package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.IProductoRepository;
import com.unicauca.carrito.domain.model.Categoria;
import com.unicauca.carrito.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    ICategoriaService categoriaService;
    @Override
    public List<Producto> buscarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        //verificar que exista la categoria para asignarlo a producto
        Categoria verificarCategoria = categoriaService.encontrarPorId(producto.getCategoria().getIdCategoria());

        if(verificarCategoria == null){
            System.out.println("La categoria no existe ");
            return null;
        }
        producto.setCategoria(verificarCategoria);
        producto.setEstado(true);
        return productoRepository.save(producto);
    }

    @Override
    public Producto encontrarPorId(String id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean estaDisponible(String id) {
        Producto newProducto=encontrarPorId(id);
        if(newProducto.getEstado() ==true){
            return true;
        }
        return false;
    }

    @Override
    public void reducirStock(String idProducto,int cantidad) {
        //llega el id de producto y cantidad a reducir
        Producto newProducto=encontrarPorId(idProducto);
        //disminuimos la cantidad
        newProducto.setCantidadStock(newProducto.getCantidadStock()-cantidad);
        if(newProducto.getCantidadStock()==0){ //si queda en 0 entonces desactivarlo
            newProducto.setEstado(false);
        }
        //actualizamos el producto
        actualizar(newProducto);
    }

    @Override
    public void aumentarStock(String idProducto, int cantidad) {
        //llega el id de producto y cantidad a reducir
        Producto newProducto=encontrarPorId(idProducto);
        //disminuimos la cantidad
        newProducto.setCantidadStock(newProducto.getCantidadStock()+cantidad);
        if(newProducto.getCantidadStock()==0){ //si queda en 0 entonces desactivarlo
            newProducto.setEstado(false);
        }
        //actualizamos el producto
        actualizar(newProducto);
    }

    @Override
    public Producto actualizar(Producto producto) {
        Categoria verificarCategoria = categoriaService.encontrarPorId(producto.getCategoria().getIdCategoria());

        if(verificarCategoria == null){
            System.out.println("La categoria no existe ");
            return null;
        }
        producto.setCategoria(verificarCategoria);
        Producto newProducto = encontrarPorId(producto.getIdProducto());

        if (newProducto ==null){
            return null;
        }
        if(estaDisponible(newProducto.getIdProducto())==false && newProducto.getCantidadStock()==0 &&producto.getCantidadStock()>0){
            newProducto.setEstado(true); //vuelve a haber cantidad en stock
        }else{
            newProducto.setEstado(producto.getEstado());
        }
        // actualizar
        newProducto.setNombre(producto.getNombre());
        newProducto.setPrecio(producto.getPrecio());
        newProducto.setCantidadStock(producto.getCantidadStock());
        newProducto.setCategoria(producto.getCategoria());
        newProducto.setDescripcion(producto.getDescripcion());
        if(producto.getFoto()!=null){
            newProducto.setFoto(producto.getFoto());
        }

        //newProducto.setEstado(producto.getEstado());

        return  productoRepository.save(newProducto);
    }

    @Override
    public Producto eliminar(String id) {
        Producto newProducto = encontrarPorId(id);
        if(newProducto==null){
            return null;
        }
        newProducto.setEstado(false);
        return productoRepository.save(newProducto);
    }
}
