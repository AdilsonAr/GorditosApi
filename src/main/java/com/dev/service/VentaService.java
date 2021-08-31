package com.dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Cliente;
import com.dev.model.Venta;
import com.dev.repository.PedidoRepository;
import com.dev.repository.VentaRepository;

@Service
public class VentaService {
	@Autowired
	private VentaRepository repo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	public List<Venta> readByCliente(int id) {
		Cliente c =new Cliente();
		c.setId(id);
		List<Venta> lista=new ArrayList<>();
		(pedidoRepo.findByCliente(c)).forEach(x->{
			Venta venta=repo.findByPedido(x);
			if(venta!=null) {
				lista.add(venta);
			}
		});
		return lista;
	}
	
	public List<Venta> readByMes(int anio, int mes) {
		LocalDateTime despues=LocalDateTime.of(anio, mes, 1, 0, 0);
		LocalDateTime antes=despues.plusMonths(1);
		return repo.findByFechaAfterAndFechaBefore(despues, antes);
	}
	
	public void create(Venta v) {
		repo.save(v);
	}
	
	public List<Venta> readAll(){
		return repo.findAll();
	}
}
