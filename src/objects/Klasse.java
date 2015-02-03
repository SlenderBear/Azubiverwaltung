package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von (Schul-)Klassen. Diese Klasse enthaelt
 *         alle noetigen Attribute sowie getter- und setter-Methoden um auf
 *         diese zuzugreifen.
 */

public class Klasse {

	private String ID;
	private String Bezeichnung;
	private Lehrer lehrer;

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

	public Lehrer getLehrer() {
		return lehrer;
	}

	public void setLehrer(Lehrer lehrer) {
		this.lehrer = lehrer;
	}

}
