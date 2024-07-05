package com.project.bloquera.controllers;

import com.project.bloquera.dtos.producto.ProductoCreateRequest;
import com.project.bloquera.models.Producto;
import com.project.bloquera.services.ProductoService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<Page<Producto>> all(Pageable pageable) {
        var productos = productoService.getAllProductos(pageable);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Producto>> list() {
        var productos = productoService.getListProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> show(@PathVariable Long id) {
        var producto = productoService.getProductoById(id);

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> create(@Valid @RequestBody ProductoCreateRequest producto) {
        var newProducto = productoService.createProducto(producto);
        URI locationOfNewProducto = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newProducto.getId())
            .toUri();

        return ResponseEntity.created(locationOfNewProducto)
            .body(newProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto) {
        var updateProducto = productoService.updateProducto(id, producto);

        return ResponseEntity.ok(updateProducto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }
}
