package com.unicauca.carrito.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cliente")
public class Cliente {

    @Id
    private String idCliente;
    @Field(name="nombre_cliente")
    private String nombreCliente;
    private String apellido;
    private String correo;
    private String password;
    @DBRef
    private Rol rol;
    private Boolean estado;
}