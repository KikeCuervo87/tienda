package com.pt.tienda.dto;

import com.pt.tienda.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
 //   @Mapping(target = "idProducto",source = "id_producto")
    Producto toModel(ProductoRequest productoRequest);
    ProductoDto toDto(Producto producto);
}
