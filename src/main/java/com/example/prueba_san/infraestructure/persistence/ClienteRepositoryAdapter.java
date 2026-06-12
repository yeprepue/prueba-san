package com.example.prueba_san.infraestructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.prueba_san.domain.models.Cliente;
import com.example.prueba_san.domain.ports.ClienteRepository;

@Component
public class ClienteRepositoryAdapter implements ClienteRepository {
    
    private final JpaClienteRepository jpaClienteRepository;
    
    public ClienteRepositoryAdapter(JpaClienteRepository jpaClienteRepository) {
        this.jpaClienteRepository = jpaClienteRepository;
    }
    
    @Override
    public List<Cliente> findAll() {
        return jpaClienteRepository.findAll().stream()
            .map(this::toDomain)
            .toList();
    }
    
    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaClienteRepository.findById(id)
            .map(this::toDomain);
    }
    
    @Override
    public Cliente save(@Nullable Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        ClienteEntity saved = jpaClienteRepository.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaClienteRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaClienteRepository.existsById(id);
    }
    
    @Override
    public List<Cliente> findByNombreContaining(String nombre) {
        return jpaClienteRepository.findByNombreContaining(nombre).stream()
            .map(this::toDomain)
            .toList();
    }
    
    // Conversiones
    private Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;
        Cliente cliente = new Cliente();
        cliente.setId(entity.getId());
        cliente.setNombre(entity.getNombre());
        cliente.setEmail(entity.getEmail());
        cliente.setSegmento(entity.getSegmento());
        cliente.setActivo(entity.getActivo());
        return cliente;
    }
    
    private ClienteEntity toEntity(Cliente cliente) {
        if (cliente == null) return null;
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNombre(cliente.getNombre());
        entity.setEmail(cliente.getEmail());
        entity.setSegmento(cliente.getSegmento());
        entity.setActivo(cliente.getActivo());
        return entity;
    }
}