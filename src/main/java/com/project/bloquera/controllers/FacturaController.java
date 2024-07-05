package com.project.bloquera.controllers;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.bloquera.dtos.factura.FacturaCreateRequest;
import com.project.bloquera.models.Factura;
import com.project.bloquera.services.FacturaService;

import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<Page<Factura>> all(Pageable page) {
        var facturas = facturaService.getAllFacturas(page);
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> show(@PathVariable Long id) {
        var factura = facturaService.getFacturaById(id);
        return ResponseEntity.ok(factura);
    }

    @PostMapping
    public ResponseEntity<Factura> store(@Valid @RequestBody FacturaCreateRequest facturaRequest, @AuthenticationPrincipal Jwt jwt) {
        var factura = facturaService.facturar(facturaRequest, jwt.getSubject());
        URI locationOfNewFactura = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(factura.getId())
            .toUri();

        return ResponseEntity.created(locationOfNewFactura)
            .body(factura);
    }

    @GetMapping("print/{id}")
    public ResponseEntity<Resource> print(@PathVariable Long id) throws JRException, SQLException, IOException {
        byte[] array = facturaService.awa(id);
        Resource streamResource = new ByteArrayResource(array);

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=\"factura.pdf\"")
                .contentType(MediaType.APPLICATION_PDF).body(streamResource);
    }
}
