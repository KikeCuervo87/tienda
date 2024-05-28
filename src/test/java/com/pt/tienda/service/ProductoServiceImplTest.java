package com.pt.tienda.service;

import com.pt.tienda.dto.ProductoDto;
import com.pt.tienda.dto.ProductoMapper;
import com.pt.tienda.dto.ProductoRequest;
import com.pt.tienda.exception.ResourceNotFoundException;
import com.pt.tienda.model.Producto;
import com.pt.tienda.repositories.ProductoRepository;
import com.pt.tienda.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoServiceImpl productoServiceImpl;

    private Producto buildProducto() {
        return new Producto(1L, "Producto", 200D, "Marca");
    }

    private ProductoDto buildProductoDto() {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdProducto(1L);
        productoDto.setNombre("Producto");
        productoDto.setPrecio(200D);
        productoDto.setMarca("Marca");
        return productoDto;
    }

    private ProductoRequest buildProductoRequest() {
        ProductoRequest productoRequest = new ProductoRequest();
        productoRequest.setNombre("Producto");
        productoRequest.setPrecio(200D);
        productoRequest.setMarca("Marca");
        return productoRequest;
    }

    @Test
    void crearProducto() {
        Producto producto = buildProducto();
        ProductoDto productoDto = buildProductoDto();
        ProductoRequest productoRequest = buildProductoRequest();

        when(productoMapper.toModel(any(ProductoRequest.class))).thenReturn(producto);
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        when(productoMapper.toDto(any(Producto.class))).thenReturn(productoDto);

        ProductoDto result = productoServiceImpl.crearProducto(productoRequest);

        assertNotNull(result, "El resultado no debería ser null");
        assertEquals(productoDto.getIdProducto(), result.getIdProducto());
        assertEquals(productoDto.getNombre(), result.getNombre());
        assertEquals(productoDto.getPrecio(), result.getPrecio());
        assertEquals(productoDto.getMarca(), result.getMarca());

        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void obtenerProducto() {
        Producto producto = buildProducto();
        ProductoDto productoDto = buildProductoDto();

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        when(productoMapper.toDto(any(Producto.class))).thenReturn(productoDto);

        ProductoDto result = productoServiceImpl.obtenerProducto(1L);

        assertNotNull(result, "El resultado no debería ser null");
        assertEquals(productoDto.getIdProducto(), result.getIdProducto());
        assertEquals(productoDto.getNombre(), result.getNombre());
        assertEquals(productoDto.getPrecio(), result.getPrecio());
        assertEquals(productoDto.getMarca(), result.getMarca());

        verify(productoRepository, times(1)).findById(anyLong());
    }

    @Test
    void obtenerProducto_NotFound() {
        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productoServiceImpl.obtenerProducto(1L));

        verify(productoRepository, times(1)).findById(anyLong());
    }

    @Test
    void actualizarProducto() {
        Producto producto = buildProducto();
        ProductoDto productoDto = buildProductoDto();
        ProductoRequest productoRequest = buildProductoRequest();

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        //when(productoMapper.toDto(any(Producto.class))).thenReturn(productoDto);

        ProductoDto result = productoServiceImpl.actualizarProducto(1L, productoRequest);

        assertNotNull(result, "El resultado no debería ser null");
        assertEquals(productoDto.getIdProducto(), result.getIdProducto());
        assertEquals(productoDto.getNombre(), result.getNombre());
        assertEquals(productoDto.getPrecio(), result.getPrecio());
        assertEquals(productoDto.getMarca(), result.getMarca());

        verify(productoRepository, times(1)).findById(anyLong());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void actualizarProducto_NotFound() {
        ProductoRequest productoRequest = buildProductoRequest();

        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productoServiceImpl.actualizarProducto(1L, productoRequest));

        verify(productoRepository, times(1)).findById(anyLong());
    }

    @Test
    void eliminarProducto() {
        Producto producto = buildProducto();

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        doNothing().when(productoRepository).deleteById(anyLong());

        productoServiceImpl.eliminarProducto(1L);

        verify(productoRepository, times(1)).findById(anyLong());
        verify(productoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void eliminarProducto_NotFound() {
        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productoServiceImpl.eliminarProducto(1L));

        verify(productoRepository, times(1)).findById(anyLong());
        verify(productoRepository, never()).deleteById(anyLong());
    }
}
