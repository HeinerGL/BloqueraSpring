package com.project.bloquera.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.dtos.cargo.CargoCreateRequest;
import com.project.bloquera.exceptions.notfound.CargoNotFoundException;
import com.project.bloquera.mappers.CargoMapper;
import com.project.bloquera.models.Cargo;
import com.project.bloquera.repositories.CargoRepository;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;
    private final CargoMapper cargoMapper;

    public CargoService(CargoRepository cargoRepository,
        CargoMapper cargoMapper) {
        this.cargoRepository = cargoRepository;
        this.cargoMapper = cargoMapper;
    }

    public Page<Cargo> getAllCargos(Pageable pageable) {
        return cargoRepository.findAll(pageable);
    }

    public Iterable<Cargo> getListCargos() {
        return cargoRepository.findAll();
    }

    public Cargo getCargoById(Long id) {
        return cargoRepository.findById(id)
            .orElseThrow(() -> new CargoNotFoundException(id));
    }

    public Cargo createCargo(CargoCreateRequest cargoRequest) {
        return cargoRepository.save(cargoMapper.createRequestToModel(cargoRequest));
    }
}
