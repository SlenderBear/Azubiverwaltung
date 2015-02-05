package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Lehrern. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Lehrer {

	private String ID;

	private String name;
	private String vorname;
	private String telefon;
	private Login login;
	
	public Lehrer() {
		// TODO Auto-generated constructor stub
	}
	public Lehrer(String name, String vorname, String telefon, Login login) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.telefon = telefon;
		this.login = login;
	}

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

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	@Override
	public String toString() {
		return name+" , "+vorname;
	}

}
