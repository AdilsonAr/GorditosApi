package com.dev.dto;

import java.time.format.DateTimeFormatter;

import com.dev.model.Venta;

public class VentaResponseDto {
	private int id;
	private int idPedido;
	private String fecha;
	private double monto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public VentaResponseDto(int id, int idPedido, String fecha, double monto) {
		super();
		this.id = id;
		this.idPedido = idPedido;
		this.fecha = fecha;
		this.monto = monto;
	}
	
	public static VentaResponseDto toDto(Venta v) {
		return new VentaResponseDto(v.getId(),v.getPedido().getId(),
				v.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
				v.getMonto());
	}
	
}
