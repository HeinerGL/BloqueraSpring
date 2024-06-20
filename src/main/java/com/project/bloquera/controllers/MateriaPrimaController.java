package com.project.bloquera.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.bloquera.dtos.materiaprima.MateriaPrimaCreateRequest;
import com.project.bloquera.models.MateriaPrima;
import com.project.bloquera.services.MateriaPrimaService;

@RestController
@RequestMapping("/api/materia-prima")
public class MateriaPrimaController {
    private final MateriaPrimaService materiaPrimaService;

    public MateriaPrimaController(MateriaPrimaService materiaPrimaService) {
        this.materiaPrimaService = materiaPrimaService;
    }

    @GetMapping
    public ResponseEntity<Page<MateriaPrima>> all(Pageable pageable) {
        var materiasPrimas = materiaPrimaService.getAllMateriasPrimas(pageable);
        return ResponseEntity.ok(materiasPrimas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrima> show(@PathVariable Long id) {
        var materiaPrima = materiaPrimaService.getMateriaPrimaById(id);

        return ResponseEntity.ok(materiaPrima);
    }

    @PostMapping
    public ResponseEntity<MateriaPrima> create(@RequestBody MateriaPrimaCreateRequest materiaPrima) {
        var newMateriaPrima = materiaPrimaService.createMateriaPrima(materiaPrima);
        URI locationOfNewMateriaPrima = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newMateriaPrima.getId())
            .toUri();

        return ResponseEntity.created(locationOfNewMateriaPrima)
            .body(newMateriaPrima);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaPrima> update(@PathVariable Long id, @RequestBody MateriaPrima producto) {
        var updateMateriaPrima = materiaPrimaService.updateMateriaPrima(id, producto);

        return ResponseEntity.ok(updateMateriaPrima);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        materiaPrimaService.deleteMateriaPrima(id);
    }
}
