package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.MateriaPrima;

@Repository
public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Integer>{

}
