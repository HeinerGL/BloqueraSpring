package com.project.bloquera.dtos.orden;

public record DetalleOrdenCreateRequest(
    Long articuloId,
    Double cantidad
) {
}
