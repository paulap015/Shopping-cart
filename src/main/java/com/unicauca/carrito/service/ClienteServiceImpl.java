package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.IClienteRepository;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    @Override
    public Cliente encontrarPorId(String id) {

        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        Cliente cli = encontrarPorId(cliente.getIdCliente());


        if (cli == null){
            return null;
        }

        cli.setApellido(cliente.getApellido());
        //cli.setCorreo(cliente.getCorreo());
        cli.setUsername(cliente.getUsername());
        cli.setNombreCliente(cliente.getNombreCliente());
        cli.setRol(cliente.getRol());
        cli.setEstado(cliente.getEstado());
        return clienteRepository.save(cli);
    }

    @Override
    public Cliente eliminar(String id) {
        Cliente cli = encontrarPorId(id);
        if (cli == null){
            return null;
        }

        cli.setEstado(false);
        return clienteRepository.save(cli);
    }
}
