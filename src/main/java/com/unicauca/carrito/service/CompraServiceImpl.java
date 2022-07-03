package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.ICompraProductoRepository;
import com.unicauca.carrito.dao.ICompraRepository;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Compra;
import com.unicauca.carrito.domain.model.CompraProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements  ICompraService {

    @Autowired
    private ICompraRepository compraRepository;

    @Autowired
    private ICompraProductoRepository compraProductoRepository;

    @Autowired
    ICompraProductoService compraProductoService;

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
    public void calcularTotal(Compra comp) {
        System.out.println("entra a calcular compra");

        float total=0;
        //buscar en compraProducto y sacar los totales que tengan elid de compra
        List<CompraProducto> items = compraProductoService.buscarProductosDeCompra(comp.getIdCompra());
        System.out.println("Items encontrados : "+items.size());
        for(CompraProducto compra :items){
            total = total+compra.getTotal();
        }
        comp.setTotalFinal(total);
        actualizar(comp);
    }

    @Override
    public Compra actualizar(Compra compra) {
        Compra comp = encontrarPorId(compra.getIdCompra());

        
        if (comp == null){
            return null;
        }

        comp.setCliente(compra.getCliente());
        comp.setFecha(compra.getFecha());
        comp.setMedioPago(compra.getMedioPago());
        comp.setComentario(compra.getComentario());
        comp.setTotalFinal(compra.getTotalFinal());
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
