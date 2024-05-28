package com.pt.tienda.service;

import com.pt.tienda.dto.ProductoDto;
import com.pt.tienda.dto.ProductoRequest;

public interface ProductoSevice {
    ProductoDto crearProducto(ProductoRequest productoRequest);
    ProductoDto obtenerProducto(Long id);
    ProductoDto actualizarProducto(Long id,ProductoRequest productoRequest);
    void eliminarProducto(Long id);
}
