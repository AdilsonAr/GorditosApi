package com.dev.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.MateriaPrima;
import com.dev.repository.MateriaPrimaRepository;

@Service
public class MateriaPrimaService {
	@Autowired
	private MateriaPrimaRepository repo;
	public List<MateriaPrima> readAll(){
		return repo.findAll();
	}
	
	public void update(MateriaPrima m) {
		repo.save(m);
	}
	
	public MateriaPrima findId (int id) throws NoSuchElementException{
		return repo.findById(id).orElseThrow();
	}
}
