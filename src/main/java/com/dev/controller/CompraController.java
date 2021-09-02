package com.dev.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.Resultado;
import com.dev.model.Compra;
import com.dev.model.MateriaPrima;
import com.dev.service.CompraService;

@RestController
@RequestMapping("/compra")
@CrossOrigin
public class CompraController {
	@Autowired
	private CompraService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestParam int idMateria, @RequestParam double cantidad){
		if(cantidad>0) {
			try {
				MateriaPrima m=new MateriaPrima();
				m.setId(idMateria);
				service.create(new Compra(LocalDateTime.now(), cantidad, m));
				return new ResponseEntity<>(HttpStatus.CREATED);
			}catch(NoSuchElementException e) {
				return new ResponseEntity<>(new Resultado("Materia prima no encontrada"), HttpStatus.BAD_REQUEST);
			}
			
		}else {
			return new ResponseEntity<>(new Resultado("cantidad no permitida"), HttpStatus.BAD_REQUEST);
		}
	}
}
