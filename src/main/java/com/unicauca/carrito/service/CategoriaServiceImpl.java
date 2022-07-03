package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.ICategoriaRepository;
import com.unicauca.carrito.domain.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaServiceImpl implements ICategoriaService{
    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        categoria.setEstado(true);
        return categoriaRepository.save(categoria);
    }
    @Override
    public Categoria encontrarPorId(String id) {

        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria actualizar(Categoria categoria) {
        Categoria catego = encontrarPorId(categoria.getIdCategoria());

        //Categoria catego = categoriaRepository.findById(categoria.getIdCategoria()).get();
        if (catego == null){
            return null;
        }

        catego.setDescripcion(categoria.getDescripcion());
        catego.setEstado(categoria.getEstado());
        System.out.println("aunto de guardar actualizar "+catego.getIdCategoria());
        return categoriaRepository.save(catego);
    }

    @Override
    public Categoria eliminar(String id) {
        Categoria cat = encontrarPorId(id);
        if (cat == null){
            return null;
        }

        cat.setEstado(false);
        return categoriaRepository.save(cat);
    }


}
