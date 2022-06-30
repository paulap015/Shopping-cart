package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.ICompraProductoRepository;
import com.unicauca.carrito.domain.model.CompraProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraProductoServiceImpl implements ICompraProductoService{
    
    @Autowired
    ICompraProductoRepository compraProductoRepository;

    @Override
    public List<CompraProducto> buscarTodos() {
        return compraProductoRepository.findAll();
    }

    @Override
    public CompraProducto guardar(CompraProducto compraProducto) {
        return compraProductoRepository.save(compraProducto);
    }
    @Override
    public CompraProducto encontrarPorId(String id) {

        return compraProductoRepository.findById(id).orElse(null);
    }

    @Override
    public CompraProducto actualizar(CompraProducto compraProducto) {

        CompraProducto comp = encontrarPorId(compraProducto.getId());



        if (comp == null){
            return null;
        }
        comp.setCompra(compraProducto.getCompra());
        comp.setProducto(compraProducto.getProducto());
        comp.setCantidad(compraProducto.getCantidad());
        comp.setEstado(compraProducto.getEstado());
        comp.setTotal((float) (compraProducto.getProducto().getPrecio() * compraProducto.getCantidad()));
        //System.out.println("aunto de guardar actualizar "+comp.getIdCompra());
        return compraProductoRepository.save(comp);
    }

    @Override
    public CompraProducto eliminar(String id) {
        CompraProducto comp = encontrarPorId(id);
        if (comp == null){
            return null;
        }

        comp.setEstado(false);
        return compraProductoRepository.save(comp);
    }
}
