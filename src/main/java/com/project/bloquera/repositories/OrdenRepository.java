package com.project.bloquera.repositories;

import com.project.bloquera.models.Orden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends CrudRepository<Orden, Long>, PagingAndSortingRepository<Orden, Long> {
}
