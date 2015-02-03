package objects;

public class Ausbilder {

	private int ID;
	private String vorname;
	private String nachname;
	private String telefon;
	private String eMai;
	private String IDBetrieb;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getID_Betrieb() {
		return IDBetrieb;
	}

	public void setID_Betrieb(String iD_Betrieb) {
		IDBetrieb = iD_Betrieb;
	}

}
