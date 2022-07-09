package com.unicauca.carrito.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection="categoria")
public class Categoria {

    @Id
    private String idCategoria;
    private String descripcion;
    private Boolean estado;

    public Categoria(String descripcion, Boolean estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
