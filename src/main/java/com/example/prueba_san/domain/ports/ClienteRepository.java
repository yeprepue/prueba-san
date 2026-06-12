package com.example.prueba_san.domain.ports;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.Nullable;

import com.example.prueba_san.domain.models.Cliente;

/**
 * Port interface for Cliente repository operations.
 * Defines contracts for persistence operations in the domain layer.
 */
public interface ClienteRepository {
    
    /**
     * Get all clientes.
     * @return list of all clientes
     */
    List<Cliente> findAll();
    
    /**
     * Find a cliente by id.
     * @param id the cliente id
     * @return Optional containing the cliente if found
     */
    Optional<Cliente> findById(Long id);
    
    /**
     * Save or update a cliente.
     * @param cliente the cliente to save (may be null)
     * @return the saved cliente
     */
    @Nullable
    Cliente save(@Nullable Cliente cliente);
    
    /**
     * Delete a cliente by id.
     * @param id the cliente id to delete
     */
    void deleteById(Long id);
    
    /**
     * Check if a cliente exists by id.
     * @param id the cliente id
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);
    
    /**
     * Find clientes by nombre containing the given string.
     * @param nombre the string to search for in nombre
     * @return list of matching clientes
     */
    List<Cliente> findByNombreContaining(String nombre);
}
