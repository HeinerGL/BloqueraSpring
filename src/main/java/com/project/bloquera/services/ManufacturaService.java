package com.project.bloquera.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.dtos.manufactura.DetalleManufacturaCreateRequest;
import com.project.bloquera.exceptions.notfound.EmpleadoNotFoundException;
import com.project.bloquera.exceptions.notfound.ProductoNotFoundException;
import com.project.bloquera.models.DetalleManufactura;
import com.project.bloquera.models.Empleado;
import com.project.bloquera.models.MateriaPrima;
import com.project.bloquera.models.Producto;
import com.project.bloquera.repositories.DetalleManufacturaRepository;
import com.project.bloquera.repositories.EmpleadoRepository;
import com.project.bloquera.repositories.MateriaPrimaRepository;
import com.project.bloquera.repositories.ProductoRepository;

@Service
public class ManufacturaService {
    private final DetalleManufacturaRepository detalleManufacturaRepository;
    private final ProductoRepository productoRepository;
    private final MateriaPrimaRepository materiaPrimaRepository;
    private final EmpleadoRepository empleadoRepository;

    public ManufacturaService(DetalleManufacturaRepository detalleManufacturaRepository,
        ProductoRepository productoRepository,
        MateriaPrimaRepository materiaPrimaRepository,
        EmpleadoRepository empleadoRepository) {
        this.detalleManufacturaRepository = detalleManufacturaRepository;
        this.productoRepository = productoRepository;
        this.materiaPrimaRepository = materiaPrimaRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Page<DetalleManufactura> getAllDetalleManufacturas(Pageable page) {
        return detalleManufacturaRepository.findAll(page);
    }

    public List<DetalleManufactura> fabricarProducto(DetalleManufacturaCreateRequest detalleManufactura) {
        List<DetalleManufactura> detalleList = new ArrayList<>();

        Producto producto = productoRepository.findById(detalleManufactura.productoId())
            .orElseThrow(() -> new ProductoNotFoundException(detalleManufactura.productoId()));
            
        Empleado empleado = empleadoRepository.findById(detalleManufactura.empleadoId())
            .orElseThrow(() -> new EmpleadoNotFoundException(detalleManufactura.empleadoId()));

        DetalleManufactura detalle = new DetalleManufactura();
        detalle.setComision(producto.getComisionPorProducto() * detalleManufactura.cantidad());
        detalle.setEmpleado(empleado);
        detalle.setCantidad(detalleManufactura.cantidad());
        detalle.setProductoManufacturado(producto);

        detalleList.add(detalleManufacturaRepository.save(detalle));

        Double cantidad = producto.getStock() + detalleManufactura.cantidad();
        producto.setStock(cantidad);

        producto.getDetalleMateriaProducto().forEach(detalleMateriaProducto -> {
            MateriaPrima materiaPrima = detalleMateriaProducto.getMateriaPrima();

            Double stockRestante = materiaPrima.getStock() - detalleManufactura.cantidad() * detalleMateriaProducto.getCantidad();

            if (stockRestante > 0) {
                materiaPrima.setStock(stockRestante);
                materiaPrimaRepository.save(materiaPrima);
            }
        });

        productoRepository.save(producto);

        return detalleList;
    }
}
