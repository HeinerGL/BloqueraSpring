package com.project.bloquera.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.bloquera.dtos.cargo.CargoCreateRequest;
import com.project.bloquera.models.Cargo;
import com.project.bloquera.services.CargoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cargo")
public class CargoController {
    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<Page<Cargo>> all(Pageable pageable) {
        var cargos = cargoService.getAllCargos(pageable);
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> show(@PathVariable Long id) {
        var cargo = cargoService.getCargoById(id);

        return ResponseEntity.ok(cargo);
    }

    @PostMapping
    public ResponseEntity<Cargo> store(@Valid @RequestBody CargoCreateRequest cargo) {
        var newCargo = cargoService.createCargo(cargo);
        URI locationOfNewCargo = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newCargo.getId())
            .toUri();

        return ResponseEntity.created(locationOfNewCargo)
            .body(newCargo);
    }
}
