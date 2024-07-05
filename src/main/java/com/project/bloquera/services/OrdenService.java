package com.project.bloquera.services;

import com.project.bloquera.dtos.orden.OrdenCreateRequest;
import com.project.bloquera.dtos.orden.OrdenResponse;
import com.project.bloquera.exceptions.notfound.ArticuloNotFoundException;
import com.project.bloquera.exceptions.notfound.ClienteNotFoundException;
import com.project.bloquera.exceptions.notfound.OrdenNotFoundException;
import com.project.bloquera.mappers.OrdenMapper;
import com.project.bloquera.models.Articulo;
import com.project.bloquera.models.Cliente;
import com.project.bloquera.models.EstadoOrden;
import com.project.bloquera.models.Orden;
import com.project.bloquera.repositories.ArticuloRepository;
import com.project.bloquera.repositories.ClienteRepository;
import com.project.bloquera.repositories.EstadoOrdenRepository;
import com.project.bloquera.repositories.OrdenRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {
    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository;
    private final ArticuloRepository articuloRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;

    private final OrdenMapper ordenMapper;

    public OrdenService(OrdenRepository ordenRepository,
        ClienteRepository clienteRepository,
        ArticuloRepository articuloRepository,
        EstadoOrdenRepository estadoOrdenRepository,
        OrdenMapper ordenMapper) {
        this.ordenRepository = ordenRepository;
        this.clienteRepository = clienteRepository;
        this.articuloRepository = articuloRepository;
        this.estadoOrdenRepository = estadoOrdenRepository;

        this.ordenMapper = ordenMapper;
    }

    public Page<OrdenResponse> getAllOrdenes(Pageable page) {
        return ordenRepository.findAll(page)
            .map(ordenMapper::modelToResponse);
    }

    public OrdenResponse getOrdenById(Long id) {
        return ordenRepository.findById(id)
            .map(ordenMapper::modelToResponse)
            .orElseThrow(() -> new OrdenNotFoundException(id));
    }

    public OrdenResponse createOrden(OrdenCreateRequest orden, String username) {
        Orden newOrden = new Orden();

        Cliente cliente = clienteRepository.findById(orden.clienteId())
            .orElseThrow(() -> new ClienteNotFoundException(orden.clienteId()));
        newOrden.setCliente(cliente);

        orden.detalle().forEach(detalle -> {
            Articulo articulo = articuloRepository.findById(detalle.articuloId())
                .orElseThrow(() -> new ArticuloNotFoundException(detalle.articuloId()));

            if (articulo.getStock() >= detalle.cantidad()) {
                newOrden.addArticulo(detalle.cantidad(), articulo);
            }
            // si no lanzar excepcion de producto sin stock
        });

        // asignar estado pendiente a la factura
        EstadoOrden estadoOrden = estadoOrdenRepository.findById(2L)
            .orElseThrow();

        newOrden.setEstadoOrden(estadoOrden);
        newOrden.setUsername(username);

        return ordenMapper.modelToResponse(ordenRepository.save(newOrden));
    }

    /*public OrdenResponse updateOrden(Long id, Orden orden) {
        boolean ordenExists = ordenRepository.existsById(id);

        if (!ordenExists) {
            throw new ResourceNotFoundException(ORDEN_NOT_FOUND.formatted(id));
        }

        orden.setId(id);

        return ordenMapper.modelToResponse(orden);
    }*/

    public void deleteOrden(Long id) {
        Orden orden = ordenRepository.findById(id)
            .orElseThrow(() -> new OrdenNotFoundException(id));

        EstadoOrden estadoOrden = estadoOrdenRepository.findById(1L)
            .orElseThrow();

        if (orden.getEstadoOrden().getId() == 2L) {
            orden.setEstadoOrden(estadoOrden);
            ordenRepository.save(orden);
        }
    }
}
