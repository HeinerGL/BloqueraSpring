package com.project.bloquera.exceptions.notfound;

public class OrdenNotFoundException extends ResourceNotFoundException {
    
    public OrdenNotFoundException(Long id) {
        super("Orden #%d no encontrada".formatted(id));
    }
}
