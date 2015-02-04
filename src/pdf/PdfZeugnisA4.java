package pdf;

import java.awt.Color;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import objects.Azubi;

public class PdfZeugnisA4 {

	private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
	private Font fBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	
	Document document = null;

	public PdfZeugnisA4(Azubi azubi, String filepath, String konfDatum) {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filepath + "_"
					+ azubi.getName() + ".pdf"));
			document.open();

			Paragraph title = new Paragraph();
			title.add(new Paragraph("Zeugnis der Berufsschule", fTitle));

			document.add(title);
			schreibeAzubi(azubi);
			schreibeLeistungen(azubi, konfDatum);

			document.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Das Zeugnis konnte nicht ordentlich als PDF gespeichert werden.");
		} finally {
			if (document.isOpen()) {
				document.close();
			}
		}
	}

	public void schreibeAzubi(Azubi azubi) throws Exception{
		Paragraph azubiDaten = new Paragraph();
		azubiDaten.setLeading(25.0f);
		
		Phrase name = new Phrase();
		name.add(new Chunk("Vor- und Zuname:"));
		name.setTabSettings(new TabSettings(56f));
        name.add(Chunk.TABBING);
        name.add(Chunk.TABBING);
		name.add(new Chunk(azubi.getVorname() + " " + azubi.getName(), fBold));
		name.add(Chunk.NEWLINE);
		
		Phrase gebi = new Phrase();
		gebi.add(new Chunk("geboren am:"));
		gebi.setTabSettings(new TabSettings(56f));
        gebi.add(Chunk.TABBING);
        gebi.add(Chunk.TABBING);
        gebi.add(Chunk.TABBING); 
		gebi.add(new Chunk(azubi.getGeburtsdatum(), fBold));
		gebi.add(Chunk.NEWLINE);
		
		Phrase beruf = new Phrase();
		beruf.add(new Chunk("Ausbildungsberuf:"));
		beruf.setTabSettings(new TabSettings(56f));
        beruf.add(Chunk.TABBING);
        beruf.add(Chunk.TABBING);
		if(azubi.getGeschlecht() == 'f'){
			beruf.add(new Chunk("Fachinformatikerin", fBold));
		} else{
			beruf.add(new Chunk("Fachinformatiker", fBold));
		}
		beruf.add(Chunk.NEWLINE);
		
		Phrase richtung = new Phrase();
		richtung.add(new Chunk("Fachrichtung:"));
		richtung.setTabSettings(new TabSettings(56f));
        richtung.add(Chunk.TABBING);
        richtung.add(Chunk.TABBING);
		richtung.add(new Chunk("Anwendungsentwicklung", fBold));
		richtung.add(Chunk.NEWLINE);
		
		Phrase klasse = new Phrase();
		klasse.add(new Chunk("Fachklasse:"));
		klasse.setTabSettings(new TabSettings(56f));
        klasse.add(Chunk.TABBING);
        klasse.add(Chunk.TABBING);
        klasse.add(Chunk.TABBING);
        klasse.add(new Chunk(azubi.getKlasse().getBezeichnung() + "   " + azubi.getLehrjahr(), fBold));
        klasse.add(new Chunk(". Jahr   Schuljahr "));
        klasse.add(new Chunk(String.valueOf((azubi.getKlasse().getJahr())-1) + "/" + String.valueOf(azubi.getKlasse().getJahr()), fBold));
		klasse.add(Chunk.NEWLINE);
		
		azubiDaten.add(name);
		azubiDaten.add(gebi);
		azubiDaten.add(beruf);
		azubiDaten.add(richtung);
		azubiDaten.add(klasse);
		document.add(azubiDaten);
	}
	
	public void schreibeLeistungen(Azubi azubi, String konfDatum) throws Exception{
		Paragraph leistung = new Paragraph();
		leistung.setLeading(25.0f);
		
		Phrase konf = new Phrase();
		konf.add(new Chunk("Die Zeugniskonferenz stelle am "));
		konf.add(new Chunk(konfDatum, fBold));
		konf.add(new Chunk(" folgende Leistungen fest:"));
		konf.add(Chunk.NEWLINE);
		
		Phrase titel = new Phrase("LEISTUNENG", fBold);
		titel.add(Chunk.NEWLINE);
		
		Phrase bereich1 = new Phrase("I. Berufsübergreifender Bereich", fBold);
		bereich1.add(Chunk.NEWLINE);
		
		Phrase fach1 = new Phrase();
		fach1.add("Deutsch / Kommunikation");
		Chunk note1 = new Chunk("- gut -");
		PdfPTable table = new PdfPTable(1);
        PdfPCell cell1 = new PdfPCell(new Paragraph("- gut - ", fBold));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);


        table.addCell(cell1);

        fach1.add(table);
        
//		Phrase fach1 = new Phrase();
//		fach1.add("Deutsch / Kommunikation");
//		Chunk note1 = new Chunk("- gut -", fBold);
//		note1.setBackground(BaseColor.LIGHT_GRAY);
//
//        fach1.add(note1);
        fach1.add(Chunk.NEWLINE);
		
		Phrase bereich2 = new Phrase("II. Berufsbezogener Bereich", fBold);
		bereich2.add(Chunk.NEWLINE);
		
		Phrase bereich3 = new Phrase("I. Differenzierungsbereich", fBold);
		bereich3.add(Chunk.NEWLINE);
		
		leistung.add(konf);
		leistung.add(titel);
		leistung.add(bereich1);
		leistung.add(fach1);
		leistung.add(bereich2);
		leistung.add(bereich3);
		document.add(leistung);
	}

}
