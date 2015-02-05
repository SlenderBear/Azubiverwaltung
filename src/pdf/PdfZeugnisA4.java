package pdf;

import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
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
		
		Phrase titel = new Phrase("LEISTUNGEN", fBold);
		titel.add(Chunk.NEWLINE);
		
		Phrase bereich1 = new Phrase("I. Berufsübergreifender Bereich", fBold);
		
		PdfPTable tableB1 = new PdfPTable(2);
		tableB1.setWidthPercentage(100);
		tableB1.setHorizontalAlignment(Element.ALIGN_TOP);
		tableB1.setWidths(new int[]{2, 1});
		
		tableFach(tableB1, "Deutsch / Kommunikation");
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, "Politik / Gesellschaftslehre");
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, "Religionslehre");
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, "Sport / Gesundheitsförderung");
		tableNote(tableB1, "- gut -");
		
		PdfPTable tableB2 = new PdfPTable(2);
		tableB2.setWidthPercentage(100);
		tableB2.setHorizontalAlignment(Element.ALIGN_TOP);
		tableB2.setWidths(new int[]{2, 1});
		
		Phrase bereich2 = new Phrase("II. Berufsbezogener Bereich", fBold);
		tableFach(tableB2, "Informations- und Telekommunikationssysteme");
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, "Anwendungsentwicklung");
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, "Wirtschafts- und Geschäftsprozesse");
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, "Englisch");
		tableNote(tableB2, "- gut -");
		
		Phrase bereich3 = new Phrase("I. Differenzierungsbereich", fBold);
		
		PdfPTable tableB3 = new PdfPTable(2);
		tableB3.setWidthPercentage(100);
		tableB3.setHorizontalAlignment(Element.ALIGN_TOP);
		tableB3.setWidths(new int[]{2, 1});
		
		tableFach(tableB3, "------------------------");
		tableNote(tableB3, "------------------------");

		leistung.add(konf);
		leistung.add(titel);
		leistung.add(bereich1);
		leistung.add(tableB1);
		leistung.add(bereich2);
		leistung.add(tableB2);
		leistung.add(bereich3);
		leistung.add(tableB3);
		document.add(leistung);
	}
	
	private void tableFach(PdfPTable table, String fach){
		PdfPCell cellFach = new PdfPCell(new Paragraph(fach));
		cellFach.setBorder(Rectangle.NO_BORDER);
		cellFach.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellFach.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellFach.setFixedHeight(20f);
		
        table.addCell(cellFach);
	}
	
	private void tableNote(PdfPTable table, String note){
		PdfPCell cellNote = new PdfPCell(new Paragraph(note, fBold));
		cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellNote.setFixedHeight(20f);
        table.addCell(cellNote);
	}

}