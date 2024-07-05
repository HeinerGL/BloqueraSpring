package com.project.bloquera.exceptions.notfound;

public class MateriaPrimaNotFoundException extends ResourceNotFoundException {
    
    public MateriaPrimaNotFoundException(Long id) {
        super("Materia Prima #%d no encontrado".formatted(id));
    }
}
