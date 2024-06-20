package com.project.bloquera.dtos.orden;

import java.time.Instant;
import java.util.List;

import com.project.bloquera.models.Cliente;

public record OrdenResponse(
    Long id,
    Instant fecha,
    Double total,
    Cliente cliente,
    List<DetalleOrdenResponse> detalle
) {
}
