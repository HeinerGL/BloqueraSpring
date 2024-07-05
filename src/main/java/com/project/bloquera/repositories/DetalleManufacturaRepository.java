package com.project.bloquera.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.bloquera.models.DetalleManufactura;

@Repository
public interface DetalleManufacturaRepository extends CrudRepository<DetalleManufactura, Long>, PagingAndSortingRepository<DetalleManufactura, Long> {
}
