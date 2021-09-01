package com.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.CompraRequestDto;
import com.dev.service.CompraService;

@RestController
@RequestMapping("/compra")
@CrossOrigin
public class CompraController {
	@Autowired
	private CompraService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CompraRequestDto c){
		service.create(CompraRequestDto.toMOdel(c));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}