package com.project.bloquera.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.dtos.empleado.EmpleadoCreateRequest;
import com.project.bloquera.exceptions.notfound.CargoNotFoundException;
import com.project.bloquera.exceptions.notfound.ClienteNotFoundException;
import com.project.bloquera.exceptions.notfound.EmpleadoNotFoundException;
import com.project.bloquera.mappers.EmpleadoMapper;
import com.project.bloquera.models.Cargo;
import com.project.bloquera.models.Empleado;
import com.project.bloquera.repositories.CargoRepository;
import com.project.bloquera.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final CargoRepository cargoRepository;

    private final EmpleadoMapper empleadoMapper;

    public EmpleadoService(EmpleadoRepository empleadoRepository,
        EmpleadoMapper empleadoMapper,
        CargoRepository cargoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.cargoRepository = cargoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    public Page<Empleado> getAllEmpleados(Pageable pageable) {
        return empleadoRepository.findAll(pageable);
    }

    public Iterable<Empleado> getListEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById(Long id) {
        return empleadoRepository.findById(id)
            .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }

    public Empleado createEmpleado(EmpleadoCreateRequest empleadoRequest) {
        Cargo cargo = cargoRepository.findById(empleadoRequest.cargoId())
            .orElseThrow(() -> new CargoNotFoundException(empleadoRequest.cargoId()));
        
        Empleado empleado = empleadoMapper.createRequestToModel(empleadoRequest);
        empleado.setCargo(cargo);

        return empleadoRepository.save(empleado);
    }

    public Empleado updateEmpleado(Long id, Empleado empleadoRequest) {
        boolean empleadoExists = empleadoRepository.existsById(id);

        if (!empleadoExists) {
            throw new ClienteNotFoundException(id);
        }

        empleadoRequest.setId(id);

        return empleadoRepository.save(empleadoRequest);
    }
}
