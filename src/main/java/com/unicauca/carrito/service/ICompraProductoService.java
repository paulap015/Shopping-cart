package com.unicauca.carrito.service;

import com.unicauca.carrito.domain.model.CompraProducto;

import java.util.List;

public interface ICompraProductoService {
    List<CompraProducto> buscarTodos();
    CompraProducto guardar(CompraProducto compraProducto);

    CompraProducto actualizar(CompraProducto compraProducto,String username);

    CompraProducto eliminar(String id);
    CompraProducto encontrarPorId(String id);

    List<CompraProducto> buscarProductosDeCompra(String idCompra);

    void totalEnCompra(CompraProducto cp);
}
