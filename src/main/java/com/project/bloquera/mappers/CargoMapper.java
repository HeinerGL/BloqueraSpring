package com.project.bloquera.mappers;

import org.springframework.stereotype.Component;

import com.project.bloquera.dtos.cargo.CargoCreateRequest;
import com.project.bloquera.models.Cargo;

@Component
public class CargoMapper {
    
    public Cargo createRequestToModel(CargoCreateRequest createRequest) {
        Cargo cargo = new Cargo();
        cargo.setDescripcion(createRequest.descripcion());

        return cargo;
    }
}
