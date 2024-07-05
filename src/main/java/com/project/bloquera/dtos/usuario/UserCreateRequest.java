package com.project.bloquera.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(
    @NotBlank(message = "El campo usuario es requerido") String username,
    @NotBlank(message = "El campo contraseña es requerido") String password,
    @NotBlank(message = "El campo email es requerido")
    @Email(message = "El campo correo debe tener un formato valido") String email,
    @NotNull(message = "El campo rol es requerido") Long rol
) {
}
