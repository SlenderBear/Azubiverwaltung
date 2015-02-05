package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Betrieben. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Betrieb {

	private String ID;
	private String firmenbezeichnung;
	private String strasse;
	private String plz;
	private String ort;
	private String eMail;
	private String telefon;
	private String fax;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirmenbezeichnung() {
		return firmenbezeichnung;
	}

	public void setFirmenbezeichnung(String firmenbezeichnung) {
		this.firmenbezeichnung = firmenbezeichnung;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	@Override
	public String toString() {
		return firmenbezeichnung;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
