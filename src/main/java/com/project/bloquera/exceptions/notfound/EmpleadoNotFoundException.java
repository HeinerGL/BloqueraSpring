package com.project.bloquera.exceptions.notfound;

public class EmpleadoNotFoundException extends ResourceNotFoundException {
    
    public EmpleadoNotFoundException(Long id) {
        super("Empleado #%d no encontrado".formatted(id));
    }
}
