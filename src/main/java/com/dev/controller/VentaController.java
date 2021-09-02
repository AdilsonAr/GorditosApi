package com.dev.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.Resultado;
import com.dev.dto.VentaResponseDto;
import com.dev.model.Pedido;
import com.dev.model.Venta;
import com.dev.service.PedidoService;
import com.dev.service.VentaService;

@RestController
@RequestMapping("/venta")
@CrossOrigin
public class VentaController {
	@Autowired
	private VentaService service;
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestParam int idPedido){
		try {
			double monto=pedidoService.monto(idPedido);
			Pedido p=new Pedido();
			p.setId(idPedido);
			
			service.create(new Venta(LocalDateTime.now(), monto, p));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(NoSuchElementException e){
			return new ResponseEntity<>(new Resultado("No se encontro el pedido"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<?> readAll(){
		List<VentaResponseDto> dtos=new ArrayList<>();
		(service.readAll()).forEach(x->dtos.add(VentaResponseDto.toDto(x)));
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
