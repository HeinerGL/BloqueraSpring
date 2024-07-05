package com.project.bloquera.dtos.producto;

public record DetalleMateriaProductoCreateRequest(
    Long materiaPrimaId,
    Double cantidad
) {
}
