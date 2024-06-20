package com.project.bloquera.controllers;

import com.project.bloquera.dtos.cliente.ClienteCreateRequest;
import com.project.bloquera.models.Cliente;
import com.project.bloquera.services.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> all(Pageable pageable) {
        var clientes = clienteService.getAllClientes(pageable);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> show(@PathVariable Long id) {
        var cliente = clienteService.getClienteById(id);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody ClienteCreateRequest cliente) {
        var newCliente = clienteService.createCliente(cliente);
        URI locationOfNewCliente = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newCliente.getId())
            .toUri();

        return ResponseEntity.created(locationOfNewCliente)
            .body(newCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        var updateCliente = clienteService.updateCliente(id, cliente);

        return ResponseEntity.ok(updateCliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
}
