package com.unicauca.carrito.service;


import com.unicauca.carrito.domain.model.Rol;

import java.util.List;

public interface IRolService {

    List<Rol> buscarTodos();
    Rol guardar(Rol rol);
    Rol actualizar(Rol rol);
    Rol eliminar(String id);
    Rol encontrarPorId(String id);

    Rol encontrarPorNombreRol(String nombre);
}
