package com.dev.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.dev.dto.MateriaPrimaResponseDto;
import com.dev.dto.PedidoResponseDto;
import com.dev.service.MateriaPrimaReporteService;
import com.dev.service.MateriaPrimaService;
import com.dev.service.PedidoReporteService;
import com.dev.service.PedidoService;
import com.dev.service.VentaReporteService;
import com.dev.service.VentaService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/reporte")
@CrossOrigin
public class ReporteController {
	@Autowired
    private VentaReporteService reporteVentaService;
	@Autowired
    private VentaService ventaService;
	@Autowired
    private MateriaPrimaReporteService materiaReporteService;
	@Autowired
    private PedidoReporteService pedidoReporteService;
	@Autowired
    private MateriaPrimaService materiaService;
	@Autowired
    private PedidoService pedidoService;

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
    
    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE, path = "/materia")
    public ResponseEntity<?> materia() {
    	
        ByteArrayInputStream bis;
		try {
			List<MateriaPrimaResponseDto> dtos=new ArrayList<>();
			(materiaService.readAll()).forEach(x->dtos.add(MateriaPrimaResponseDto.toDto(x)));
			bis = materiaReporteService.getReporte(dtos);
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=materia-prima.pdf");
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
    
    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE, path = "/pedido")
    public ResponseEntity<?> pedido(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fecha) {
        ByteArrayInputStream bis;
		try {
			List<PedidoResponseDto> dtos=new ArrayList<>();
			(pedidoService.readByMes(fecha.getYear(), fecha.getMonthValue())).forEach(x->dtos.add(PedidoResponseDto.toDto(x)));
			bis = pedidoReporteService.getReporte(dtos, "Reporte de pedidos por mes "+fecha.getYear()+"/"+fecha.getMonthValue());
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=pedidos-por-mes.pdf");
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
