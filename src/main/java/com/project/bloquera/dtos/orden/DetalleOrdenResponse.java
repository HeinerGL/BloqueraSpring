package com.project.bloquera.dtos.orden;

public record DetalleOrdenResponse(
    Long id,
    String descripcion,
    Double cantidad,
    Double precio
) {
}
