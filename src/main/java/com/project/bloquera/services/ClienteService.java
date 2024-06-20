package com.project.bloquera.services;

import com.project.bloquera.dtos.cliente.ClienteCreateRequest;
import com.project.bloquera.exceptions.ResourceNotFoundException;
import com.project.bloquera.mappers.ClienteMapper;
import com.project.bloquera.models.Cliente;
import com.project.bloquera.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper;

    public static final String CLIENTE_NOT_FOUND = "Cliente #%d no encontrado";

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Page<Cliente> getAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        CLIENTE_NOT_FOUND.formatted(id)));
    }

    public Cliente createCliente(ClienteCreateRequest clienteRequest) {
        Cliente cliente = clienteMapper.CreateRequestToModel(clienteRequest);
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        boolean clienteExists = clienteRepository.existsById(id);

        if (!clienteExists) {
            throw new ResourceNotFoundException(CLIENTE_NOT_FOUND.formatted(id));
        }

        cliente.setId(id);

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        CLIENTE_NOT_FOUND.formatted(id)));

        clienteRepository.delete(cliente);
    }
}