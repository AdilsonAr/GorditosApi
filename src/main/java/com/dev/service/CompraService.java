package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Compra;
import com.dev.model.MateriaPrima;
import com.dev.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	private CompraRepository repo;
	@Autowired
	private MateriaPrimaService serviceMateria;
	
	public void create(Compra c) {
		MateriaPrima m = serviceMateria.findId(c.getMateriaPrima().getId());
		m.setCantidadUnidadesEnBodega(m.getCantidadUnidadesEnBodega()+c.getCantidad());
		repo.save(c);
		serviceMateria.update(m);
	}
}
