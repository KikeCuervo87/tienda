package com.pt.tienda.controller;

import com.pt.tienda.dto.ProductoDto;
import com.pt.tienda.dto.ProductoRequest;
import com.pt.tienda.exception.ResourceNotFoundException;
import com.pt.tienda.service.ProductoSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private final ProductoSevice productoService;

    public ProductoController(ProductoSevice productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoRequest productoRequest) {
        ProductoDto nuevoProducto = productoService.crearProducto(productoRequest);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerProducto(@PathVariable Long id) {
        try {
            ProductoDto producto = productoService.obtenerProducto(id);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest) {
        try {
            ProductoDto productoActualizado = productoService.actualizarProducto(id, productoRequest);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
