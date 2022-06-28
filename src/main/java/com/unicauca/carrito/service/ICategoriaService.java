package com.unicauca.carrito.service;

import com.unicauca.carrito.domain.model.Categoria;

import java.util.List;

public interface ICategoriaService {

    List<Categoria> buscarTodos();
    Categoria guardar(Categoria categoria);

    Categoria actualizar(Categoria categoria);

    Categoria eliminar(String id);
    Categoria encontrarPorId(String id);
}
