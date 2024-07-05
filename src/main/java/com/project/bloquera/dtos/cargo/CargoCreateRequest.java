package com.project.bloquera.dtos.cargo;

import jakarta.validation.constraints.NotBlank;

public record CargoCreateRequest(
    @NotBlank(message = "El campo descripcion es requerido") String descripcion
) {
}
