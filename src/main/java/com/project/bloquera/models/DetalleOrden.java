package com.project.bloquera.models;

import jakarta.persistence.*;

@Entity
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cantidad;

    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "articulo_id", referencedColumnName = "id")
    private Articulo articulo;

    public DetalleOrden() {}

    public DetalleOrden(Long id, Double cantidad, Double precioUnitario, Articulo articulo) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.articulo = articulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precio) {
        this.precioUnitario = precio;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
