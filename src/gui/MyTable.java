package gui;
import javax.swing.table.DefaultTableModel;

	/**
	 * Klasse MyTable.
	 * Erweiterung der Klasse DefaultTableModel
	 * ueberschreibt die Methode isCellEditable von der Oberklasse
	 * damit man die 1. Spalte der Tabelle nicht ändern kann
	 * @author Maksim Imaev
	 *
	 */
public class MyTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public MyTable(int row, int column) {
		super(row, column);
	}
	@Override
	public boolean isCellEditable(int row, int column) {
		switch (column) {
		case 0:
			return false;
		case 1:
			return true;
		default:
			return true;
		}
		
	}
}
