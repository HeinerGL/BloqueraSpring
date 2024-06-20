package com.project.bloquera.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.dtos.materiaprima.MateriaPrimaCreateRequest;
import com.project.bloquera.exceptions.ResourceNotFoundException;
import com.project.bloquera.mappers.MateriaPrimaMapper;
import com.project.bloquera.models.MateriaPrima;
import com.project.bloquera.models.UnidadMedida;
import com.project.bloquera.repositories.MateriaPrimaRepository;
import com.project.bloquera.repositories.UnidadMedidaRepository;

@Service
public class MateriaPrimaService {
    private final MateriaPrimaRepository materiaPrimaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    private final MateriaPrimaMapper materiaPrimaMapper;

    public static final String MATERIA_PRIMA_NOT_FOUND = "Materia Prima #%d no encontrado";

    public MateriaPrimaService(MateriaPrimaRepository materiaPrimaRepository,
        UnidadMedidaRepository unidadMedidaRepository,
        MateriaPrimaMapper materiaPrimaMapper) {
        this.materiaPrimaRepository = materiaPrimaRepository;
        this.unidadMedidaRepository = unidadMedidaRepository;

        this.materiaPrimaMapper = materiaPrimaMapper;
    }

    public Page<MateriaPrima> getAllMateriasPrimas(Pageable pageable) {
        return materiaPrimaRepository.findAll(pageable);
    }

    public MateriaPrima getMateriaPrimaById(Long id) {
        return materiaPrimaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                MATERIA_PRIMA_NOT_FOUND.formatted(id)));
    }

    public MateriaPrima createMateriaPrima(MateriaPrimaCreateRequest materiaPrimaRequest) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(materiaPrimaRequest.unidadMedidaId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Unidad de medida %d no encontrada".formatted(materiaPrimaRequest.unidadMedidaId())));
        
        MateriaPrima materiaPrima = materiaPrimaMapper.CreateRequestToModel(materiaPrimaRequest);
        materiaPrima.setUnidadMedida(unidadMedida);

        return materiaPrimaRepository.save(materiaPrima);
    }

    public MateriaPrima updateMateriaPrima(Long id, MateriaPrima materiaPrima) {
        boolean materiaPrimaExists = materiaPrimaRepository.existsById(id);

        if (!materiaPrimaExists) {
            throw new ResourceNotFoundException(MATERIA_PRIMA_NOT_FOUND.formatted(id));
        }

        materiaPrima.setId(id);

        return materiaPrimaRepository.save(materiaPrima);
    }

    public void deleteMateriaPrima(Long id) {
        MateriaPrima materiaPrima = materiaPrimaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                MATERIA_PRIMA_NOT_FOUND.formatted(id)));

        materiaPrimaRepository.delete(materiaPrima);
    }
}
