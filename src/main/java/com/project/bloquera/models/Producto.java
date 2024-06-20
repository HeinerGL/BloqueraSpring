package com.project.bloquera.models;

import jakarta.persistence.Entity;

@Entity
public class Producto extends Articulo {

    public Producto() {
        super();
    }

    public Producto(Long id, String descripcion, Double precio, Double stock, Double stockMinimo) {
        super(id, descripcion, precio, stock, stockMinimo);
    }
}
