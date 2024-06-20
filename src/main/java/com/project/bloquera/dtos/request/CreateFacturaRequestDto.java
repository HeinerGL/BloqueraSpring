package com.project.bloquera.dtos.request;

import java.util.List;

public record CreateFacturaRequestDto(Long clienteId, List<DetalleFacturaRequestDto> detalle) {
}
