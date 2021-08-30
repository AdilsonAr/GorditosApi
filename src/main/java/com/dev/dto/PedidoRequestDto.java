package com.dev.dto;

import java.time.LocalDateTime;

import com.dev.model.Cliente;
import com.dev.model.Concentrado;
import com.dev.model.Pedido;

public class PedidoRequestDto {
	private LocalDateTime fechaEntrega;
	private int idCliente;
	private int idConcentrado;
	private double quintales;
	
	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdConcentrado() {
		return idConcentrado;
	}
	public void setIdConcentrado(int idConcentrado) {
		this.idConcentrado = idConcentrado;
	}
	public double getQuintales() {
		return quintales;
	}
	public void setQuintales(double quintales) {
		this.quintales = quintales;
	}
	
	public static Pedido toModel(PedidoRequestDto p) {
		Cliente cliente=new Cliente();
		cliente.setId(p.getIdCliente());
		Concentrado concentrado=new Concentrado();
		concentrado.setId(p.getIdConcentrado());
		return new Pedido(p.getFechaEntrega(), p.getQuintales(),concentrado,cliente);
	}
}
