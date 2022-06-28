package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRolRepository extends MongoRepository<Rol,String> {
}
