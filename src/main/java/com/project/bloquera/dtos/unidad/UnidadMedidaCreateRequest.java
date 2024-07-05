package com.project.bloquera.dtos.unidad;

import jakarta.validation.constraints.NotBlank;

public record UnidadMedidaCreateRequest(
    @NotBlank(message = "El campo unidad de medida es requerido") String unidad
) {
}
