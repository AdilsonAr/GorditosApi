package com.dev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IngredientePorQuintal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="idMateriaPrima")
	private MateriaPrima materiaPrima;
	private double cantidadUnidades;
	@ManyToOne
	@JoinColumn(name="idConcentrado")
	private Concentrado concentrado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public double getCantidadUnidades() {
		return cantidadUnidades;
	}
	public void setCantidadUnidades(double cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}
	public Concentrado getConcentrado() {
		return concentrado;
	}
	public void setConcentrado(Concentrado concentrado) {
		this.concentrado = concentrado;
	}
	
}
