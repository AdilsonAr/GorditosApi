package com.dev.dto;

import java.time.LocalDateTime;

import com.dev.model.Compra;
import com.dev.model.MateriaPrima;

public class CompraRequestDto {
	private LocalDateTime fecha;
	private double cantidad;
	private int materiaPrima;
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public int getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(int materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public CompraRequestDto(LocalDateTime fecha, double cantidad, int materiaPrima) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.materiaPrima = materiaPrima;
	}
	public static Compra toMOdel(CompraRequestDto c) {
		MateriaPrima m=new MateriaPrima();
		m.setId(c.getMateriaPrima());
		return new Compra(c.getFecha(),c.getCantidad(),m);
	}
}
