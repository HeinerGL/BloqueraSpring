package com.project.bloquera.mappers;

import org.springframework.stereotype.Component;

import com.project.bloquera.dtos.cliente.ClienteCreateRequest;
import com.project.bloquera.models.Cliente;

@Component
public class ClienteMapper {
    
    public Cliente CreateRequestToModel(ClienteCreateRequest createRequest) {
        Cliente cliente = new Cliente();
        cliente.setNombres(createRequest.nombres());
        cliente.setApellidos(createRequest.apellidos());
        cliente.setDireccion(createRequest.direccion());
        cliente.setCorreo(createRequest.correo());
        cliente.setTelefono(createRequest.telefono());

        return cliente;
    }
}
