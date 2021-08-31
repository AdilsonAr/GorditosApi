package com.dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.MateriaPrimaResponseDto;
import com.dev.service.MateriaPrimaService;

@RestController
@RequestMapping("/materia")
public class MateriaPrimaController {
	@Autowired
	private MateriaPrimaService service;
	@GetMapping
	public ResponseEntity<?> readAll(){
		List<MateriaPrimaResponseDto> dtos=new ArrayList<>();
		(service.readAll()).forEach(x->dtos.add(MateriaPrimaResponseDto.toDto(x)));
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}