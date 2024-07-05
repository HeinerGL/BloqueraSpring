package com.project.bloquera.models;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant fecha = Instant.now();

    private Double total = 0.0;

    private String username;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "estado_orden_id", referencedColumnName = "id")
    private EstadoOrden estadoOrden;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    private List<DetalleOrden> detalleOrden;

    private Boolean active = true;

    public Orden() {
        this.detalleOrden = new ArrayList<>();
    }

    public Orden(Long id, Double total, String username, Cliente cliente, List<DetalleOrden> detalleOrden, EstadoOrden estadoOrden) {
        this.id = id;
        this.total = total;
        this.username = username;
        this.cliente = cliente;
        this.detalleOrden = detalleOrden;
        this.estadoOrden = estadoOrden;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
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
