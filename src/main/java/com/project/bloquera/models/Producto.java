package com.project.bloquera.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Producto extends Articulo {
    private Double comisionPorProducto;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalle_materia_producto_id", referencedColumnName = "id")
    private List<DetalleMateriaProducto> detalleMateriaProductos;

    public Producto() {
        super();
        this.detalleMateriaProductos = new ArrayList<>();
    }

    public Producto(Long id, String descripcion, Double precio, Double stock, Double stockMinimo, Double comisionPorProducto, List<DetalleMateriaProducto> detalleMateriaProductos) {
        super(id, descripcion, precio, stock, stockMinimo);
        this.comisionPorProducto = comisionPorProducto;
        this.detalleMateriaProductos = detalleMateriaProductos;
    }

    public Double getComisionPorProducto() {
        return comisionPorProducto;
    }

    public void setComisionPorProducto(Double comisionPorProducto) {
        this.comisionPorProducto = comisionPorProducto;
    }

    public List<DetalleMateriaProducto> getDetalleMateriaProducto() {
        return detalleMateriaProductos;
    }

    public void setDetalleMateriaProducto(List<DetalleMateriaProducto> detalleMateriaProductos) {
        this.detalleMateriaProductos = detalleMateriaProductos;
    }

    public void addDetalleMateriaProducto(DetalleMateriaProducto detalleMateriaProducto) {
        this.detalleMateriaProductos.add(detalleMateriaProducto);
    }
}
