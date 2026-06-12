package com.example.prueba_san.application.useCases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.prueba_san.domain.models.Cliente;
import com.example.prueba_san.domain.service.ClienteService;

@Component
public class GetClienteService {

    private final ClienteService clienteService;

    public GetClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<Cliente> executeGetAll() {
        return clienteService.getAllClientes();
    }

    public Optional<Cliente> executeGetById(Long id) {
        return clienteService.getClienteById(id);
    }

    public Cliente executeCreate(Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    public Cliente executeUpdate(Long id, Cliente cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    public void executeDelete(Long id) {
        clienteService.deleteCliente(id);
    }

    public List<Cliente> executeSearch(String nombre) {
        return clienteService.searchClientes(nombre);
    }
}
    