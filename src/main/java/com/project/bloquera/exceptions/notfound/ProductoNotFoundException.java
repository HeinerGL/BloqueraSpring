package com.project.bloquera.exceptions.notfound;

public class ProductoNotFoundException extends ResourceNotFoundException {
    
    public ProductoNotFoundException(Long id) {
        super("Producto #%d no encontrado".formatted(id));
    }
}
