package com.project.bloquera.mappers;

import org.springframework.stereotype.Component;

import com.project.bloquera.dtos.unidad.UnidadMedidaCreateRequest;
import com.project.bloquera.models.UnidadMedida;

@Component
public class UnidadMedidaMapper {
    
    public UnidadMedida createRequestToModel(UnidadMedidaCreateRequest createRequest) {
        UnidadMedida unidadMedida = new UnidadMedida();
        unidadMedida.setUnidad(createRequest.unidad());

        return unidadMedida;
    }
}
