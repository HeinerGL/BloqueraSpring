package com.project.bloquera.dtos.producto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoCreateRequest(
    @NotBlank(message = "El campo descripcion es requerido") String descripcion,
    @NotNull(message = "El campo precio es requerido") Double precio,
    @NotNull(message = "El campo stock es requerido") Double stock,
    @NotNull(message = "El campo stock minimo es requerido") Double stockMinimo,
    @NotNull(message = "El campo comision es requerido") Double comisionPorProducto,
    List<DetalleMateriaProductoCreateRequest> detalleMateriaPrima
) {
}
