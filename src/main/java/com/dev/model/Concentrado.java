package com.dev.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Concentrado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private double precioPorQuintal;
	@OneToMany(mappedBy = "concentrado")
	private List<IngredientePorQuintal> ingredientes;
	@OneToMany(mappedBy = "concentrado")
	private List<Pedido> pedidos;
	
	public double getPrecioPorQuintal() {
		return precioPorQuintal;
	}
	public void setPrecioPorQuintal(double precioPorQuintal) {
		this.precioPorQuintal = precioPorQuintal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<IngredientePorQuintal> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<IngredientePorQuintal> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
