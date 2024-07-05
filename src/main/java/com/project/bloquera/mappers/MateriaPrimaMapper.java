package com.project.bloquera.mappers;

import org.springframework.stereotype.Component;

import com.project.bloquera.dtos.materiaprima.MateriaPrimaCreateRequest;
import com.project.bloquera.models.MateriaPrima;

@Component
public class MateriaPrimaMapper {

    public MateriaPrima createRequestToModel(MateriaPrimaCreateRequest createRequest) {
        MateriaPrima materiaPrima = new MateriaPrima();
        materiaPrima.setDescripcion(createRequest.descripcion());
        materiaPrima.setPrecio(createRequest.precio());
        materiaPrima.setStock(createRequest.stock());
        materiaPrima.setStockMinimo(createRequest.stockMinimo());

        return materiaPrima;
    }
}
