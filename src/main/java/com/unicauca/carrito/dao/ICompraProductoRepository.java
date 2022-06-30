package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.CompraProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompraProductoRepository extends MongoRepository<CompraProducto,String> {
}
