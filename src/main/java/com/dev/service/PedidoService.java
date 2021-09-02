package com.dev.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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
	
	@Autowired
	private ClienteService clienteService;
	
	public void create(Pedido p) throws NoSuchElementException{
		clienteService.readId(p.getCliente().getId());
		p.setFecha(LocalDateTime.now());
		p.setEstado("en proceso");
		repo.save(p);
	}
	
	public void update(Pedido p) {
		repo.save(p);
	}
	
	public List<Pedido> readAll(){
		return repo.findAll();
	}
	
	public Pedido readId(int id) throws NoSuchElementException{
		return repo.findById(id).orElseThrow();
	}
	
	public double monto(int idPedido) throws NoSuchElementException{
		Pedido p = readId(idPedido);
		double precioQuintal=p.getConcentrado().getPrecioPorQuintal();
		double cantidadQuintales=p.getQuintales();
		return precioQuintal*cantidadQuintales;
	}
	
	public List<Pedido> readByMes(int anio, int mes) {
		LocalDateTime despues=LocalDateTime.of(anio, mes, 1, 0, 0);
		LocalDateTime antes=despues.plusMonths(1);
		return repo.findByFechaAfterAndFechaBefore(despues, antes);
	}
	
	public boolean tieneMateriaPrima(double quintales, int idConcentrado) throws NoSuchElementException{
		boolean resultado=true;
		List<IngredientePorQuintal> ingredientes=concentradoService.readId(idConcentrado).getIngredientes();
		for(IngredientePorQuintal c: ingredientes) {
			if(c.getMateriaPrima().getCantidadUnidadesEnBodega()-c.getCantidadUnidades()*quintales<0) {
				resultado=false;
			}
		}
		return resultado;
	}
	
	public void descontarMateriaPrima(double quintales, int idConcentrado) throws NoSuchElementException{
		List<IngredientePorQuintal> ingredientes=concentradoService.readId(idConcentrado).getIngredientes();
		for(IngredientePorQuintal c: ingredientes) {
			MateriaPrima materia=c.getMateriaPrima();
			double update = materia.getCantidadUnidadesEnBodega()-c.getCantidadUnidades()*quintales;
			materia.setCantidadUnidadesEnBodega(update);
			materiaService.update(materia);
		}
	}
}
