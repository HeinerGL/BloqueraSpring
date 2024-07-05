package com.project.bloquera.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bloquera.dtos.manufactura.DetalleManufacturaCreateRequest;
import com.project.bloquera.models.DetalleManufactura;
import com.project.bloquera.services.ManufacturaService;

@RestController
@RequestMapping("/api/manufactura")
public class ManufacturaController {
    private final ManufacturaService manufacturaService;

    public ManufacturaController(ManufacturaService manufacturaService) {
        this.manufacturaService = manufacturaService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalleManufactura>> all(Pageable page) {
        var detalleManufactura = manufacturaService.getAllDetalleManufacturas(page);
        return ResponseEntity.ok(detalleManufactura);
    }

    @PostMapping
    public ResponseEntity<List<DetalleManufactura>> store(@RequestBody DetalleManufacturaCreateRequest detalleManufactura) {
        var newManufactura = manufacturaService.fabricarProducto(detalleManufactura);

        return ResponseEntity.created(null)
            .body(newManufactura);
    }
}
