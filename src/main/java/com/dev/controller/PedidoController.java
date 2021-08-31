package com.dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.PedidoRequestDto;
import com.dev.dto.PedidoResponseDto;
import com.dev.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private PedidoService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PedidoRequestDto p){
		service.create(PedidoRequestDto.toModel(p));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<?> readAll(){
		List<PedidoResponseDto> dtos=new ArrayList<>();
		(service.readAll()).forEach(x->dtos.add(PedidoResponseDto.toDto(x)));
		return new ResponseEntity<>(dtos,HttpStatus.OK);
	}
}
