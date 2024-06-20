package com.project.bloquera.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.bloquera.models.UnidadMedida;

@Repository
public interface UnidadMedidaRepository extends CrudRepository<UnidadMedida, Long>, PagingAndSortingRepository<UnidadMedida, Long> {
}
