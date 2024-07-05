package com.project.bloquera.services;

import com.project.bloquera.dtos.producto.ProductoCreateRequest;
import com.project.bloquera.exceptions.notfound.MateriaPrimaNotFoundException;
import com.project.bloquera.exceptions.notfound.ProductoNotFoundException;
import com.project.bloquera.mappers.ProductoMapper;
import com.project.bloquera.models.DetalleMateriaProducto;
import com.project.bloquera.models.MateriaPrima;
import com.project.bloquera.models.Producto;
import com.project.bloquera.repositories.MateriaPrimaRepository;
import com.project.bloquera.repositories.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final MateriaPrimaRepository materiaPrimaRepository;

    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository,
        MateriaPrimaRepository materiaPrimaRepository,
        ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.materiaPrimaRepository = materiaPrimaRepository;

        this.productoMapper = productoMapper;
    }

    public Page<Producto> getAllProductos(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    public Iterable<Producto> getListProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public Producto createProducto(ProductoCreateRequest productoRequest) {
        Producto producto = productoMapper.createRequestToModel(productoRequest);

        productoRequest.detalleMateriaPrima().forEach(detalleMateriaPrima -> {
            MateriaPrima materiaPrima = materiaPrimaRepository.findById(detalleMateriaPrima.materiaPrimaId())
                .orElseThrow(() -> new MateriaPrimaNotFoundException(detalleMateriaPrima.materiaPrimaId()));

            DetalleMateriaProducto detalleMateriaProducto = new DetalleMateriaProducto();
            detalleMateriaProducto.setCantidad(detalleMateriaPrima.cantidad());
            detalleMateriaProducto.setMateriaPrima(materiaPrima);

            producto.addDetalleMateriaProducto(detalleMateriaProducto);
        });

        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) {
        boolean productoExists = productoRepository.existsById(id);

        if (!productoExists) {
            throw new ProductoNotFoundException(id);
        }

        producto.setId(id);

        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        productoRepository.delete(producto);
    }
}
