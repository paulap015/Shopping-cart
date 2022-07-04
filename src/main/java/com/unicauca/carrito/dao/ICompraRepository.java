package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ICompraRepository extends MongoRepository<Compra,String> {

}
