package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Berechtigungen. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Berechtigung {

	private int ID;
	private String Bezeichnung;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getBezeichnung() {
		return Bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		Bezeichnung = bezeichnung;
	}

}
