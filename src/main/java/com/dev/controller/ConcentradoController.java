package com.dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.ConcentradoResponseDto;
import com.dev.service.ConcentradoService;

@RestController
@RequestMapping("/concentrado")
@CrossOrigin
public class ConcentradoController {
	@Autowired
	private ConcentradoService service;
	
	@GetMapping
	public ResponseEntity<?> readAll(){
		List<ConcentradoResponseDto> dtos=new ArrayList<>();
		(service.readAll()).forEach(x->dtos.add(ConcentradoResponseDto.toDto(x)));
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
