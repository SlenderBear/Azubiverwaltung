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
	private int jahr;
	
	public Klasse() {
		// TODO Auto-generated constructor stub
	}
	public Klasse(String id, String bezeichnung, Lehrer lehrer, int jahr) {
		this.ID = id;
		this.Bezeichnung = bezeichnung;
		this.lehrer = lehrer;
		this.jahr = jahr;
	}

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

	@Override
	public String toString() {
		return Bezeichnung;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

}
