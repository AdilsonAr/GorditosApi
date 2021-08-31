package com.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.IngredientePorQuintal;
import com.dev.model.MateriaPrima;
import com.dev.model.Pedido;
import com.dev.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private MateriaPrimaService materiaService;
	
	@Autowired
	private ConcentradoService concentradoService;
	
	public void create(Pedido p) {
		p.setFecha(LocalDateTime.now());
		p.setEstado("en proceso");
		repo.save(p);
	}
	
	public List<Pedido> readAll(){
		return repo.findAll();
	}
	
	public double monto(int idPedido) {
		Pedido p = repo.findById(idPedido).get();
		double precioQuintal=p.getConcentrado().getPrecioPorQuintal();
		double cantidadQuintales=p.getQuintales();
		return precioQuintal*cantidadQuintales;
	}
	
	public boolean tieneMateriaPrima(double quintales, int idConcentrado) {
		boolean resultado=true;
		List<IngredientePorQuintal> ingredientes=concentradoService.readId(idConcentrado).getIngredientes();
		for(IngredientePorQuintal c: ingredientes) {
			if(c.getMateriaPrima().getCantidadUnidadesEnBodega()-c.getCantidadUnidades()*quintales<0) {
				resultado=false;
			}
		}
		return resultado;
	}
	
	public void descontarMateriaPrima(double quintales, int idConcentrado) {
		List<IngredientePorQuintal> ingredientes=concentradoService.readId(idConcentrado).getIngredientes();
		for(IngredientePorQuintal c: ingredientes) {
			MateriaPrima materia=c.getMateriaPrima();
			double update = materia.getCantidadUnidadesEnBodega()-c.getCantidadUnidades()*quintales;
			materia.setCantidadUnidadesEnBodega(update);
			materiaService.update(materia);
		}
	}
}
