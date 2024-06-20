package com.project.bloquera.dtos.orden;

import java.util.List;

public record OrdenCreateRequest(
    Long clienteId,
    List<DetalleOrdenCreateRequest> detalle
) {
}
