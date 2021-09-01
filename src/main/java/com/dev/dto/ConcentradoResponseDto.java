package com.dev.dto;

import com.dev.model.Concentrado;

public class ConcentradoResponseDto {
	private int id;
	private String nombre;
	private double precioPorQuintal;
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
	public double getPrecioPorQuintal() {
		return precioPorQuintal;
	}
	public void setPrecioPorQuintal(double precioPorQuintal) {
		this.precioPorQuintal = precioPorQuintal;
	}
	public ConcentradoResponseDto(int id, String nombre, double precioPorQuintal) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioPorQuintal = precioPorQuintal;
	}
	public static ConcentradoResponseDto toDto(Concentrado c) {
		return new ConcentradoResponseDto(c.getId(), c.getNombre(),c.getPrecioPorQuintal());
	}
}
