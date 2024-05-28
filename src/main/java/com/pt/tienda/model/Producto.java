package com.pt.tienda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.swing.*;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Producto {
    @Id
    @Column(name = "id_producto")
    private Long idProducto;
    @Column
    private String nombre;
    @Column
    private Double precio;
    @Column
    private String marca;
}
