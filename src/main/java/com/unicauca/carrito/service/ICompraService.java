package com.unicauca.carrito.service;

import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Compra;

import java.util.List;

public interface ICompraService {
    List<Compra> buscarTodos();
    Compra guardar(Compra compra);

    Compra actualizar(Compra compra);

    Compra eliminar(String id);
    Compra encontrarPorId(String id);

    void calcularTotal(Compra comp);

}
