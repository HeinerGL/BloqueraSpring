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

import com.project.bloquera.models.UnidadMedida;
import com.project.bloquera.services.UnidadMedidaService;

@RestController
@RequestMapping("/api/unidad-medida")
public class UnidadMedidaController {
    private final UnidadMedidaService unidadMedidaService;

    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public ResponseEntity<Page<UnidadMedida>> all(Pageable pageable) {
        var unidadesMedida = unidadMedidaService.getAllUnidadMedidas(pageable);
        return ResponseEntity.ok(unidadesMedida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedida> show(@PathVariable Long id) {
        var unidadMedida = unidadMedidaService.getUnidadMedidaById(id);

        return ResponseEntity.ok(unidadMedida);
    }

    @PostMapping
    public ResponseEntity<UnidadMedida> create(@RequestBody UnidadMedida unidadMedida) {
        var newUnidadMedida = unidadMedidaService.createUnidadMedida(unidadMedida);
        URI locationOfNewUnidadMedida = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newUnidadMedida.getId())
            .toUri();

        return ResponseEntity.created(locationOfNewUnidadMedida)
            .body(newUnidadMedida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedida> update(@PathVariable Long id, @RequestBody UnidadMedida unidadMedida) {
        var updateUnidadMedida = unidadMedidaService.updateUnidadMedida(id, unidadMedida);

        return ResponseEntity.ok(updateUnidadMedida);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        unidadMedidaService.deleteUnidadMedida(id);
    }
}
