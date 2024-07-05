package com.project.bloquera.dtos.manufactura;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleManufacturaCreateRequest(
    @NotNull(message = "El campo empleado es requerido") Long empleadoId,
    @NotNull(message = "El campo producto es requerido") Long productoId,
    @Min(value = 1, message = "El campo cantidad debe de ser mayor a cero")
    @NotNull(message = "El campo cantdad es requerido") Double cantidad
) {
}
