package pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * @author backs.kristin-anna
 *Diese Klasse dient dem Schreiben der  Fächer- und Notenübersicht in den Zeugnis-PDFs
 */

public class NotenTabelle {
	
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	private Font fSmaBo = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	
	public PdfPTable doTabelle(String[][] faecher, int start, int ende){
	
		PdfPTable tableB1 = new PdfPTable(3);
		tableB1.setWidthPercentage(100);
		tableB1.setHorizontalAlignment(Element.ALIGN_TOP);
		
		for(int i = start; i < ende; i++){
			createCell(tableB1, new Chunk(faecher[0][i], fSmall), 2, false, 20f);
			createCell(tableB1, new Chunk(faecher[1][i], fSmaBo), 1, true, 20f);
			emptyTableLine(tableB1, 2f);		
		}
		return tableB1;
	}
	/**
	 * Die Methode doTabelleDiff unterscheidet zwischen A3 und A4 Dokument
	 * @param format
	 * @return
	 */
	public PdfPTable doTabelleDiff(Short format){
		PdfPTable tableB3 = new PdfPTable(3);
		tableB3.setWidthPercentage(100);
		tableB3.setHorizontalAlignment(Element.ALIGN_TOP);

		createCell(tableB3, new Chunk("------------------------", fSmall), 2, false, 20f);
		createCell(tableB3, new Chunk("------------------------", fSmaBo), 1, true, 20f);
		emptyTableLine(tableB3, 2f);

		if (format == 3) {
			createCell(tableB3, new Chunk("------------------------", fSmall), 2, false, 20f);
			createCell(tableB3, new Chunk("------------------------", fSmaBo), 1, true, 20f);
			emptyTableLine(tableB3, 40);
		}

		createCell(tableB3, new Chunk("Bemerkungen:", fSmall), 1, false, 40);
		createCell(tableB3, new Chunk("- keine -", fSmaBo), 2, true, 40);
		if (format == 4) {
			emptyTableLine(tableB3, 2f);
			createCell(tableB3, new Chunk("Beschluss der Konferenz:",
					fSmall), 1, false, 20);
			createCell(tableB3, new Chunk("------------------------", fSmaBo), 2, true, 20);
		}
		return tableB3;
	}
/**
 * Die Methode createCell erstellt eine Zelle der tabelle
 * @param table Table Objekt
 * @param value Anzahl der Chunks
 * @param colspan Spaltenbreite
 * @param grey graues Feld 
 * @param height Höhe einer Zelle
 */
	private void createCell(PdfPTable table, Chunk value, int colspan, Boolean grey, float height) {
		PdfPCell cell = new PdfPCell(new Paragraph(value));
		cell.setBorder(Rectangle.NO_BORDER);
		if(grey){
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);			
		} else{			
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		}
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(height);
		cell.setColspan(colspan);
		table.addCell(cell);
	}
/**
 * Die Methode emptyTableLine erstellt eine Leere Tabellenzeile
 * @param table Table Objekt
 * @param height Die Höhe der Zeile
 */
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
