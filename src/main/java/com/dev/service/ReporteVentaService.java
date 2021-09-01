package com.dev.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.model.Venta;
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
public class ReporteVentaService {
	public ByteArrayInputStream getReporte(List<Venta> lista, String title) throws DocumentException {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(90);
        table.setWidths(new int[]{1, 1, 1, 1, 1});

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Código", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Fecha", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Monto", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("Pedido", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("cliente", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
        
        for (Venta c : lista) {

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(String.valueOf(c.getId())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(c.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(c.getMonto())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(c.getPedido().getId())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(c.getPedido().getCliente().getNombre()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        PdfWriter.getInstance(document, out);
        document.open();
        document.addTitle(title);
        document.add(new Phrase(title));
        document.add(table);
        
        document.close();
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}
