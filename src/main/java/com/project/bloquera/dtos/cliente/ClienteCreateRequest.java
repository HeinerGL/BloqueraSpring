package com.project.bloquera.dtos.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteCreateRequest(
    @NotBlank(message = "El campo nombres es requerido") String nombres,
    @NotBlank(message = "El campo apellidos es requerido") String apellidos,
    @NotBlank(message = "El campo direccion es requerido") String direccion,
    @NotBlank(message = "El campo correo es requerido")
    @Email(message = "El campo correo debe tener un formato valido") String correo,
    @NotBlank(message = "El campo telefono es requerido") String telefono
) {
}
