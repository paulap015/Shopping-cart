package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends MongoRepository<Rol,String> {

    Rol findByNombreRol(String rol);
}
