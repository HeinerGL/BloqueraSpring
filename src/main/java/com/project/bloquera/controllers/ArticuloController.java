package com.project.bloquera.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bloquera.models.Articulo;
import com.project.bloquera.services.ArticuloService;

@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {
    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public ResponseEntity<Page<Articulo>> all(Pageable pageable) {
        var productos = articuloService.getAllArticulos(pageable);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> show(@PathVariable Long id) {
        var producto = articuloService.getArticuloById(id);

        return ResponseEntity.ok(producto);
    }
}
