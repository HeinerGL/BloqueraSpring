package com.project.bloquera.mappers;

import org.springframework.stereotype.Component;

import com.project.bloquera.dtos.empleado.EmpleadoCreateRequest;
import com.project.bloquera.models.Empleado;

@Component
public class EmpleadoMapper {
    
    public Empleado createRequestToModel(EmpleadoCreateRequest createRequest) {
        Empleado empleado = new Empleado();
        empleado.setNombres(createRequest.nombres());
        empleado.setApellidos(createRequest.apellidos());
        empleado.setCedula(createRequest.cedula());
        empleado.setSexo(createRequest.sexo());
        empleado.setTelefono(createRequest.telefono());

        return empleado;
    }
}
