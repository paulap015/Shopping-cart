package com.unicauca.carrito.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="producto")
public class Producto {

    @Id
    private String idProducto;
    private String nombre;
    @DBRef
    private Categoria categoria;
    private Double precio;
    @Field(name="cantidad_stock")
    private Integer cantidadStock;
    private String foto;
    private String descripcion;
    private Boolean estado;
}
