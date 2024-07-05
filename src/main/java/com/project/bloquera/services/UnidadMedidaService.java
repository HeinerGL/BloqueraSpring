package com.project.bloquera.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.dtos.unidad.UnidadMedidaCreateRequest;
import com.project.bloquera.exceptions.notfound.UnidadMedidaNotFoundException;
import com.project.bloquera.mappers.UnidadMedidaMapper;
import com.project.bloquera.models.UnidadMedida;
import com.project.bloquera.repositories.UnidadMedidaRepository;

@Service
public class UnidadMedidaService {
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final UnidadMedidaMapper unidadMedidaMapper;

    public UnidadMedidaService(UnidadMedidaRepository unidadMedidaRepository,
        UnidadMedidaMapper unidadMedidaMapper) {
        this.unidadMedidaRepository = unidadMedidaRepository;
        this.unidadMedidaMapper = unidadMedidaMapper;
    }

    public Page<UnidadMedida> getAllUnidadMedidas(Pageable pageable) {
        return unidadMedidaRepository.findAll(pageable);
    }

    public Iterable<UnidadMedida> getListUnidadMedidas() {
        return unidadMedidaRepository.findAll();
    }

    public UnidadMedida getUnidadMedidaById(Long id) {
        return unidadMedidaRepository.findById(id)
            .orElseThrow(() -> new UnidadMedidaNotFoundException(id));
    }

    public UnidadMedida createUnidadMedida(UnidadMedidaCreateRequest unidadMedidaRequest) {
        return unidadMedidaRepository.save(unidadMedidaMapper.createRequestToModel(unidadMedidaRequest));
    }

    public UnidadMedida updateUnidadMedida(Long id, UnidadMedida unidadMedida) {
        boolean unidadMedidaExists = unidadMedidaRepository.existsById(id);

        if (!unidadMedidaExists) {
            throw new UnidadMedidaNotFoundException(id);
        }

        unidadMedida.setId(id);

        return unidadMedidaRepository.save(unidadMedida);
    }

    public void deleteUnidadMedida(Long id) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
            .orElseThrow(() -> new UnidadMedidaNotFoundException(id));

        unidadMedidaRepository.delete(unidadMedida);
    }
}
