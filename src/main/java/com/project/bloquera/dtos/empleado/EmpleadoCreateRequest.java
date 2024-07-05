package com.project.bloquera.dtos.empleado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpleadoCreateRequest(
    @NotBlank(message = "El campo nombres es requerido") String nombres,
    @NotBlank(message = "El campo apellidos es requerido") String apellidos,
    @NotBlank(message = "El campo cedula es requerido") String cedula,
    @NotNull(message = "EL campo sexo es requerido") Character sexo,
    @NotBlank(message = "El campo telefono es requerido") String telefono,
    @NotNull(message = "El campo cargo es requerido") Long cargoId
) {
}
