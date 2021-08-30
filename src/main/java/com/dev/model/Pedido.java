package com.dev.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime fecha;
	private LocalDateTime fechaEntrega;
	private String estado;
	private double quintales;
	@ManyToOne
	@JoinColumn(name="idConcentrado")
	private Concentrado concentrado;
	@OneToOne(mappedBy = "pedido")
	private Venta venta;
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getQuintales() {
		return quintales;
	}
	public void setQuintales(double quintales) {
		this.quintales = quintales;
	}
	public Concentrado getConcentrado() {
		return concentrado;
	}
	public void setConcentrado(Concentrado concentrado) {
		this.concentrado = concentrado;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pedido(LocalDateTime fechaEntrega, double quintales, Concentrado concentrado, Cliente cliente) {
		super();
		this.fechaEntrega = fechaEntrega;
		this.quintales = quintales;
		this.concentrado = concentrado;
		this.cliente = cliente;
	}
	
	
}
