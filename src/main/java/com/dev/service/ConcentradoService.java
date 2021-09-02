package com.dev.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Concentrado;
import com.dev.repository.ConcentradoRepository;

@Service
public class ConcentradoService {
	@Autowired
	private ConcentradoRepository repo;
	
	public Concentrado readId(int id) throws NoSuchElementException{
		return repo.findById(id).orElseThrow();
	}
	
	public List<Concentrado> readAll(){
		return repo.findAll();
	}
}
