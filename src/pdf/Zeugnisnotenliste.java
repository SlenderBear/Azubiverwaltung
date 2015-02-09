package pdf;

import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import objects.Azubi;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Zeugnisnotenliste {

	private Font fNorm = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	private Font fBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	private Document document = null;
	private PdfPTable table = null;

	public void doTabelle(String filepath, Azubi azubi) {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filepath + "Zeugnisnotenliste_"
					+ azubi.getKlasse().getBezeichnung() + ".pdf"));

			document.open();
			document.add(createTabelle(azubi));
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(new JFrame(),
							"Die Zeugnisnotenliste konnte nicht ordentlich als PDF gespeichert werden.");
		} finally {
			if (document.isOpen()) {
				document.close();
			}
		}

	}

	private PdfPTable createTabelle(Azubi azubi) {
		table = new PdfPTable(24);
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_TOP);

		createHeader(azubi);
		createFaecher();
		createListe();
		
		return table;
	}

	private void createHeader(Azubi azubi) {
		Paragraph datum = new Paragraph();
		datum.add(new Chunk("Datum Zeugniskonferenz:", fNorm));
		datum.add(Chunk.NEWLINE);
		datum.add(new Chunk("Datum Zeugnisausgabe:", fNorm));
		createCell(datum, 12, false, 40f, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		createCell(new Paragraph("Zeunisnotenliste Anlage 3", fBold),
				12, false, 40f, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		
		createCell(new Paragraph("Fachrichtung", fNorm), 7, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		createCell(new Paragraph("Klasse", fNorm), 7, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		createCell(new Paragraph("Zeugnisnoten (Gewichtungsfaktor für Abschlusszeugnis)", fNorm), 8, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		createCell(new Paragraph("JZ", fNorm), 1, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		createCell(new Paragraph("AZ", fNorm), 1, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		
		if(azubi.getFachrichtung() == 'a'){			
			createCell(new Paragraph("AE", fBold), 7, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		} else if(azubi.getFachrichtung() == 's'){
			createCell(new Paragraph("SI", fBold), 7, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		}
		createCell(new Paragraph(azubi.getKlasse().getBezeichnung(), fBold), 7, false, 30, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		createCell(new Paragraph(""), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		createCell(new Paragraph("1"), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		createCell(new Paragraph(""), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		createCell(new Paragraph(""), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		if(azubi.getFachrichtung() == 'a'){
			createCell(new Paragraph("1"), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
			createCell(new Paragraph("2"), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		} else if(azubi.getFachrichtung() == 's'){
			createCell(new Paragraph("2"), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
			createCell(new Paragraph("1"), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		}
		createCell(new Paragraph("1"), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		createCell(new Paragraph(""), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		createCell(new Paragraph(""), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		createCell(new Paragraph(""), 1, false, 30, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		
	}
	
	private void createFaecher(){
	    createCell(new Paragraph("Nr.", fNorm), 1, false, 100, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM);
	    createCell(new Paragraph("Name", fNorm), 6, false, 100, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM);
	    createCell(new Paragraph("Vorname", fNorm), 6, false, 100, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM);
	    
	    createCell(new Paragraph("Verkürzer", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    
	    createCell(new Paragraph("Deutsch", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    createCell(new Paragraph("Gesellschaftslehre", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    createCell(new Paragraph("Religion", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    createCell(new Paragraph("Sport", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    createCell(new Paragraph("Informations- und Telekommunitionssys.", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    createCell(new Paragraph("Anwendungsentwicklung", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    createCell(new Paragraph("Wirtschafts- und Geschäftsprozesse", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    createCell(new Paragraph("Englisch", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	    
	    createCell(new Paragraph("Anforderungen erfüllt(e) Anford. nicht erfüllt(ne)", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
            createCell(new Paragraph("Durchschnittsnote Abschlusszeugnis", fSmall), 1, true, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	}
	
	private void createListe(){
	    for(int i = 0; i < 30; i++){
	        createCell(new Paragraph(String.valueOf(i+1), fSmall), 1, false, 15, Element.ALIGN_CENTER, Element.ALIGN_CENTER);
	        createCell(new Paragraph("", fNorm), 6, false, 15, Element.ALIGN_CENTER, Element.ALIGN_CENTER);
	        createCell(new Paragraph("", fNorm), 6, false, 15, Element.ALIGN_CENTER, Element.ALIGN_CENTER);
	        for(int j = 0; j < 11; j++){
	            createCell(new Paragraph("", fNorm), 1, false, 15, Element.ALIGN_CENTER, Element.ALIGN_CENTER);
	        }

	    }
	}


	private void createCell(Paragraph value, int colspan,
			Boolean gekippt, float height, int horiAlignment, int vertiAlignment) {
		PdfPCell cell = new PdfPCell(value);
		if (gekippt) {
			cell.setRotation(90);
		}
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(height);
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(horiAlignment);
		cell.setVerticalAlignment(vertiAlignment);
		table.addCell(cell);
	}


}
