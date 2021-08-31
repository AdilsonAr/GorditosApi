package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Concentrado;

@Repository
public interface ConcentradoRepository extends JpaRepository<Concentrado, Integer>{

}
