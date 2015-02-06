package pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class NotenTabelle {
	
	private Font fSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	private Font fSmaBo = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	
	public PdfPTable doTabelle(String[][] faecher, int start, int ende){
	
		PdfPTable tableB1 = new PdfPTable(3);
		tableB1.setWidthPercentage(100);
		tableB1.setHorizontalAlignment(Element.ALIGN_TOP);
		
		for(int i = start; i < ende; i++){
			tableFach(tableB1, new Chunk(faecher[0][i], fSmall));
			tableNote(tableB1, new Chunk(faecher[1][i], fSmaBo));
			emptyTableLine(tableB1, 2f);		
		}
		return tableB1;
	}
	
	public PdfPTable doTabelleB3(Short format){
		PdfPTable tableB3 = new PdfPTable(3);
		tableB3.setWidthPercentage(100);
		tableB3.setHorizontalAlignment(Element.ALIGN_TOP);

		tableFach(tableB3, new Chunk("------------------------", fSmall));
		tableNote(tableB3, new Chunk("------------------------", fSmaBo));
		emptyTableLine(tableB3, 2f);

		if (format == 3) {
			tableFach(tableB3, new Chunk("------------------------", fSmall));
			tableNote(tableB3, new Chunk("------------------------", fSmaBo));
			emptyTableLine(tableB3, 2f);
			emptyTableLine(tableB3, 40);
		}

		tableBemerkung(tableB3, new Chunk("Bemerkungen:", fSmall));
		tableBox(tableB3, new Chunk("- keine -", fSmaBo));
		if (format == 4) {
			emptyTableLine(tableB3, 2f);
			tableBeschluss(tableB3, new Chunk("Beschluss der Konferenz:",
					fSmall));
			tableBox2(tableB3, new Chunk("------------------------", fSmaBo));
		}
		return tableB3;
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

	private void tableNote(PdfPTable table, Chunk note) {
		PdfPCell cellNote = new PdfPCell(new Paragraph(note));
		cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellNote.setFixedHeight(20f);

		table.addCell(cellNote);
		table.completeRow();
	}

	private void tableBemerkung(PdfPTable table, Chunk bemerkung) {
		PdfPCell cellFach = new PdfPCell(new Paragraph(bemerkung));
		cellFach.setBorder(Rectangle.NO_BORDER);
		cellFach.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellFach.setVerticalAlignment(Element.ALIGN_TOP);
		cellFach.setFixedHeight(40f);
		table.addCell(cellFach);
	}

	private void tableBeschluss(PdfPTable table, Chunk bemerkung) {
		PdfPCell cellFach = new PdfPCell(new Paragraph(bemerkung));
		cellFach.setBorder(Rectangle.NO_BORDER);
		cellFach.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellFach.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellFach.setFixedHeight(20f);
		table.addCell(cellFach);
	}

	private void tableBox(PdfPTable table, Chunk inhalt) {
		PdfPCell cellNote = new PdfPCell(new Paragraph(inhalt));
		cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_TOP);
		cellNote.setFixedHeight(40f);
		cellNote.setColspan(2);
		table.addCell(cellNote);
		table.completeRow();
	}

	private void tableBox2(PdfPTable table, Chunk inhalt) {
		PdfPCell cellNote = new PdfPCell(new Paragraph(inhalt));
		cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellNote.setBorder(Rectangle.NO_BORDER);
		cellNote.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellNote.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellNote.setFixedHeight(20f);
		cellNote.setColspan(2);
		table.addCell(cellNote);
		table.completeRow();
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
