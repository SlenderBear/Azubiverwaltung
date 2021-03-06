package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung vob Ausbildern. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Ausbilder implements StandardValueObject {

	private String ID;
	private String name;
	private String vorname;
	private String telefon;
	private String email;

	private Betrieb betrieb;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Betrieb getBetrieb() {
		return betrieb;
	}

	public void setBetrieb(Betrieb betrieb) {
		this.betrieb = betrieb;
	}
	
	@Override
	public String toString() {
		return name+" , "+vorname;
	}

}
