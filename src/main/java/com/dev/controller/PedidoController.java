package com.dev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.PedidoRequestDto;
import com.dev.dto.PedidoResponseDto;
import com.dev.dto.Resultado;
import com.dev.service.PedidoService;

@RestController
@RequestMapping("/pedido")
@CrossOrigin
public class PedidoController {
	@Autowired
	private PedidoService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PedidoRequestDto p){
		try {
			if(service.tieneMateriaPrima(p.getQuintales(), p.getIdConcentrado())) {
				service.create(PedidoRequestDto.toModel(p));
				service.descontarMateriaPrima(p.getQuintales(), p.getIdConcentrado());
				return new ResponseEntity<>(new Resultado("El pedido se registro exitosamente"), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(new Resultado("No hay suficiente materia prima"), HttpStatus.BAD_REQUEST);
			}
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(new Resultado("algunas referencias a otros datos no fueron encontradas"), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping
	public ResponseEntity<?> readAll(){
		List<PedidoResponseDto> dtos=new ArrayList<>();
		(service.readAll()).forEach(x->dtos.add(PedidoResponseDto.toDto(x)));
		return new ResponseEntity<>(dtos,HttpStatus.OK);
	}
}
