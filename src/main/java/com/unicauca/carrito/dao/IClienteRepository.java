package com.unicauca.carrito.dao;

import com.unicauca.carrito.domain.model.Cliente;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente,String> {

    @Query(value = "{'idCliente':?0}" )
    Cliente findByClienteId (String idCliente);

    @Query(value = "{username:'?0'}")
    Cliente findByUsername(String username);
}
