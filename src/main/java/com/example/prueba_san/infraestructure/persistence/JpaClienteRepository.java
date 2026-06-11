package com.example.prueba_san.infraestructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {

    List<ClienteEntity> findByNombreContaining(String nombre);
}
