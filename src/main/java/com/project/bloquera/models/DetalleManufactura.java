package com.project.bloquera.models;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleManufactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double comision;

    private Double cantidad;

    private Instant fechaElaboracion  = Instant.now();

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto productoManufacturado;

    public DetalleManufactura() {}

    public DetalleManufactura(Long id, Instant fechaElaboracion, Empleado empleado, Producto producto) {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Instant getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Instant fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Producto getProductoManufacturado() {
        return productoManufacturado;
    }

    public void setProductoManufacturado(Producto productoManufacturado) {
        this.productoManufacturado = productoManufacturado;
    }
}
