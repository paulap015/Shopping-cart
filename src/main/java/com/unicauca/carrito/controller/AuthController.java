package com.unicauca.carrito.controller;

import com.unicauca.carrito.domain.AuthenticationRequest;
import com.unicauca.carrito.domain.AuthenticationResponse;
import com.unicauca.carrito.seguridad.JWTUtil;
import com.unicauca.carrito.service.DetallesUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private DetallesUsuarioService detallesUsuarioService;

    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try{
            System.out.println("va a entrar a authenticate ");
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            System.out.println("pasa authenticate "+request.getUsername());
            UserDetails userDetails =  detallesUsuarioService.loadUserByUsername(request.getUsername());
            System.out.printf("Pasa detalles de usario "+userDetails.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt ),HttpStatus.OK);
        }catch(BadCredentialsException e ){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
