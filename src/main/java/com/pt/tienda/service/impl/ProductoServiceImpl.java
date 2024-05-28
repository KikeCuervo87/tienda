package com.pt.tienda.service.impl;

import com.pt.tienda.dto.ProductoDto;
import com.pt.tienda.dto.ProductoMapper;
import com.pt.tienda.dto.ProductoRequest;
import com.pt.tienda.exception.ResourceNotFoundException;
import com.pt.tienda.model.Producto;
import com.pt.tienda.repositories.ProductoRepository;
import com.pt.tienda.service.ProductoSevice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoSevice {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public ProductoDto crearProducto(ProductoRequest productoRequest) {
        Producto producto = productoMapper.toModel(productoRequest);
        Producto productoNuevo = productoRepository.save(producto);
        return productoMapper.toDto(productoNuevo);
    }

    @Override
    public ProductoDto obtenerProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id " + id));
        return productoMapper.toDto(producto);
    }


    @Override
    public ProductoDto actualizarProducto(Long id, ProductoRequest productoRequest) {
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if (!productoOptional.isPresent()) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }

        Producto producto = productoOptional.get();

        if (productoRequest.getNombre() != null) {
            producto.setNombre(productoRequest.getNombre());
        }

        if (productoRequest.getPrecio() != null) {
            producto.setPrecio(productoRequest.getPrecio());
        }

        if (productoRequest.getMarca() != null) {
            producto.setMarca(productoRequest.getMarca());
        }

        Producto productoActualizado = productoRepository.save(producto);

        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdProducto(productoActualizado.getIdProducto());
        productoDto.setNombre(productoActualizado.getNombre());
        productoDto.setPrecio(productoActualizado.getPrecio());
        productoDto.setMarca(productoActualizado.getMarca());

        return productoDto;
    }

    @Override
    public void eliminarProducto(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (!productoOptional.isPresent()) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}
