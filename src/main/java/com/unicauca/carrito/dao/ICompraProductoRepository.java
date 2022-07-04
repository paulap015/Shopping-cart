package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Compra;
import com.unicauca.carrito.domain.model.CompraProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ICompraProductoRepository extends MongoRepository<CompraProducto,String> {

    //@Query(value = "{idCompra:'?0'}")
    //@Query("{'compra':DBRef('compra', ObjectId('62be6a7023490e2e47af5769'))}")
    //List<CompraProducto> items(String idCompra); //le llega el id de la compra debe buscarlos por ese id

    List<CompraProducto> findByCompra(Compra compra);
}
