package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoriaRepository extends MongoRepository<Categoria,String> {

}
