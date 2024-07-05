package com.project.bloquera.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.bloquera.models.Empleado;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long>, PagingAndSortingRepository<Empleado, Long> {
}
