package com.project.bloquera.exceptions.notfound;

public class ClienteNotFoundException extends ResourceNotFoundException {

    public ClienteNotFoundException(Long id) {
        super("Cliente con ID %d no encontrado".formatted(id));
    }
}
