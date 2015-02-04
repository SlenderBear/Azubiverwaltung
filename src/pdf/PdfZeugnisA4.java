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

public class PdfZeugnisA4 {

	private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
	private Font fBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	Document document = null;

	public PdfZeugnisA4(Azubi azubi, String filepath, String konferenzDatum) {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filepath + "_"
					+ azubi.getName() + ".pdf"));
			document.open();

			Paragraph title = new Paragraph(150);
			title.add(new Paragraph("Zeugnis der Berufsschule", fTitle));

			document.add(title);
			schreibeAzubi(azubi);

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
		
		Phrase name = new Phrase(150);
		name.add(new Chunk("Vor- und Zuname:"));
		name.setTabSettings(new TabSettings(56f));
        name.add(Chunk.TABBING);
		name.add(new Chunk(azubi.getVorname() + " " + azubi.getName(), fBold));
		name.add(Chunk.NEWLINE);
		
		Phrase gebi = new Phrase(150);
		gebi.add(new Chunk("geboren am:"));
		gebi.setTabSettings(new TabSettings(56f));
        gebi.add(Chunk.TABBING);
        gebi.add(Chunk.TABBING);
		gebi.add(new Chunk(azubi.getGeburtsdatum(), fBold));
		gebi.add(Chunk.NEWLINE);
		
		Phrase beruf = new Phrase(150);
		beruf.add(new Chunk("Ausbildungsberuf:"));
		beruf.setTabSettings(new TabSettings(56f));
        beruf.add(Chunk.TABBING);
		if(azubi.getGeschlecht() == 'f'){
			beruf.add(new Chunk("Fachinformatikerin", fBold));
		} else{
			beruf.add(new Chunk("Fachinformatiker", fBold));
		}
		beruf.add(Chunk.NEWLINE);
		
		Phrase richtung = new Phrase(150);
		richtung.add(new Chunk("Fachrichtung:"));
		richtung.setTabSettings(new TabSettings(56f));
        richtung.add(Chunk.TABBING);
		richtung.add(new Chunk("Anwendungsentwicklung", fBold));
		richtung.add(Chunk.NEWLINE);
		
		Phrase klasse = new Phrase(150);
		klasse.add(new Chunk("Fachklasse:"));
		klasse.setTabSettings(new TabSettings(56f));
        klasse.add(Chunk.TABBING);
		klasse.add(Chunk.NEWLINE);
	
		name.setLeading(11.0f);
		gebi.setLeading(11.0f);
		beruf.setLeading(11.0f);
		
		azubiDaten.add(name);
		azubiDaten.add(gebi);
		azubiDaten.add(beruf);
		azubiDaten.add(richtung);
		azubiDaten.add(klasse);
		document.add(azubiDaten);
	}

}
