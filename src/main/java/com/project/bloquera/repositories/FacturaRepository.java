package com.project.bloquera.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.bloquera.models.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Long>, PagingAndSortingRepository<Factura, Long> {
}
