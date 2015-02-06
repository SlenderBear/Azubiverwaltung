package pdf;

import java.io.FileOutputStream;
import java.util.ArrayList;

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

import datenbank.provider.StandardDataProvider;
import objects.Azubi;
import objects.Zeugnis;
import objects.Zeugnisposition;

public class PdfZeugnisA4 {

	private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLDITALIC);
	private Font fNorm = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private Font fBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	private Font fSmaBo = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);

	Document document = null;
	
	ArrayList<Zeugnisposition> noten = null;

	public PdfZeugnisA4(Azubi azubi, String filepath, Zeugnis zeugnis,
			String zeugDatum, Short format) {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filepath + "_"
					+ azubi.getName() + ".pdf"));
			document.open();

			if (format == 4) {
				doHeader();
			}
			holeZeugnisnote(zeugnis);
			schreibeAzubi(azubi, zeugnis.getZeugnisKonferenz(), format);
			schreibeLeistungen(azubi, zeugnis.getZeugnisKonferenz(), format);
			schreibeSignaturen(azubi, zeugDatum, format);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(new JFrame(),
							"Das Zeugnis konnte nicht ordentlich als PDF gespeichert werden.");
		} finally {
			if (document.isOpen()) {
				document.close();
			}
		}
	}

	public void doHeader() throws Exception {
		Paragraph title = new Paragraph();
		title.add(new Paragraph("Zeugnis der Berufsschule", fTitle));

		document.add(title);
	}

	private void schreibeAzubi(Azubi azubi, String konfDatum, Short format)
			throws Exception {
		Paragraph azubiDaten = new Paragraph();
		azubiDaten.setLeading(22.0f);
		azubiDaten.setTabSettings(new TabSettings(56f));

		Phrase name = new Phrase();
		name.add(new Chunk("Vor- und Zuname:", fNorm));
		name.add(Chunk.TABBING);
		name.add(Chunk.TABBING);
		name.add(new Chunk(azubi.getVorname() + " " + azubi.getName(), fBold));
		name.add(Chunk.NEWLINE);
		azubiDaten.add(name);

		Phrase gebi = new Phrase();
		gebi.add(new Chunk("geboren am:", fSmall));
		gebi.add(Chunk.TABBING);
		gebi.add(Chunk.TABBING);
		gebi.add(Chunk.TABBING);
		gebi.add(new Chunk(azubi.getGeburtsdatum(), fSmaBo));
		if (format == 3) {
			gebi.add(new Chunk(" in ", fSmall));
			gebi.add(new Chunk(azubi.getGeburtsort() + " ("
					+ azubi.getGeburtsland() + ")", fSmaBo));
		}
		gebi.add(Chunk.NEWLINE);
		azubiDaten.add(gebi);

		if (format == 3) {
			Phrase zeitraum = new Phrase();
			zeitraum.add(new Chunk("war vom", fSmall));
			zeitraum.add(Chunk.TABBING);
			zeitraum.add(Chunk.TABBING);
			zeitraum.add(new Chunk(azubi.getAusbildungsbeginn(), fSmaBo));
			zeitraum.add(new Chunk(" bis zum ", fSmall));
			zeitraum.add(new Chunk(azubi.getAusbildungsende(), fSmaBo));
			if (azubi.getGeschlecht() == 'f') {
				zeitraum.add(new Chunk(" Schülerin", fSmaBo));
			} else {
				zeitraum.add(new Chunk(" Schüler", fSmaBo));
			}
			zeitraum.add(new Chunk(" der Berufsschule", fSmall));
			zeitraum.add(Chunk.NEWLINE);
			zeitraum.add(new Chunk("Fachkklasse: ", fSmall));
			zeitraum.add(Chunk.TABBING);
			zeitraum.add(Chunk.TABBING);
			zeitraum.add(new Chunk(azubi.getKlasse().getBezeichnung(), fSmaBo));
			zeitraum.add(Chunk.NEWLINE);
			azubiDaten.add(zeitraum);
		} else if (format == 4) {
			Phrase klasse = new Phrase();
			klasse.add(new Chunk("Fachklasse:", fSmall));
			klasse.add(Chunk.TABBING);
			klasse.add(Chunk.TABBING);
			klasse.add(Chunk.TABBING);
			klasse.add(new Chunk(azubi.getKlasse().getBezeichnung() + "   "
					+ azubi.getLehrjahr(), fSmaBo));
			klasse.add(new Chunk(". Jahr   Schuljahr ", fSmall));
			klasse.add(new Chunk(
					String.valueOf((azubi.getKlasse().getJahr()) - 1) + "/"
							+ String.valueOf(azubi.getKlasse().getJahr()),
					fSmaBo));
			klasse.add(new Chunk("   " + getHalbjahr(konfDatum), fSmaBo));
			klasse.add(new Chunk(". Halbjahr", fSmall));
			klasse.add(Chunk.NEWLINE);
			azubiDaten.add(klasse);
		}

		Phrase beruf = new Phrase();
		beruf.add(new Chunk("Ausbildungsberuf:", fSmall));
		beruf.add(Chunk.TABBING);
		beruf.add(Chunk.TABBING);
		if (azubi.getGeschlecht() == 'f') {
			beruf.add(new Chunk("Fachinformatikerin", fSmaBo));
		} else {
			beruf.add(new Chunk("Fachinformatiker", fSmaBo));
		}
		beruf.add(Chunk.NEWLINE);
		azubiDaten.add(beruf);

		Phrase richtung = new Phrase();
		richtung.add(new Chunk("Fachrichtung:", fSmall));
		richtung.add(Chunk.TABBING);
		richtung.add(Chunk.TABBING);
		if (azubi.getFachrichtung() == 's') {
			richtung.add(new Chunk("Systemintegration", fSmaBo));
		} else if (azubi.getFachrichtung() == 'a') {
			richtung.add(new Chunk("Anwendungsentwicklung", fSmaBo));
		}
		richtung.add(Chunk.NEWLINE);
		azubiDaten.add(richtung);

		Phrase fehltage = new Phrase();
		fehltage.add(new Chunk("Schulversäumnisse:", fSmall));
		fehltage.add(Chunk.TABBING);
		fehltage.add(Chunk.TABBING);
		if (format == 4) {
			fehltage.add(new Chunk(String.valueOf(azubi.getFehltage()), fSmaBo));
			fehltage.add(new Chunk(" Fehltage, davon ", fSmall));
		}
		fehltage.add(new Chunk("0", fSmaBo));
		if (format == 4) {
			fehltage.add(new Chunk(" unentschuldigt", fSmall));
		} else if (format == 3) {
			fehltage.add(new Chunk(" unentschuldigte Unterrichtstage", fSmall));
		}
		azubiDaten.add(fehltage);

		document.add(azubiDaten);
	}

	private void schreibeLeistungen(Azubi azubi, String konfDatum, Short format)
			throws Exception {
		Paragraph leistung = new Paragraph();
		leistung.setLeading(22.0f);

		if (format == 3) {
			leistung.add(Chunk.NEWLINE);
			leistung.add(Chunk.NEWLINE);
		}

		Phrase konf = new Phrase();
		konf.add(new Chunk("Die Zeugniskonferenz stellte am "));
		konf.add(new Chunk(konfDatum, fBold));
		konf.add(new Chunk(" folgende Leistungen fest:"));
		konf.add(Chunk.NEWLINE);

		Phrase titel = new Phrase("LEISTUNGEN", fBold);
		titel.add(Chunk.NEWLINE);

		Phrase bereich1 = new Phrase("I. Berufsübergreifender Bereich", fBold);

		PdfPTable tableB1 = new PdfPTable(3);
		tableB1.setWidthPercentage(100);
		tableB1.setHorizontalAlignment(Element.ALIGN_TOP);

		String[] faecher = gibFachStrings(format);
		
		tableFach(tableB1, new Chunk(faecher[0], fSmall));
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, new Chunk(faecher[1], fSmall));
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, new Chunk(faecher[2], fSmall));
		tableNote(tableB1, "- gut -");
		tableFach(tableB1, new Chunk(faecher[3], fSmall));
		tableNote(tableB1, "- gut -");

		PdfPTable tableB2 = new PdfPTable(3);
		tableB2.setWidthPercentage(100);
		tableB2.setHorizontalAlignment(Element.ALIGN_TOP);

		
		Phrase bereich2 = new Phrase();
		bereich2.add(new Chunk("II. Berufsbezogener Bereich", fBold));
		bereich2.add(new Chunk("\u00B9\u207E"));
		tableFach(tableB2, new Chunk(faecher[4], fSmall));
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, new Chunk(faecher[5], fSmall));
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, new Chunk(faecher[6], fSmall));
		tableNote(tableB2, "- gut -");
		tableFach(tableB2, new Chunk(faecher[7], fSmall));
		tableNote(tableB2, "- gut -");

		Phrase bereich3 = new Phrase("I. Differenzierungsbereich", fBold);

		PdfPTable tableB3 = new PdfPTable(3);
		tableB3.setWidthPercentage(100);
		tableB3.setHorizontalAlignment(Element.ALIGN_TOP);

		tableFach(tableB3, new Chunk("------------------------", fSmall));
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

	private void schreibeSignaturen(Azubi azubi, String zeugDatum, Short Format)
			throws Exception {
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
		// stuffSig.add("Klassenleitung (" +
		// azubi.getKlasse().getLehrer().getName() + ")");
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

		Phrase line = new Phrase(
				"______________________________________________________________________________");
		line.add(Chunk.NEWLINE);

		Phrase fuss = new Phrase("1)", fSmall);
		fuss.add(Chunk.TABBING);
		fuss.add(new Chunk(
				"Die Fächer im berufsbezogenen Bereich umfassen die in der Anlage aufgeführten Lernfelder.",
				fSmall));

		signatur.add(datum);
		signatur.add(lineSig);
		signatur.add(stuffSig);
		signatur.add(lineKenn);
		signatur.add(kenntnis);
		signatur.add(line);
		signatur.add(fuss);
		document.add(signatur);

	}

	private void tableFach(PdfPTable table, Chunk fach) {
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

	private String getHalbjahr(String konfDatum) {
		String monat = konfDatum.substring(3, 5);
		if (Integer.parseInt(monat) < 2 || Integer.parseInt(monat) > 9) {
			return "1";
		} else {
			return "2";
		}
	}
	
	private String[] gibFachStrings(Short format){
		String[] strings = new String[8];
		if(format == 3){
			strings[0] = "Deutsch / Kommunikation\u00B2\u207E";
			strings[1] = "Politik / Gesellschaftslehre\u00B2\u207E";
			strings[3] = "Sport / Gesundheitsförderung\u00B9\u207E";
			strings[4] = "Informations- und Telekommunikationssysteme\u00B2\u207E";
			strings[5] = "Anwendungsentwicklung\u00B2\u207E";
			strings[6] = "Wirtschafts- und Geschäftsprozesse\u00B2\u207E";
			strings[7] = "Englisch\u00B2\u207E";
		} else{
			strings[0] = "Deutsch / Kommunikation";
			strings[1] = "Politik / Gesellschaftslehre";
			strings[3] = "Sport / Gesundheitsförderung";
			strings[4] = "Informations- und Telekommunikationssysteme";
			strings[5] = "Anwendungsentwicklung";
			strings[6] = "Wirtschafts- und Geschäftsprozesse";
			strings[7] = "Englisch";
		}
		strings[2] = "Religionslehre";
		
		return strings;
	}

	private void holeZeugnisnote(Zeugnis zeugnis) throws Exception{
		StandardDataProvider dataGetter = StandardDataProvider.getInstance();
		noten = dataGetter.gibPositionenZuZeugnis();
		if(noten.isEmpty()){
			throw new Exception("Keine Noten vorhanden.");
		}
	}

}