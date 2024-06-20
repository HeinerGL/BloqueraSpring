package com.project.bloquera.services;

import com.project.bloquera.dtos.orden.OrdenCreateRequest;
import com.project.bloquera.dtos.orden.OrdenResponse;
import com.project.bloquera.exceptions.ResourceNotFoundException;
import com.project.bloquera.mappers.OrdenMapper;
import com.project.bloquera.models.Articulo;
import com.project.bloquera.models.Cliente;
import com.project.bloquera.models.Orden;
import com.project.bloquera.repositories.ArticuloRepository;
import com.project.bloquera.repositories.ClienteRepository;
import com.project.bloquera.repositories.OrdenRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {
    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository;
    private final ArticuloRepository articuloRepository;

    private final OrdenMapper ordenMapper;

    public static final String ORDEN_NOT_FOUND = "Orden #%d no encontrada";

    public OrdenService(OrdenRepository ordenRepository,
        ClienteRepository clienteRepository,
        ArticuloRepository articuloRepository,
        OrdenMapper ordenMapper) {
        this.ordenRepository = ordenRepository;
        this.clienteRepository = clienteRepository;
        this.articuloRepository = articuloRepository;

        this.ordenMapper = ordenMapper;
    }

    public Page<OrdenResponse> getAllOrdenes() {
        Pageable pageable = PageRequest.of(0, 3);
        return ordenRepository.findAll(pageable)
            .map(ordenMapper::modelToResponse);
    }

    public OrdenResponse getOrdenById(Long id) {
        return ordenRepository.findById(id)
            .map(ordenMapper::modelToResponse)
            .orElseThrow(() -> new ResourceNotFoundException(
                ORDEN_NOT_FOUND.formatted(id)));
    }

    public OrdenResponse createOrden(OrdenCreateRequest orden) {
        Orden newOrden = new Orden();

        Cliente cliente = clienteRepository.findById(orden.clienteId())
            .orElseThrow();
        newOrden.setCliente(cliente);

        orden.detalle().forEach(detalle -> {
            Articulo articulo = articuloRepository.findById(detalle.articuloId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    ProductoService.PRODUCTO_NOT_FOUND.formatted(detalle.articuloId())));

            if (articulo.getStock() >= detalle.cantidad()) {
                Double newStock = articulo.getStock() - detalle.cantidad();
                articulo.setStock(newStock);

                newOrden.addArticulo(detalle.cantidad(), articulo);
                articuloRepository.save(articulo);
            }
            // si no lanzar excepcion de producto sin stock
        });

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
                .orElseThrow(() -> new ResourceNotFoundException(
                    ORDEN_NOT_FOUND.formatted(id)));

        ordenRepository.delete(orden);
    }
}
