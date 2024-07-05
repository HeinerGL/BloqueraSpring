package com.project.bloquera.controllers;

import com.project.bloquera.dtos.orden.OrdenCreateRequest;
import com.project.bloquera.dtos.orden.OrdenResponse;
import com.project.bloquera.services.OrdenService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/orden")
public class OrdenController {
    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public ResponseEntity<Page<OrdenResponse>> all(Pageable page) {
        var ordenes = ordenService.getAllOrdenes(page);
        return ResponseEntity.ok(ordenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponse> show(@PathVariable Long id) {
        var orden = ordenService.getOrdenById(id);

        return ResponseEntity.ok(orden);
    }

    @PostMapping
    public ResponseEntity<OrdenResponse> create(@Valid @RequestBody OrdenCreateRequest orden, @AuthenticationPrincipal Jwt jwt) {
        var newOrden = ordenService.createOrden(orden, jwt.getSubject());
        URI locationOfNewOrden = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newOrden.id())
            .toUri();

        return ResponseEntity.created(locationOfNewOrden)
            .body(newOrden);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<OrdenResponse> update(@PathVariable Long id, @RequestBody Orden orden) {
        var updateOrden = ordenService.updateOrden(id, orden);

        return ResponseEntity.ok(updateOrden);
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ordenService.deleteOrden(id);
    }
}
