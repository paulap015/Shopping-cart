package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.IClienteRepository;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    @Autowired
    private IClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente  = clienteRepository.findByUsername(username);
        //return new User("pau","{noop}123",new ArrayList<>());
        if (cliente == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(cliente.getUsername())
                .password("{noop}"+cliente.getPassword())
                .authorities(getAuthorities(cliente)).build();
        return user;
        //return new User(cliente.getUsername(),"{noop}"+cliente.getPassword(), new ArrayList<>());
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Cliente cli) {
        Rol rol = cli.getRol();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(rol.getNombreRol().toUpperCase()));

        return authorities;
    }
}
