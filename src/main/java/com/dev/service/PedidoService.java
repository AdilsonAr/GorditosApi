package com.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Pedido;
import com.dev.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository repo;
	public void create(Pedido p) {
		p.setFecha(LocalDateTime.now());
		p.setEstado("en proceso");
		repo.save(p);
	}
	
	public List<Pedido> readAll(){
		return repo.findAll();
	}
}
