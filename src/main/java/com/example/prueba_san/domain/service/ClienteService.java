package com.example.prueba_san.domain.service;

import com.example.prueba_san.domain.models.Cliente;
import com.example.prueba_san.domain.ports.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }
    
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        return clienteRepository.findById(id)
            .map(cliente -> {
                cliente.setNombre(clienteDetails.getNombre());
                cliente.setEmail(clienteDetails.getEmail());
                cliente.setSegmento(clienteDetails.getSegmento());
                cliente.setActivo(clienteDetails.getActivo());
                return clienteRepository.save(cliente);
            })
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }
    
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
    
    public List<Cliente> searchClientes(String nombre) {
        return clienteRepository.findByNombreContaining(nombre);
    }
}