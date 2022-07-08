package com.unicauca.carrito.service;


import com.unicauca.carrito.domain.model.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> buscarTodos();
    Producto guardar(Producto producto);
    Producto actualizar(Producto producto);
    Producto eliminar(String id);
    Producto encontrarPorId(String id);

    boolean estaDisponible(String id);
    void reducirStock(String idProducto,int cantidad);

    void aumentarStock(String idProducto,int cantidad);
}
