package com.pt.tienda.repositories;

import com.pt.tienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
