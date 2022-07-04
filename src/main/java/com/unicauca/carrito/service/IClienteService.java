package com.unicauca.carrito.service;

import com.unicauca.carrito.domain.model.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> buscarTodos();
    Cliente guardar(Cliente cliente);

    Cliente actualizar(Cliente cliente);

    Cliente eliminar(String id);
    Cliente encontrarPorId(String id);

    Cliente encontrarPorUsername(String username);
}
