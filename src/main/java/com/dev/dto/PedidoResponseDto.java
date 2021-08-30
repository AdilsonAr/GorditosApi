package com.dev.dto;

import java.time.LocalDateTime;

import com.dev.model.Pedido;

public class PedidoResponseDto {
	private int id;
	private LocalDateTime fechaEntrega;
	private LocalDateTime fecha;
	private String estado;
	private String Cliente;
	private String Concentrado;
	private double quintales;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCliente() {
		return Cliente;
	}

	public void setCliente(String cliente) {
		Cliente = cliente;
	}

	public String getConcentrado() {
		return Concentrado;
	}

	public void setConcentrado(String concentrado) {
		Concentrado = concentrado;
	}

	public double getQuintales() {
		return quintales;
	}

	public void setQuintales(double quintales) {
		this.quintales = quintales;
	}

	public PedidoResponseDto(int id, LocalDateTime fechaEntrega, LocalDateTime fecha, String estado, String cliente,
			String concentrado, double quintales) {
		super();
		this.id = id;
		this.fechaEntrega = fechaEntrega;
		this.fecha = fecha;
		this.estado = estado;
		Cliente = cliente;
		Concentrado = concentrado;
		this.quintales = quintales;
	}
	
	public static PedidoResponseDto toDto(Pedido p) {
		return new PedidoResponseDto(p.getId(),p.getFechaEntrega(),p.getFecha(),p.getEstado(),
				p.getCliente().getNombre(),p.getConcentrado().getNombre(),p.getQuintales());
	}
}
