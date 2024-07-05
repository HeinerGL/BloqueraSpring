package com.project.bloquera.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bloquera.models.EstadoOrden;

@Repository
public interface EstadoOrdenRepository extends CrudRepository<EstadoOrden, Long> {
}
