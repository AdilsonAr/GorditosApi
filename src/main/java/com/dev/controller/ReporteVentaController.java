package com.dev.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.service.ReporteVentaService;
import com.dev.service.VentaService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/reporte")
@CrossOrigin
public class ReporteVentaController {
	@Autowired
    private ReporteVentaService reporteVentaService;
	@Autowired
    private VentaService ventaService;

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE, path = "/ventas-cliente")
    public ResponseEntity<?> cliente(@RequestParam int id) {
    	
        ByteArrayInputStream bis;
		try {
			bis = reporteVentaService.getReporte(ventaService.readByCliente(id),"Reporte por cliente");
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=ventas-por-cliente.pdf");
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
		} catch (DocumentException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
    
    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE, path = "/ventas-mes")
    public ResponseEntity<?> mes(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fecha) {
        ByteArrayInputStream bis;
		try {
			bis = reporteVentaService.getReporte(ventaService.readByMes(fecha.getYear(), fecha.getMonthValue()),"Reporte por mes "+fecha.getYear()+"/"+fecha.getMonthValue());
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=ventas-por-mes.pdf");
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
		} catch (DocumentException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
}
