package com.project.bloquera.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.bloquera.dtos.empleado.EmpleadoCreateRequest;
import com.project.bloquera.models.Empleado;
import com.project.bloquera.services.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<Page<Empleado>> all(Pageable pageable) {
        var productos = empleadoService.getAllEmpleados(pageable);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Empleado>> list() {
        var empleados = empleadoService.getListEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> show(@PathVariable Long id) {
        var producto = empleadoService.getEmpleadoById(id);

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Empleado> store(@Valid @RequestBody EmpleadoCreateRequest empleado) {
        var newEmpleado = empleadoService.createEmpleado(empleado);
        URI locationOfNewCliente = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newEmpleado.getId())
            .toUri();

        return ResponseEntity.created(locationOfNewCliente)
            .body(newEmpleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable Long id, @RequestBody Empleado empleado) {
        var updateEmpleado = empleadoService.updateEmpleado(id, empleado);

        return ResponseEntity.ok(updateEmpleado);
    }
}
