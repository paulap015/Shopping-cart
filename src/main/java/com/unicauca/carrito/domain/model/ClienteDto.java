package com.unicauca.carrito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    private String username;
    private String token;
    private String rol;
    private String idCarrito;
}
