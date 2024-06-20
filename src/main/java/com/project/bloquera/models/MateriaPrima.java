package com.project.bloquera.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MateriaPrima extends Articulo {
    @ManyToOne
    @JoinColumn(name = "unidad_medida_id", referencedColumnName = "id")
    private UnidadMedida unidadMedida;

    public MateriaPrima() {
        super();
    }

    public MateriaPrima(Long id, String descripcion, Double precio, Double stock, Double stockMinimo) {
        super(id, descripcion, precio, stock, stockMinimo);
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
