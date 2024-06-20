package com.project.bloquera.services;

import com.project.bloquera.dtos.producto.ProductoCreateRequest;
import com.project.bloquera.exceptions.ResourceNotFoundException;
import com.project.bloquera.mappers.ProductoMapper;
import com.project.bloquera.models.Producto;
import com.project.bloquera.repositories.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    private final ProductoMapper productoMapper;

    public static final String PRODUCTO_NOT_FOUND = "Producto #%d no encontrado";

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;

        this.productoMapper = productoMapper;
    }

    public Page<Producto> getAllProductos(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                PRODUCTO_NOT_FOUND.formatted(id)));
    }

    public Producto createProducto(ProductoCreateRequest productoRequest) {
        Producto producto = productoMapper.CreateRequestToModel(productoRequest);
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) {
        boolean productoExists = productoRepository.existsById(id);

        if (!productoExists) {
            throw new ResourceNotFoundException(PRODUCTO_NOT_FOUND.formatted(id));
        }

        producto.setId(id);

        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PRODUCTO_NOT_FOUND.formatted(id)));

        productoRepository.delete(producto);
    }
}
