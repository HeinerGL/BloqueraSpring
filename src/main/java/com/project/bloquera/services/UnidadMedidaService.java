package com.project.bloquera.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.exceptions.ResourceNotFoundException;
import com.project.bloquera.models.UnidadMedida;
import com.project.bloquera.repositories.UnidadMedidaRepository;

@Service
public class UnidadMedidaService {
    private final UnidadMedidaRepository unidadMedidaRepository;

    public static final String UNIDAD_MEDIDA_NOT_FOUND = "Unidad medida #%d no encontrada";

    public UnidadMedidaService(UnidadMedidaRepository unidadMedidaRepository) {
        this.unidadMedidaRepository = unidadMedidaRepository;
    }

    public Page<UnidadMedida> getAllUnidadMedidas(Pageable pageable) {
        return unidadMedidaRepository.findAll(pageable);
    }

    public UnidadMedida getUnidadMedidaById(Long id) {
        return unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    UNIDAD_MEDIDA_NOT_FOUND.formatted(id)));
    }

    public UnidadMedida createUnidadMedida(UnidadMedida unidadMedidaRequest) {
        return unidadMedidaRepository.save(unidadMedidaRequest);
    }

    public UnidadMedida updateUnidadMedida(Long id, UnidadMedida unidadMedida) {
        boolean unidadMedidaExists = unidadMedidaRepository.existsById(id);

        if (!unidadMedidaExists) {
            throw new ResourceNotFoundException(UNIDAD_MEDIDA_NOT_FOUND.formatted(id));
        }

        unidadMedida.setId(id);

        return unidadMedidaRepository.save(unidadMedida);
    }

    public void deleteUnidadMedida(Long id) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                UNIDAD_MEDIDA_NOT_FOUND.formatted(id)));

        unidadMedidaRepository.delete(unidadMedida);
    }
}
