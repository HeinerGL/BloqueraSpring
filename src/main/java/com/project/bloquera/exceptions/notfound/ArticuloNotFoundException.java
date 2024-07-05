package com.project.bloquera.exceptions.notfound;

public class ArticuloNotFoundException extends ResourceNotFoundException {
    
    public ArticuloNotFoundException(Long id) {
        super("Articulo %d no encontrado".formatted(id));
    }
}
