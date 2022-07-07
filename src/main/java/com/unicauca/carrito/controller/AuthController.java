package com.unicauca.carrito.controller;

import com.unicauca.carrito.domain.AuthenticationRequest;
import com.unicauca.carrito.domain.model.Cliente;
import com.unicauca.carrito.domain.model.ClienteDto;
import com.unicauca.carrito.domain.model.Compra;
import com.unicauca.carrito.seguridad.JWTUtil;
import com.unicauca.carrito.service.DetallesUsuarioService;
import com.unicauca.carrito.service.IClienteService;
import com.unicauca.carrito.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private DetallesUsuarioService detallesUsuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    @Lazy
    @Autowired
    private IClienteService clienteService;
    @Lazy
    @Autowired
    private ICompraService compraService;

    @PostMapping("/authenticate")
    //public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request){
        try {
            Cliente buscarCliente = clienteService.encontrarPorUsername(request.getUsername());
            if (buscarCliente==null){
                return new ResponseEntity<>(ClienteDto.builder().build(),HttpStatus.OK);
            }
            System.out.println("va a entrar a authenticate ");
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            System.out.println("pasa authenticate " + request.getUsername());
            UserDetails userDetails = detallesUsuarioService.loadUserByUsername(request.getUsername());
            System.out.printf("Pasa detalles de usario " + userDetails.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            ClienteDto newClienteDTO= new ClienteDto(userDetails.getUsername(),jwt,buscarCliente.getRol().getNombreRol(), buscarCliente.getIdCompra());
            return new ResponseEntity<>(newClienteDTO,HttpStatus.OK);
            //return new ResponseEntity<>(new AuthenticationResponse(jwt ),HttpStatus.OK);
        }catch(BadCredentialsException e ){
            //return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            return new ResponseEntity<>(ClienteDto.builder().build(),HttpStatus.OK);
        }

    }

}
