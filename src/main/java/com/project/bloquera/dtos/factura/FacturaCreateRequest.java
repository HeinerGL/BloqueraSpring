package com.project.bloquera.dtos.factura;

import jakarta.validation.constraints.NotNull;

public record FacturaCreateRequest(
    @NotNull(message = "El campo orden es requerido") Long ordenId
) {
}
