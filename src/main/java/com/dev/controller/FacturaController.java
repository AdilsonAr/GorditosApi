package com.dev.controller;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.Resultado;
import com.dev.service.FacturaService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/factura")
@CrossOrigin
public class FacturaController {
	@Autowired
	FacturaService service;
	@GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> factura(@RequestParam int idVenta) {
    	
        ByteArrayInputStream bis;
		try {
			bis = service.getFactura(idVenta);
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=factura-"
	        		+ idVenta+".pdf");
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
		} catch (DocumentException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Resultado("tuvimos problemas al crear el documento"), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(new Resultado("venta no encontrada"), HttpStatus.BAD_REQUEST);
		}
        
    }

}
