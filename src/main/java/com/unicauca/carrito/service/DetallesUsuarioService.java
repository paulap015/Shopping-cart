package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.IClienteRepository;
import com.unicauca.carrito.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    @Autowired
    private IClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente  = clienteRepository.findByUsername(username);
        //return new User("pau","{noop}123",new ArrayList<>());

        return new User(cliente.getUsername(),"{noop}"+cliente.getPassword(), new ArrayList<>());
    }
}
