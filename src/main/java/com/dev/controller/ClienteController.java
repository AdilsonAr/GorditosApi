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

import com.dev.dto.ClienteRequestDto;
import com.dev.dto.ClienteResponseDto;
import com.dev.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ClienteRequestDto c){
		service.create(ClienteRequestDto.toMOdel(c));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> readAll(){
		List<ClienteResponseDto> dtos=new ArrayList<>();
		(service.readAll()).forEach(x->dtos.add(ClienteResponseDto.toDto(x)));
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
