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

	private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLDITALIC);
	private Font fBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

	Document document = null;

	public PdfZeugnisA4(Azubi azubi, String filepath, String konfDatum, String zeugDatum) {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filepath + "_"
					+ azubi.getName() + ".pdf"));
			document.open();

			Paragraph title = new Paragraph();
			title.add(new Paragraph("Zeugnis der Berufsschule", fTitle));

			document.add(title);
			schreibeAzubi(azubi, konfDatum);
			schreibeLeistungen(azubi, konfDatum);
			schreibeSignaturen(azubi, zeugDatum);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(),
					"Das Zeugnis konnte nicht ordentlich als PDF gespeichert werden.");
		} finally {
			if (document.isOpen()) {
				document.close();
			}
		}
	}

	private void schreibeAzubi(Azubi azubi, String konfDatum) throws Exception {
		Paragraph azubiDaten = new Paragraph();
		azubiDaten.setLeading(22.0f);

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
		if (azubi.getGeschlecht() == 'f') {
			beruf.add(new Chunk("Fachinformatikerin", fBold));
		} else {
			beruf.add(new Chunk("Fachinformatiker", fBold));
		}
		beruf.add(Chunk.NEWLINE);

		Phrase richtung = new Phrase();
		richtung.add(new Chunk("Fachrichtung:"));
		richtung.setTabSettings(new TabSettings(56f));
		richtung.add(Chunk.TABBING);
		richtung.add(Chunk.TABBING);
		if(azubi.getFachrichtung() == 's'){
			richtung.add(new Chunk("Systemintegration", fBold));
		} else if(azubi.getFachrichtung() == 'a'){
			richtung.add(new Chunk("Anwendungsentwicklung", fBold));
		}
		richtung.add(Chunk.NEWLINE);

		Phrase klasse = new Phrase();
		klasse.add(new Chunk("Fachklasse:"));
		klasse.setTabSettings(new TabSettings(56f));
		klasse.add(Chunk.TABBING);
		klasse.add(Chunk.TABBING);
		klasse.add(Chunk.TABBING);
		klasse.add(new Chunk(azubi.getKlasse().getBezeichnung() + "   "
				+ azubi.getLehrjahr(), fBold));
		klasse.add(new Chunk(". Jahr   Schuljahr "));
		klasse.add(new Chunk(String.valueOf((azubi.getKlasse().getJahr()) - 1)
				+ "/" + String.valueOf(azubi.getKlasse().getJahr()), fBold));
		klasse.add(new Chunk("   " + getHalbjahr(konfDatum), fBold));
		klasse.add(new Chunk(". Halbjahr"));
		klasse.add(Chunk.NEWLINE);
		
		Phrase fehltage = new Phrase();
		fehltage.add("Schulversäumnisse:");
		fehltage.add(Chunk.TABBING);
		fehltage.add(Chunk.TABBING);
		fehltage.add(new Chunk(String.valueOf(azubi.getFehltage()), fBold));
		fehltage.add(new Chunk(" Fehltage, davon "));
		fehltage.add(new Chunk("0", fBold));
		fehltage.add(new Chunk("unentschuldigt"));
		

		azubiDaten.add(name);
		azubiDaten.add(gebi);
		azubiDaten.add(beruf);
		azubiDaten.add(richtung);
		azubiDaten.add(klasse);
		azubiDaten.add(fehltage);
		document.add(azubiDaten);
	}

	private void schreibeLeistungen(Azubi azubi, String konfDatum)
			throws Exception {
		Paragraph leistung = new Paragraph();
		leistung.setLeading(22.0f);

		Phrase konf = new Phrase();
		konf.add(new Chunk("Die Zeugniskonferenz stelle am "));
		konf.add(new Chunk(konfDatum, fBold));
		konf.add(new Chunk(" folgende Leistungen fest:"));
		konf.add(Chunk.NEWLINE);

		Phrase titel = new Phrase("LEISTUNGEN", fBold);
		titel.add(Chunk.NEWLINE);

		Phrase bereich1 = new Phrase("I. Berufsübergreifender Bereich", fBold);

		PdfPTable tableB1 = new PdfPTable(3);
		tableB1.setWidthPercentage(100);
		tableB1.setHorizontalAlignment(Element.ALIGN_TOP);

		tableFach(tableB1, "Deutsch / Kommunikation");
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, "Politik / Gesellschaftslehre");
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, "Religionslehre");
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, "Sport / Gesundheitsförderung");
		tableNote(tableB1, "- gut -");

		PdfPTable tableB2 = new PdfPTable(3);
		tableB2.setWidthPercentage(100);
		tableB2.setHorizontalAlignment(Element.ALIGN_TOP);

		Phrase bereich2 = new Phrase();
		bereich2.add(new Chunk("II. Berufsbezogener Bereich", fBold));
		bereich2.add(new Chunk("\u00B9\u207E"));
		tableFach(tableB2, "Informations- und Telekommunikationssysteme");
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, "Anwendungsentwicklung");
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, "Wirtschafts- und Geschäftsprozesse");
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, "Englisch");
		tableNote(tableB2, "- gut -");

		Phrase bereich3 = new Phrase("I. Differenzierungsbereich", fBold);

		PdfPTable tableB3 = new PdfPTable(3);
		tableB3.setWidthPercentage(100);
		tableB3.setHorizontalAlignment(Element.ALIGN_TOP);

		tableFach(tableB3, "------------------------");
		tableNote(tableB3, "------------------------");

		tableBemerkung(tableB3, "Bemerkungen:");
		tableBox(tableB3, "- keine -");
		tableBeschluss(tableB3, "Beschluss der Konferenz:");
		tableBox2(tableB3, "------------------------");

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

	private void schreibeSignaturen(Azubi azubi, String zeugDatum) throws Exception {
		Paragraph signatur = new Paragraph();
		signatur.setLeading(22.0f);
		
		Phrase datum = new Phrase();
		datum.add(new Chunk("Düsseldorf, den "));
		datum.add(new Chunk(zeugDatum, fBold));
		datum.add(new Chunk(" (Datum der Zeugnisausgabe)", fSmall));
		datum.add(Chunk.NEWLINE);
		
		Phrase lineSig = new Phrase("___________________________________");
		lineSig.add(Chunk.TABBING);
		lineSig.add(Chunk.TABBING);
		lineSig.add(new Chunk("___________________________________"));
		lineSig.add(Chunk.NEWLINE);
		
		Phrase stuffSig = new Phrase("Schulleiter", fSmall);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
//		stuffSig.add("Klassenleitung (" + azubi.getKlasse().getLehrer().getName() + ")");
		stuffSig.add("Klassenleitung (Name insert follows...)");
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.NEWLINE);
		
		Phrase lineKenn = new Phrase();
		lineKenn.add(Chunk.TABBING);
		lineKenn.add(Chunk.TABBING);
		lineKenn.add(Chunk.TABBING);
		lineKenn.add(new Chunk("____________________"));
		lineKenn.add(Chunk.TABBING);
		lineKenn.add(Chunk.TABBING);
		lineKenn.add(new Chunk("___________________________________"));
		lineKenn.add(Chunk.NEWLINE);
		
		Phrase kenntnis = new Phrase("Kentnnis genommen:", fSmall);
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(new Chunk("Erziehungsberechtigte(r)"));
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(new Chunk("Ausbildende(r)"));
		kenntnis.add(Chunk.NEWLINE);
		
		Phrase line = new Phrase("______________________________________________________________________________");
		line.add(Chunk.NEWLINE);
		
		Phrase fuss = new Phrase("1)", fSmall);
		fuss.add(Chunk.TABBING);
		fuss.add(new Chunk("Die Fächer im berufsbezogenen Bereich umfassen die in der Anlage aufgeführten Lernfelder.", fSmall));
		
		signatur.add(datum);
		signatur.add(lineSig);
		signatur.add(stuffSig);
		signatur.add(lineKenn);
		signatur.add(kenntnis);
		signatur.add(line);
		signatur.add(fuss);
		document.add(signatur);
		
	}

	private void tableFach(PdfPTable table, String fach) {
		PdfPCell cellFach = new PdfPCell(new Paragraph(fach));
		cellFach.setBorder(Rectangle.NO_BORDER);
		cellFach.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellFach.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellFach.setFixedHeight(20f);
		cellFach.setColspan(2);
		table.addCell(cellFach);
	}

	private void tableNote(PdfPTable table, String note) {
		PdfPCell cellNote = new PdfPCell(new Paragraph(note, fBold));
		cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellNote.setFixedHeight(20f);

		table.addCell(cellNote);
		table.completeRow();
	}

	private void tableBemerkung(PdfPTable table, String bemerkung) {
		PdfPCell cellFach = new PdfPCell(new Paragraph(bemerkung));
		cellFach.setBorder(Rectangle.NO_BORDER);
		cellFach.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellFach.setVerticalAlignment(Element.ALIGN_TOP);
		cellFach.setFixedHeight(40f);
		table.addCell(cellFach);
	}
	
	private void tableBeschluss(PdfPTable table, String bemerkung) {
		PdfPCell cellFach = new PdfPCell(new Paragraph(bemerkung));
		cellFach.setBorder(Rectangle.NO_BORDER);
		cellFach.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellFach.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellFach.setFixedHeight(20f);
		table.addCell(cellFach);
	}

	private void tableBox(PdfPTable table, String inhalt) {
		PdfPCell cellNote = new PdfPCell(new Paragraph(inhalt, fBold));
		cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_TOP);
		cellNote.setFixedHeight(40f);
		cellNote.setColspan(2);
		table.addCell(cellNote);
		table.completeRow();
	}
	
	private void tableBox2(PdfPTable table, String inhalt) {
		PdfPCell cellNote = new PdfPCell(new Paragraph(inhalt, fBold));
		cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellNote.setFixedHeight(20f);
		cellNote.setColspan(2);
		table.addCell(cellNote);
		table.completeRow();
	}
	
	private String getHalbjahr(String konfDatum){
		String monat = konfDatum.substring(3, 5);
		if(Integer.parseInt(monat) < 2 || Integer.parseInt(monat) > 9){
			return "1";
		} else{
			return "2";
		}
	}

}