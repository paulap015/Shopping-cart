package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends MongoRepository<Producto,String> {
}
