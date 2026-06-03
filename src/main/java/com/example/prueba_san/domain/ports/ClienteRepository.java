package com.example.prueba_san.domain.post

import com.example.Clientes.domain.models.Cliente;

import java.utils.List;
import java.util.Optional;

public interface ClienteRepository {

    List<Cliente> findAll();

    Optional<Cliente>FindById(Long id);
    
    CLiente save(Cliente cliente);

    
}
