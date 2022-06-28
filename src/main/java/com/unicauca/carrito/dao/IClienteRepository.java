package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente,String> {
}
