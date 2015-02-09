package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Faechern. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Fach implements StandardValueObject{

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
	
	@Override
	public String toString() {
		return Bezeichnung;
	}

}
