package com.example.prueba_san.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
