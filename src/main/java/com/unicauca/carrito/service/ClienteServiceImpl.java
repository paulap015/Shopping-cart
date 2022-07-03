package com.unicauca.carrito.service;

import com.unicauca.carrito.dao.IClienteRepository;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    IRolService rolService;

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        System.out.println("vouy a buscar rol");
        Rol rol = rolService.encontrarPorNombreRol("customer");
        System.out.println("obtuve el rol "+rol.getNombreRol());
        cliente.setRol(rol);
        cliente.setEstado(true);

        return clienteRepository.save(cliente);

    }
    @Override
    public Cliente encontrarPorId(String id) {

        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        Rol rol = rolService.encontrarPorId(cliente.getRol().getIdRol());
        if(rol ==null){
            System.out.println("el rol no existe");
            return null;
        }
        Cliente cli = encontrarPorId(cliente.getIdCliente());


        if (cli == null){
            System.out.println("no existe cliente con ese id ");
            return null;
        }
        cli.setNombreCliente(cliente.getNombreCliente());
        cli.setApellido(cliente.getApellido());
        cli.setUsername(cliente.getUsername());
        cli.setRol(rol);
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
