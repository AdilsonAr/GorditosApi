package com.dev.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Venta;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class FacturaService {
	@Autowired
	private VentaService ventaService;
	
	private final String NIT="344343-4434-434";
	
	public ByteArrayInputStream getFactura(int id) throws NoSuchElementException, DocumentException {
		DateTimeFormatter df=DateTimeFormatter.ISO_DATE;
		NumberFormat f=NumberFormat.getCurrencyInstance(Locale.US);
		Venta venta=ventaService.readId(id);
		int numeroFactura=venta.getId();
		
		Document document=new Document();
		ByteArrayOutputStream out =new ByteArrayOutputStream();
		
		PdfPTable head=new PdfPTable(2);
		head.setWidthPercentage(95);
		head.setWidths(new int[] {3,1});
		
		PdfPCell cell=new PdfPCell(new Phrase("La Empresa Gorditos S.A de R.L"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		head.addCell(cell);
		
		PdfPCell cell2=new PdfPCell(new Phrase("Factura\nN° "+numeroFactura+"\nNIT: "+NIT));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		head.addCell(cell2);
		
		PdfPTable subHead=new PdfPTable(2);
		subHead.setWidthPercentage(95);
		subHead.setWidths(new int[] {2,1});
		
		PdfPCell cliente=new PdfPCell(new Phrase("Cliente: "+venta.getPedido().getCliente().getNombre()));
		cliente.setHorizontalAlignment(Element.ALIGN_LEFT);
		cliente.setBorder(Rectangle.NO_BORDER);
		subHead.addCell(cliente);
		PdfPCell fecha=new PdfPCell(new Phrase("Fecha: "+venta.getFecha().format(df)));
		fecha.setHorizontalAlignment(Element.ALIGN_LEFT);
		fecha.setBorder(Rectangle.NO_BORDER);
		subHead.addCell(fecha);
		
		PdfPCell direccion=new PdfPCell(new Phrase("Direccion: "+venta.getPedido().getCliente().getDireccion()));
		direccion.setHorizontalAlignment(Element.ALIGN_LEFT);
		direccion.setBorder(Rectangle.NO_BORDER);
		subHead.addCell(direccion);
		
		PdfPCell nit=new PdfPCell(new Phrase("NIT: "+venta.getPedido().getCliente().getNit()));
		nit.setHorizontalAlignment(Element.ALIGN_LEFT);
		nit.setBorder(Rectangle.NO_BORDER);
		subHead.addCell(nit);
		
		PdfPTable body=new PdfPTable(5);
		body.setWidthPercentage(95);
		body.setWidths(new int[]{2,4,1,1,1});
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		PdfPCell cantidad = new PdfPCell(new Phrase("Cantidad", headFont));
		cantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(cantidad);
        PdfPCell descripcion = new PdfPCell(new Phrase("Descripcion", headFont));
        descripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(descripcion);
        PdfPCell precio = new PdfPCell(new Phrase("Precio unitario", headFont));
        precio.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(precio);
        PdfPCell excentas = new PdfPCell(new Phrase("Ventas excentas", headFont));
        excentas.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(excentas);
        PdfPCell afectas = new PdfPCell(new Phrase("Ventas afectas", headFont));
        afectas.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(afectas);
        
        PdfPCell cantidadVal = new PdfPCell(new Phrase(String.valueOf(venta.getPedido().getQuintales())));
		cantidadVal.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(cantidadVal);
        PdfPCell descripcionVal = new PdfPCell(new Phrase("concentrado "+venta.getPedido().getConcentrado().getNombre()));
        descripcionVal.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(descripcionVal);
        PdfPCell precioVal = new PdfPCell(new Phrase(f.format(venta.getPedido().getConcentrado().getPrecioPorQuintal())));
        precioVal.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(precioVal);
        PdfPCell excentasVal = new PdfPCell(new Phrase(""));
        excentasVal.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(excentasVal);
        PdfPCell afectasVal = new PdfPCell(new Phrase(f.format(venta.getMonto())));
        afectasVal.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(afectasVal);
        
        PdfPCell white1 = new PdfPCell(new Phrase(""));
        white1.setHorizontalAlignment(Element.ALIGN_CENTER);
        white1.setBorder(Rectangle.NO_BORDER);
        body.addCell(white1);
        PdfPCell white2 = new PdfPCell(new Phrase(""));
        white2.setHorizontalAlignment(Element.ALIGN_CENTER);
        white2.setBorder(Rectangle.NO_BORDER);
        body.addCell(white2);
        PdfPCell sumas = new PdfPCell(new Phrase("Sumas"));
        sumas.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(sumas);
        PdfPCell white3 = new PdfPCell(new Phrase(""));
        white3.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(white3);
        PdfPCell sumaVal = new PdfPCell(new Phrase(f.format(venta.getMonto())));
        sumaVal.setHorizontalAlignment(Element.ALIGN_CENTER);
        body.addCell(sumaVal);
        
		PdfWriter.getInstance(document, out);
		document.open();
		document.add(head);
		document.add(subHead);
		document.add(body);
		document.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}
