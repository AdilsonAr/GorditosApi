package com.dev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Cliente;
import com.dev.model.Pedido;
import com.dev.model.Venta;
import com.dev.repository.PedidoRepository;
import com.dev.repository.VentaRepository;

@Service
public class VentaService {
	@Autowired
	private VentaRepository repo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PedidoService pedidoService;
	
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
	
	public void create(Venta v) throws NoSuchElementException{
		Pedido p=pedidoService.readId(v.getPedido().getId());
		p.setEstado("entregado");
		repo.save(v);
		pedidoService.update(p);
	}
	
	public List<Venta> readAll(){
		return repo.findAll();
	}
	
	public Venta readId(int id) throws NoSuchElementException{
		return repo.findById(id).orElseThrow();
	}
}
