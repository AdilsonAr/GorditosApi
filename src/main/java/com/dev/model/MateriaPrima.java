package com.dev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MateriaPrima {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String unidades;
	private double costoPorUnidad;
	private double cantidadUnidadesEnBodega;
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
	public String getUnidades() {
		return unidades;
	}
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
	public double getCostoPorUnidad() {
		return costoPorUnidad;
	}
	public void setCostoPorUnidad(double costoPorUnidad) {
		this.costoPorUnidad = costoPorUnidad;
	}
	public double getCantidadUnidadesEnBodega() {
		return cantidadUnidadesEnBodega;
	}
	public void setCantidadUnidadesEnBodega(double cantidadUnidadesEnBodega) {
		this.cantidadUnidadesEnBodega = cantidadUnidadesEnBodega;
	}
	
}
