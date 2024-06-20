package com.project.bloquera.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.exceptions.ResourceNotFoundException;
import com.project.bloquera.models.Articulo;
import com.project.bloquera.repositories.ArticuloRepository;

@Service
public class ArticuloService {
    private final ArticuloRepository articuloRepository;

    public static final String ARTICULO_NOT_FOUND = "Articulo #%d no encontrado";

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public Page<Articulo> getAllArticulos(Pageable pageable) {
        return articuloRepository.findAll(pageable);
    }

    public Articulo getArticuloById(Long id) {
        return articuloRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                ARTICULO_NOT_FOUND.formatted(id)));
    }
}
