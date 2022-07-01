package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.CompraProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompraProductoRepository extends MongoRepository<CompraProducto,String> {

    @Query(value = "{idCompra:'?0'}")
    List<CompraProducto> items(String idCompra); //le llega el id de la compra debe buscarlos por ese id
}
