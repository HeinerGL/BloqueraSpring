package com.project.bloquera.models;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE factura SET active = false WHERE id = ?")
@SQLRestriction("active = true")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant fecha = Instant.now();

    private Double subtotal;

    private Double total = 0.0;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    private List<DetalleOrden> detalleOrden;

    private Boolean active = true;

    public Orden() {
        this.detalleOrden = new ArrayList<>();
    }

    public Orden(Long id, Double subtotal, Double total, Cliente cliente, List<DetalleOrden> detalleOrden, Boolean active) {
        this.id = id;
        this.subtotal = subtotal;
        this.total = total;
        this.cliente = cliente;
        this.detalleOrden = detalleOrden;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void addArticulo(Double cantidad, Articulo articulo) {
        Double precio = articulo.getPrecio();
        DetalleOrden detalle = new DetalleOrden(null, cantidad, precio, articulo);

        total += cantidad * precio;
        detalleOrden.add(detalle);
    }
}
