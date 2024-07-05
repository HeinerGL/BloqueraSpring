package com.project.bloquera.exceptions.notfound;

public class UnidadMedidaNotFoundException extends ResourceNotFoundException {
    
    public UnidadMedidaNotFoundException(Long id) {
        super("Unidad medida #%d no encontrada".formatted(id));
    }
}
