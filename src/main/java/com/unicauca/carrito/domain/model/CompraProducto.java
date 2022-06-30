package com.unicauca.carrito.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "compraProducto")
public class CompraProducto {

    @Id
    private String id;
    @DBRef
    private Producto producto;
    @DBRef
    private Compra compra;
    private Integer cantidad;
    private Float total;
    private Boolean estado;

}
