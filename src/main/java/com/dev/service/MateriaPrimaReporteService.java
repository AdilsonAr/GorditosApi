package com.dev.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.dev.dto.MateriaPrimaResponseDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class MateriaPrimaReporteService {
	public ByteArrayInputStream getReporte(List<MateriaPrimaResponseDto> lista) throws DocumentException {
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		Document document=new Document();
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(90);
        table.setWidths(new int[]{1, 1, 1, 1, 1});

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        NumberFormat nf=NumberFormat.getCurrencyInstance(Locale.US);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Código", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("nombre", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("unidades", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("costo por unidad", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("unidades en bodega", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        for(MateriaPrimaResponseDto c: lista) {
        	hcell = new PdfPCell(new Phrase(String.valueOf(c.getId())));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(c.getNombre()));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(c.getUnidades()));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase(nf.format(c.getCostoPorUnidad())));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase(String.valueOf(c.getCantidadUnidadesEnBodega())));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
        }
        
		PdfWriter.getInstance(document, out);
		document.open();
		document.add(new Phrase("Reporte de materia prima"));
		document.add(table);
		document.close();
		return new ByteArrayInputStream(out.toByteArray());
	}
}
