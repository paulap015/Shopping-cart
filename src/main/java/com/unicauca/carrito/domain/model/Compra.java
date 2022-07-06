package com.unicauca.carrito.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;

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
    private Date fecha;
    @Field(name="medio_pago")
    private String medioPago;
    private Float  totalFinal;
    private String comentario;
    private Boolean estado;

    public Compra (Cliente cli,String pago ,String com)
    {
        cliente =cli;
        medioPago=pago;
        comentario=com;
    }
}
