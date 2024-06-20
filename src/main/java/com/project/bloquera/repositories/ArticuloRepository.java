package com.project.bloquera.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.bloquera.models.Articulo;

public interface ArticuloRepository extends CrudRepository<Articulo, Long>, PagingAndSortingRepository<Articulo, Long> {
}
