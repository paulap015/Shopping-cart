package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.ICompraRepository;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements  ICompraService {

    @Autowired
    private ICompraRepository compraRepository;


    @Override
    public List<Compra> buscarTodos() {
        return compraRepository.findAll();
    }

    @Override
    public Compra guardar(Compra compra) {
        return compraRepository.save(compra);
    }
    @Override
    public Compra encontrarPorId(String id) {

        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public Compra actualizar(Compra compra) {
        Compra comp = encontrarPorId(compra.getIdCompra());

        
        if (comp == null){
            return null;
        }

        comp.setComentario(compra.getComentario());
        comp.setEstado(compra.getEstado());
        //System.out.println("aunto de guardar actualizar "+comp.getIdCompra());
        return compraRepository.save(comp);
    }

    @Override
    public Compra eliminar(String id) {
        Compra comp = encontrarPorId(id);
        if (comp == null){
            return null;
        }

        comp.setEstado(false);
        return compraRepository.save(comp);
    }
}