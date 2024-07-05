package com.project.bloquera.exceptions.notfound;

public class FacturaNotFoundException extends ResourceNotFoundException {
    
    public FacturaNotFoundException(Long id) {
        super("Factura #%d no encontrada".formatted(id));
    }
}
