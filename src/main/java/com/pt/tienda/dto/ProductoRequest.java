package com.pt.tienda.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductoRequest {
    private Long idProducto;
    private String nombre;
    private Double precio;
    private String marca;
}
