package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Berechtigungen. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Berechtigung {

	private String ID;
	private String Bezeichnung;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBezeichnung() {
		return Bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		Bezeichnung = bezeichnung;
	}

}
