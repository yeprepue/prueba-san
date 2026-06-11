package com.example.prueba_san.infraestructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaContratoRepository extends JpaRepository<ContratoEntity, Long> {

    List<ContratoEntity> findByClienteId(Long clienteId);

    List<ContratoEntity> findByProductoId(Long productoId);
}
