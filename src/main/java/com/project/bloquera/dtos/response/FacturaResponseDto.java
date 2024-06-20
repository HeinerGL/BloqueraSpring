package com.project.bloquera.dtos.response;

import java.time.Instant;
import java.util.List;

public record FacturaResponseDto(Long id, Instant fecha, Double total,
                                 List<DetalleFacturaResponseDto> detalle) {
}
