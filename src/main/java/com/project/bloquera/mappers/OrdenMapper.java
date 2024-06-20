package com.project.bloquera.mappers;

import com.project.bloquera.dtos.orden.DetalleOrdenResponse;
import com.project.bloquera.dtos.orden.OrdenResponse;
import com.project.bloquera.models.Articulo;
import com.project.bloquera.models.DetalleOrden;
import com.project.bloquera.models.Orden;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdenMapper {

    public OrdenResponse modelToResponse(Orden orden) {
        List<DetalleOrdenResponse> detalle = orden.getDetalleOrden()
                .stream().map(this::detalleModelToResponse)
                .toList();

        return new OrdenResponse(
                orden.getId(),
                orden.getFecha(),
                orden.getTotal(),
                orden.getCliente(),
                detalle
        );
    }

    public DetalleOrdenResponse detalleModelToResponse(DetalleOrden detalleOrden) {
        Articulo articulo = detalleOrden.getArticulo();
        return new DetalleOrdenResponse(
                detalleOrden.getId(),
                articulo.getDescripcion(),
                detalleOrden.getCantidad(),
                detalleOrden.getPrecioUnitario()
        );
    }
}
