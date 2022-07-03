package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.IRolRepository;
import com.unicauca.carrito.domain.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> buscarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Rol guardar(Rol rol) {
        rol.setEstado(true);

        return rolRepository.save(rol);
    }

    @Override
    public Rol encontrarPorId(String id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Rol encontrarPorNombreRol(String nombre) {
        return rolRepository.findByNombreRol(nombre);
    }

    @Override
    public Rol actualizar(Rol rol) {
        Rol rolecito = encontrarPorId(rol.getIdRol());

        if (rolecito ==null){
            return null;
        }
        rolecito.setNombreRol(rol.getNombreRol());
        return  rolRepository.save(rolecito);
    }

    @Override
    public Rol eliminar(String id) {
        Rol rolecito = encontrarPorId(id);
        if(rolecito==null){
            return null;
        }
        rolecito.setEstado(false);
        return rolRepository.save(rolecito);
    }


}
