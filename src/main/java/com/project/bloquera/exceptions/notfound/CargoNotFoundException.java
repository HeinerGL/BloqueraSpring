package com.project.bloquera.exceptions.notfound;

public class CargoNotFoundException extends ResourceNotFoundException {
    
    public CargoNotFoundException(Long id) {
        super("Cargo #%d no encontrado".formatted(id));
    }
}
