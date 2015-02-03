package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Azubis. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Azubi {

	private String ID;
	private String name;
	private String vorname;
	private String strasse;
	private String plz;
	private String ort;
	private String telefon;
	private String email;
	private String geburtsdatum;
	private char geschlecht;

	private Betrieb betrieb;
	private Ausbilder ausbilder;
	private Klasse klasse;

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

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public char getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
	}

	public Betrieb getBetrieb() {
		return betrieb;
	}

	public void setBetrieb(Betrieb betrieb) {
		this.betrieb = betrieb;
	}

	public Ausbilder getAusbilder() {
		return ausbilder;
	}

	public void setAusbilder(Ausbilder ausbilder) {
		this.ausbilder = ausbilder;
	}

	public Klasse getKlasse() {
		return klasse;
	}

	public void setKlasse(Klasse klasse) {
		this.klasse = klasse;
	}

}
