package com.project.bloquera.dtos.cliente;

public record ClienteCreateRequest(
    String nombres,
    String apellidos,
    String direccion,
    String correo,
    String telefono
) {
}
