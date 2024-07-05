package com.project.bloquera.dtos.orden;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record OrdenCreateRequest(
    @NotNull(message = "El campo cliente es requerido") Long clienteId,
    @NotNull(message = "La orden debe contener uno o más productos") List<DetalleOrdenCreateRequest> detalle
) {
}
