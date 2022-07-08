package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.ICompraProductoRepository;
import com.unicauca.carrito.dao.ICompraRepository;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Compra;
import com.unicauca.carrito.domain.model.CompraProducto;
import com.unicauca.carrito.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraProductoServiceImpl implements ICompraProductoService{
    
    @Autowired
    ICompraProductoRepository compraProductoRepository;

    @Lazy
    @Autowired
    ICompraService compraService;

    @Autowired
    IProductoService productoService;
    @Lazy
    @Autowired
    IClienteService clienteService;
    @Override
    public List<CompraProducto> buscarTodos() {
        return compraProductoRepository.findAll();
    }

    @Override
    public CompraProducto guardar(CompraProducto compraProducto) {

        Producto verificarProducto =productoService.encontrarPorId(compraProducto.getProducto().getIdProducto());

        if(verificarProducto==null){
            System.out.println("producto  no existe para añadir a la compra");
            return null;
        }
        // verificar que si haya existencia en stock
        if(compraProducto.getCantidad()>verificarProducto.getCantidadStock()){
            System.out.println("No hay disponibilidad de stock");
            return null;
        }
        // actualizar stock del producto
        productoService.reducirStock(verificarProducto.getIdProducto(),compraProducto.getCantidad());

        //asignar la referencia a compra y producto
        compraProducto.setCompra(compraService.encontrarPorId(compraProducto.getCompra().getIdCompra()));
        compraProducto.setProducto(verificarProducto);
        compraProducto.setEstado(true);

        //actualizar el total en compraProducto
        compraProducto.setTotal((float) (compraProducto.getProducto().getPrecio() * compraProducto.getCantidad()));

        //calcular el  total en compra

        return compraProductoRepository.save(compraProducto);
    }
    @Override
    public CompraProducto encontrarPorId(String id) {

        return compraProductoRepository.findById(id).orElse(null);
    }

    public void totalEnCompra(CompraProducto compraProducto){

        compraService.calcularTotal(compraProducto.getCompra());
    }
    @Override
    public List<CompraProducto> buscarProductosDeCompra(String idCompra) {
        Compra newCompra = compraService.encontrarPorId(idCompra);
        if(newCompra == null){

        }

        return compraProductoRepository.findByCompra(newCompra);
    }

    @Override
    public CompraProducto actualizar(CompraProducto compraProducto,String username) {

        Compra verificarCompra = compraService.encontrarPorId(compraProducto.getCompra().getIdCompra()) ;
        Producto verificarProducto =productoService.encontrarPorId(compraProducto.getProducto().getIdProducto());
        //verificar que exista la compra y el producto
        if(verificarCompra==null){
            System.out.println("compra no existe para actualizar prodcuto");
            return null;
        }if(verificarProducto==null){
            System.out.println("producto  no existe para añadir a la compra");
            return null;
        }
        if(verificarCompra.getCliente().getUsername() != username){
            System.out.println("Este usuario no puede modificar la compra de este item");
            return null;
        }
        //asignar los obj al obj que se va a actualizar
        compraProducto.setCompra(verificarCompra);
        compraProducto.setProducto(verificarProducto);

        CompraProducto comp = encontrarPorId(compraProducto.getId());

        if (comp == null){
            System.out.println("compraProducto no existe para actualizarla");
            return null;
        }
        comp.setCompra(compraProducto.getCompra());
        comp.setProducto(compraProducto.getProducto());
        comp.setCantidad(compraProducto.getCantidad());
        if(compraProducto.getEstado() != null){
            comp.setEstado(compraProducto.getEstado());
        }

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
        productoService.aumentarStock(comp.getProducto().getIdProducto(),comp.getCantidad());
        return compraProductoRepository.save(comp);
    }
}
