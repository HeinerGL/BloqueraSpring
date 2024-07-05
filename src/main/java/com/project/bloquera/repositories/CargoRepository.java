package com.project.bloquera.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.bloquera.models.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long>, PagingAndSortingRepository<Cargo, Long> {
}
