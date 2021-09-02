package com.dev.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Cliente;
import com.dev.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public List<Cliente> readAll(){
		return repo.findAll();
	}
	
	public Cliente readId (int id) throws NoSuchElementException{
		return repo.findById(id).orElseThrow();
	}
	
	public void create(Cliente c) {
		repo.save(c);
	}
}
