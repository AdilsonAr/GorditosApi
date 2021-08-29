package com.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.MateriaPrima;
import com.dev.repository.MateriaPrimaRepository;

@Service
public class MateriaPrimaService {
	@Autowired
	MateriaPrimaRepository repo;
	public List<MateriaPrima> readAll(){
		return repo.findAll();
	}
}
