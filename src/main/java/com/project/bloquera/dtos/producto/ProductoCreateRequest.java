package com.project.bloquera.dtos.producto;

public record ProductoCreateRequest(
    String descripcion,
    Double precio,
    Double stock,
    Double stockMinimo
) {
}
