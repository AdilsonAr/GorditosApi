package com.dev.dto;

import com.dev.model.MateriaPrima;

public class MateriaPrimaResponseDto {
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
	public MateriaPrimaResponseDto(int id, String nombre, String unidades, double costoPorUnidad,
			double cantidadUnidadesEnBodega) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.unidades = unidades;
		this.costoPorUnidad = costoPorUnidad;
		this.cantidadUnidadesEnBodega = cantidadUnidadesEnBodega;
	}
	
	public static MateriaPrimaResponseDto toDto(MateriaPrima m) {
		return new MateriaPrimaResponseDto(m.getId(),m.getNombre(),m.getUnidades(),
				m.getCostoPorUnidad(),m.getCantidadUnidadesEnBodega());
	}
	
}
