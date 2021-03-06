package pdf;

import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;

import objects.Azubi;
import objects.Zeugnis;


/**
 * @author backs.kristin-anna
 *Diese Klasse dient dem Erstellen von Zeugnissen als PDF-Datein entweder 
 *als Zwischenzeugnis im DIN A4  Format oder als Abschlusszeugnis im DIN A3 Format.
 */

public class PdfZeugnis {

	private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLDITALIC);
	private Font fNorm = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private Font fBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	private Font fSmaBo = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private Font fTiny = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

	Document document = null;

	NotenFuerPdf noten = new NotenFuerPdf();
	NotenTabelle table = new NotenTabelle();

	public PdfZeugnis(Azubi azubi, String filepath, Zeugnis zeugnis,
			String zeugDatum, Short format) {

		try {
			document = new Document();
			noten.holeZeugnisnote(zeugnis);

			PdfWriter.getInstance(document, new FileOutputStream(filepath + "_"
					+ azubi.getName() + "_" + azubi.getVorname() + ".pdf"));
			document.open();

			if (format == 4) {
				doHeader();
			}

			schreibeAzubi(azubi, zeugnis.getZeugnisKonferenz(), format);
			schreibeLeistungen(azubi, zeugnis.getZeugnisKonferenz(), format);

			if (format == 3) {
				schreibeZweiteSeite(azubi, zeugnis);
			} else if (format == 4) {
				schreibeSignaturen(azubi, zeugDatum, format);
			}

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
/**
 * Die Methode doHeader generiert den Titel des PDF Dokuments
 * @throws Exception
 */
	public void doHeader() throws Exception {
		Paragraph title = new Paragraph();
		title.add(new Paragraph("Zeugnis der Berufsschule", fTitle));

		document.add(title);
	}
/**
 * Die Methode schreibeAzubi generiert den Abschnitt des PDF Dokuments mit den Azubidaten
 * @param azubi Azubi Objekt
 * @param konfDatum Datum der Zeugniskonferent
 * @param format Schriftformat
 * @throws Exception 
 */
	private void schreibeAzubi(Azubi azubi, String konfDatum, Short format)
			throws Exception {
		Paragraph azubiDaten = new Paragraph();
		azubiDaten.setLeading(22.0f);
		azubiDaten.setTabSettings(new TabSettings(56f));

		Phrase name = new Phrase();
		name.add(new Chunk("Vor- und Zuname:", fNorm));
		name.add(Chunk.TABBING);
		name.add(new Chunk(azubi.getVorname() + " " + azubi.getName(), fBold));
		name.add(Chunk.NEWLINE);
		azubiDaten.add(name);

		Phrase gebi = new Phrase();
		gebi.add(new Chunk("geboren am:", fSmall));
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
			azubiDaten.add(schreibeZeitraum(azubi));
		} else if (format == 4) {
			azubiDaten.add(schreibeKlasseDetail(azubi, konfDatum));
		}

		Phrase beruf = new Phrase();
		beruf.add(new Chunk("Ausbildungsberuf:", fSmall));
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
		fehltage.add(new Chunk("Schulvers�umnisse:", fSmall));
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
	/**
	 * Die Methode schreibeKlasseDetail f�gt in das PDF Dokument den 
	 * Teil mit den Azubispezifischen Klassendetails ein
	 * @param azubi Azubi-Objekt
	 * @param konfDatum Datum der Zeugniskonferenz
	 * @return
	 */
	private Phrase schreibeKlasseDetail(Azubi azubi, String konfDatum){
		Phrase klasse = new Phrase();
		klasse.add(new Chunk("Fachklasse:", fSmall));
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
		
		return klasse;
	}
	/**
	 * Die Methode schreibeZeitraum erg�nzt die Zeitr�ume verschiedener Attribute eines Azubis 
	 * @param azubi
	 * @return
	 */
	private Phrase schreibeZeitraum(Azubi azubi){
		Phrase zeitraum = new Phrase();
		zeitraum.add(new Chunk("war vom", fSmall));
		zeitraum.add(Chunk.TABBING);
		zeitraum.add(Chunk.TABBING);
		zeitraum.add(new Chunk(azubi.getAusbildungsbeginn(), fSmaBo));
		zeitraum.add(new Chunk(" bis zum ", fSmall));
		zeitraum.add(new Chunk(azubi.getAusbildungsende(), fSmaBo));
		if (azubi.getGeschlecht() == 'f') {
			zeitraum.add(new Chunk(" Sch�lerin", fSmaBo));
		} else {
			zeitraum.add(new Chunk(" Sch�ler", fSmaBo));
		}
		zeitraum.add(new Chunk(" der Berufsschule", fSmall));
		zeitraum.add(Chunk.NEWLINE);
		zeitraum.add(new Chunk("Fachklasse: ", fSmall));
		zeitraum.add(Chunk.TABBING);
		zeitraum.add(Chunk.TABBING);
		zeitraum.add(new Chunk(azubi.getKlasse().getBezeichnung(), fSmaBo));
		zeitraum.add(Chunk.NEWLINE);
		return zeitraum;
	}
/**
 * Die Methode schreibeLeistungen Erg�nzt die Anmerkungen und sonstige Leistungen in dem PDF Dokument
 * @param azubi Azubi-Objekt
 * @param konfDatum Datum der Zeugniskonferenz
 * @param format Zeichenformat 
 * @throws Exception
 */
	private void schreibeLeistungen(Azubi azubi, String konfDatum, Short format)
			throws Exception {
		Paragraph leistung = new Paragraph();
		leistung.setLeading(22.0f);

		if (format == 3) {
			leistung.add(Chunk.NEWLINE);
			leistung.add(Chunk.NEWLINE);
		}

		Phrase konf = new Phrase();
		konf.add(new Chunk("Die Zeugniskonferenz stellte am ", fSmall));
		konf.add(new Chunk(konfDatum, fSmaBo));
		konf.add(new Chunk(" folgende Leistungen fest:", fSmall));
		konf.add(Chunk.NEWLINE);
		konf.add(Chunk.NEWLINE);
		leistung.add(konf);

		Phrase titel = new Phrase("LEISTUNGEN", fSmaBo);
		titel.add(Chunk.NEWLINE);
		leistung.add(titel);

		Phrase bereich1 = new Phrase("I. Berufs�bergreifender Bereich", fSmaBo);
		leistung.add(bereich1);

		String[][] faecher = noten.gibFachNotenStrings(format);
		leistung.add(table.doTabelle(faecher, 0, 4));

		Phrase bereich2 = new Phrase();
		bereich2.add(new Chunk("II. Berufsbezogener Bereich", fSmaBo));
		if (format == 3) {
			bereich2.add(new Chunk("\u00B2\u207E", fSmall));
		} else if (format == 4) {
			bereich2.add(new Chunk("\u00B9\u207E", fSmall));
		}
		leistung.add(bereich2);

		leistung.add(table.doTabelle(faecher, 4, 8));

		Phrase bereich3 = new Phrase("III. Differenzierungsbereich", fSmaBo);
		leistung.add(bereich3);

		leistung.add(table.doTabelleDiff(format));

		document.add(leistung);
	}
	/**
	 * Die Methode schreibeZweiteSeite generiert die zweite Seite des PDF Dokuments
	 * @param azubi Azubi-Objekt
	 * @param zeugnis Zeugnis-Objekt
	 * @throws Exception
	 */
	private void schreibeZweiteSeite(Azubi azubi, Zeugnis zeugnis)
			throws Exception {
		document.add(Chunk.NEXTPAGE);
		Paragraph zweiteSeite = new Paragraph();
		zweiteSeite.setLeading(22.0f);
		zweiteSeite.setTabSettings(new TabSettings(56f));

		Phrase name = new Phrase();
		name.add(new Chunk("Vor- und Zuname:", fNorm));
		name.add(Chunk.TABBING);
		name.add(Chunk.TABBING);
		name.add(new Chunk(azubi.getVorname() + " " + azubi.getName(), fBold));
		name.add(Chunk.NEWLINE);
		zweiteSeite.add(name);

		String[] durchschnitt = noten.getDurchschnitt(azubi);

		Phrase note = new Phrase();
		note.add(new Chunk("hat mit der Note   ", fSmall));
		note.add(new Chunk("- " + durchschnitt[0] + " -", fSmaBo));
		note.add(new Chunk("   (Durchschnittsnote\u2074\u207E   ", fSmall));
		note.add(new Chunk(durchschnitt[1], fSmaBo));
		note.add(new Chunk(")", fSmall));
		note.add(Chunk.NEWLINE);
		note.add(Chunk.NEWLINE);
		note.add(Chunk.NEWLINE);
		note.add(new Chunk("den", fSmall));
		note.add(Chunk.NEWLINE);
		note.add(new Chunk("Berufsschulsabluss", fNorm));
		note.add(Chunk.NEWLINE);
		note.add(new Chunk("erborben.", fSmall));
		note.add(Chunk.NEWLINE);
		note.add(Chunk.NEWLINE);
		note.add(Chunk.NEWLINE);
		zweiteSeite.add(note);

		zweiteSeite.add(schreibeZeugnisDatum(zeugnis.getZeugnisKonferenz()));

		Phrase lineSig = new Phrase();
		lineSig.add(Chunk.NEWLINE);
		lineSig.add(new Chunk("____________________________"));
		lineSig.add(Chunk.TABBING);
		lineSig.add(Chunk.TABBING);
		lineSig.add(Chunk.TABBING);
		lineSig.add(new Chunk("____________________________"));
		lineSig.add(Chunk.NEWLINE);
		zweiteSeite.add(lineSig);

		Phrase stuffSig = new Phrase("Schulleiter", fTiny);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(new Chunk("(Siegel)", fTiny));
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(new Chunk("Klassenleitung ("
				+ azubi.getKlasse().getLehrer().getName() + ")", fTiny));
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.NEWLINE);
		stuffSig.add(Chunk.NEWLINE);
		zweiteSeite.add(stuffSig);

		document.add(zweiteSeite);
		document.add(schreibeFooter((short)3));

	}
/**
 * Die Methode schreibeFooter erstellt eine Fu�zeile und gibt diese zur�ck
 * @param format zeichenformat
 * @return gibt die Fu�zeile zur�ck
 * @throws Exception
 */
	private Paragraph schreibeFooter(Short format) throws Exception {
		Paragraph footer = new Paragraph();

		for (int i = 0; i <= 23; i++) {
			footer.add(Chunk.NEWLINE);
		}
		footer.add(new Chunk(
				"______________________________________________________________________________"));
		footer.add(Chunk.NEWLINE);

		if (format == 3) {
			footer.add(footerElement("Dieses Fach wurde vorher abgeschlossen. Die Note entspricht der zuletzt erstellen Zeugnisnote in diesem Fach."));
			footer.add(footerElement("Die Note des Faches erbigt sich aus den in den letzten beiden vorangegangenen Schulhalbjahren erbrachten Leistungen."));
		}
		footer.add(footerElement("Die F�cher im berufsbezogenen Bereich umfassen die in der Anlage aufgef�hrten Lernfelder."));

		if (format == 3) {
			footer.add(footerElement("F�cher des Differzierungsbereiches sind in die Durchschnittsnote nicht einbezogen."));
		}

		return footer;
	}
/**
 * Die Methode footerElement erstellt ein Fu�zeilen Element und gibt dieses zur�ck
 * @param value
 * @return
 */
	private Phrase footerElement(String value) {
		Phrase elem = new Phrase("4)", fTiny);
		elem.add(Chunk.TABBING);
		elem.add(new Chunk(value, fTiny));
		elem.add(Chunk.NEWLINE);

		return elem;
	}
/**
 * Die Methode schreibeZeugnisDatum erg�nzt die Signatur am ende des PDF Dokuments
 * @param zeugDatum Datum des Zeugnisses
 * @return
 */
	private Phrase schreibeZeugnisDatum(String zeugDatum){
		Phrase datum = new Phrase();
		datum.add(new Chunk("D�sseldorf, den ", fSmall));
		datum.add(new Chunk(zeugDatum, fSmaBo));
		datum.add(new Chunk(" (Datum der Zeugnisausgabe)", fSmall));
		datum.add(Chunk.NEWLINE);
		return datum;
	}
	/**
	 * Die MEthode schreibeSignaturen erg�nzt die Signaturen unter dem PDF Dokument
	 * @param azubi Azubi-Objekt
	 * @param zeugDatum Datum des Zeugnisses
	 * @param format zeichenformat
	 * @throws Exception 
	 */
	private void schreibeSignaturen(Azubi azubi, String zeugDatum, Short format)
			throws Exception {
		Paragraph signatur = new Paragraph();
		signatur.setLeading(22.0f);

		Phrase lineSig = new Phrase("___________________________________");
		lineSig.add(Chunk.TABBING);
		lineSig.add(Chunk.TABBING);
		lineSig.add(new Chunk("___________________________________"));
		lineSig.add(Chunk.NEWLINE);

		Phrase stuffSig = new Phrase("Schulleiter", fSmall);
		for(int i = 0; i < 8; i++){
			stuffSig.add(Chunk.TABBING);
		}
		stuffSig.add(new Chunk("Klassenleitung ("
				+ azubi.getKlasse().getLehrer().getName() + ")", fTiny));
		stuffSig.add(Chunk.TABBING);
		stuffSig.add(Chunk.NEWLINE);

		Phrase lineKenn = new Phrase();
		lineKenn.add(Chunk.TABBING);

		lineKenn.add(Chunk.TABBING);
		lineKenn.add(new Chunk("________________________"));
		lineKenn.add(Chunk.TABBING);
		lineKenn.add(Chunk.TABBING);
		lineKenn.add(new Chunk("___________________________________"));
		lineKenn.add(Chunk.NEWLINE);

		Phrase kenntnis = new Phrase("Kentnnis genommen:", fTiny);
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(new Chunk("Erziehungsberechtigte(r)", fTiny));
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(Chunk.TABBING);
		kenntnis.add(new Chunk("Ausbildende(r)", fTiny));
		kenntnis.add(Chunk.NEWLINE);

		signatur.add(schreibeZeugnisDatum(zeugDatum));
		signatur.add(lineSig);
		signatur.add(stuffSig);
		signatur.add(lineKenn);
		signatur.add(kenntnis);
		signatur.add(schreibeFooter(format));
		document.add(signatur);

	}
/**
 * Die Methode getHalbjahr ermittelt anhand der Zeugniskonferenz, ob das Zeugnis ein Halbjahr zeugnis ist oder nicht
 * @param konfDatum datum der Zeugniskonferenz
 * @return
 */
	private String getHalbjahr(String konfDatum) {
		String monat = konfDatum.substring(3, 5);
		if (Integer.parseInt(monat) < 2 || Integer.parseInt(monat) > 9) {
			return "1";
		} else {
			return "2";
		}
	}

}