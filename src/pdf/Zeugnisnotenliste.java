package pdf;

import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import objects.Klasse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Zeugnisnotenliste {

	private Font fNorm = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private Font fBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	private Document document = null;

	public void doTabelle(String filepath, Klasse klasse) {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filepath + "_"
					+ klasse.getBezeichnung() + ".pdf"));

			document.open();
			document.add(createTabelle());
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

	private PdfPTable createTabelle() {
		PdfPTable table = new PdfPTable(24);
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_TOP);

		createHeader(table);

		return table;
	}

	private void createHeader(PdfPTable table) {
		Paragraph datum = new Paragraph();
		datum.add(new Chunk("Datum Zeugniskonferenz:", fNorm));
		datum.add(Chunk.NEWLINE);
		datum.add(new Chunk("Datum Zeugnisausgabe:", fNorm));
		createCell(table, datum, 12, false, 40f);
		createCell(table, new Paragraph("Zeunisnotenliste Anlage 3", fBold),
				12, false, 40f);
		
		createCell(table, new Paragraph("Fachrichtung", fNorm), 7, false, 30);
	}

	private void createNestedPart(PdfPTable table) {
		PdfPCell cell1 = new PdfPCell();
		cell1.setFixedHeight(100f);
		cell1.setColspan(7);
		
		PdfPTable nestedLeft = new PdfPTable(7);
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_BOTTOM);
		
		

	}

	private void createCell(PdfPTable table, Paragraph value, int colspan,
			Boolean gekippt, float height) {
		PdfPCell cell = new PdfPCell(value);
		if (gekippt) {
			cell.setRotation(90);
		}
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(height);
		cell.setColspan(colspan);
		table.addCell(cell);
	}

	private void emptyTableLine(PdfPTable table, float height) {
		PdfPCell cellNote = new PdfPCell(new Paragraph(""));
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_TOP);
		cellNote.setFixedHeight(height);
		cellNote.setColspan(3);
		table.addCell(cellNote);
		table.completeRow();
	}

}
