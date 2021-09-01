package com.dev.dto;

import com.dev.model.Cliente;

public class ClienteRequestDto {
	private String nombre;
	private String nit;
	private String direccion;
	private String telefono;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public ClienteRequestDto(String nombre, String nit, String direccion, String telefono) {
		super();
		this.nombre = nombre;
		this.nit = nit;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	public ClienteRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Cliente toMOdel(ClienteRequestDto c) {
		return new Cliente(c.getNombre(), c.getNit(),c.getDireccion(),c.getTelefono());
	}
}
