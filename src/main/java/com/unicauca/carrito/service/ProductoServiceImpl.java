package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.IProductoRepository;
import com.unicauca.carrito.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> buscarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {

        return productoRepository.save(producto);
    }

    @Override
    public Producto encontrarPorId(String id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto actualizar(Producto producto) {
        Producto newProducto = encontrarPorId(producto.getIdProducto());

        if (newProducto ==null){
            return null;
        }

        // actualizar
        newProducto.setNombre(producto.getNombre());
        newProducto.setPrecio(producto.getPrecio());
        newProducto.setCantidadStock(producto.getCantidadStock());
        newProducto.setCategoria(producto.getCategoria());
        newProducto.setEstado(producto.getEstado());

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