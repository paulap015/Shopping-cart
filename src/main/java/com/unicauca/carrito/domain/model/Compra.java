package com.unicauca.carrito.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="compra")
public class Compra {

    @Id
    private String idCompra;

    @DBRef
    private Cliente cliente;
    private LocalDateTime fecha;

    @Field(name="medio_pago")
    private String medioPago;

    private String comentario;
    private Boolean estado;
}
