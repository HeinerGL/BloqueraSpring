package com.project.bloquera.dtos.materiaprima;

public record MateriaPrimaCreateRequest(
    String descripcion,
    Double precio,
    Double stock,
    Double stockMinimo,
    Long unidadMedidaId
) {
}
