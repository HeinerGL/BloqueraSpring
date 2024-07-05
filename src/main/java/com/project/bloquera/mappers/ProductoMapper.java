package com.project.bloquera.mappers;

import org.springframework.stereotype.Component;

import com.project.bloquera.dtos.producto.ProductoCreateRequest;
import com.project.bloquera.models.Producto;

@Component
public class ProductoMapper {

    public Producto createRequestToModel(ProductoCreateRequest createRequest) {
        Producto producto = new Producto();
        producto.setDescripcion(createRequest.descripcion());
        producto.setPrecio(createRequest.precio());
        producto.setStock(createRequest.stock());
        producto.setStockMinimo(createRequest.stockMinimo());
        producto.setComisionPorProducto(createRequest.comisionPorProducto());

        return producto;
    }
}
