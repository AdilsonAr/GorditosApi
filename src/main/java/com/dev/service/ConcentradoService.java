package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Concentrado;
import com.dev.repository.ConcentradoRepository;

@Service
public class ConcentradoService {
	@Autowired
	private ConcentradoRepository repo;
	public Concentrado readId(int id) {
		return repo.findById(id).get();
	}
}
