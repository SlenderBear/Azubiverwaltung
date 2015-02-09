package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Azubis. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Azubi implements StandardValueObject{

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
	private int lehrjahr;
	private String geburtsname;
	private String mobiltelefon;
	private String geburtsort;
	private String geburtsland;
	private char volljaehrigkeit;
	private char inklusionsberatung;
	private String konfession;
	private String staatsangehoerigkeit_1;
	private String staatsangehoerigkeit_2;
	private int zuzugsjahr;
	private String geburtsland_Vater;
	private String geburtsland_Mutter;
	private char Fachrichtung;
	private String ausbildungsbeginn;
	private String ausbildungsende;
	private String letzte_Schulform;
	private String schulabschluss;
	private String anmerkung_Schulabschluss;
	private int fehltage;
	
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
	
	@Override
	public String toString() {
		return name+" , "+vorname;
	}
	
	public int getLehrjahr() {
		return lehrjahr;
	}

	public void setLehrjahr(int lehrjahr) {
		this.lehrjahr = lehrjahr;
	}

	public String getGeburtsname() {
		return geburtsname;
	}

	public void setGeburtsname(String geburtsname) {
		this.geburtsname = geburtsname;
	}

	public String getGeburtsort() {
		return geburtsort;
	}

	public void setGeburtsort(String geburtsort) {
		this.geburtsort = geburtsort;
	}

	public String getMobiltelefon() {
		return mobiltelefon;
	}

	public void setMobiltelefon(String mobiltelefon) {
		this.mobiltelefon = mobiltelefon;
	}

	public String getGeburtsland() {
		return geburtsland;
	}

	public void setGeburtsland(String geburtsland) {
		this.geburtsland = geburtsland;
	}

	public char getVolljaehrigkeit() {
		return volljaehrigkeit;
	}

	public void setVolljaehrigkeit(char volljaehrigkeit) {
		this.volljaehrigkeit = volljaehrigkeit;
	}

	public char getInklusionsberatung() {
		return inklusionsberatung;
	}

	public void setInklusionsberatung(char inklusionsberatung) {
		this.inklusionsberatung = inklusionsberatung;
	}

	public String getKonfession() {
		return konfession;
	}

	public void setKonfession(String konfession) {
		this.konfession = konfession;
	}

	public String getStaatsangehoerigkeit_1() {
		return staatsangehoerigkeit_1;
	}

	public void setStaatsangehoerigkeit_1(String staatsangehoerigkeit_1) {
		this.staatsangehoerigkeit_1 = staatsangehoerigkeit_1;
	}

	public String getStaatsangehoerigkeit_2() {
		return staatsangehoerigkeit_2;
	}

	public void setStaatsangehoerigkeit_2(String staatsangehoerigkeit_2) {
		this.staatsangehoerigkeit_2 = staatsangehoerigkeit_2;
	}

	public String getGeburtsland_Vater() {
		return geburtsland_Vater;
	}

	public void setGeburtsland_Vater(String geburtsland_Vater) {
		this.geburtsland_Vater = geburtsland_Vater;
	}

	public String getGeburtsland_Mutter() {
		return geburtsland_Mutter;
	}

	public void setGeburtsland_Mutter(String geburtsland_Mutter) {
		this.geburtsland_Mutter = geburtsland_Mutter;
	}

	public char getFachrichtung() {
		return Fachrichtung;
	}

	public void setFachrichtung(char fachrichtung) {
		Fachrichtung = fachrichtung;
	}

	public String getAusbildungsbeginn() {
		return ausbildungsbeginn;
	}

	public void setAusbildungsbeginn(String ausbildungsbeginn) {
		this.ausbildungsbeginn = ausbildungsbeginn;
	}

	public String getAusbildungsende() {
		return ausbildungsende;
	}

	public void setAusbildungsende(String ausbildungsende) {
		this.ausbildungsende = ausbildungsende;
	}

	public String getLetzte_Schulform() {
		return letzte_Schulform;
	}

	public void setLetzte_Schulform(String letzte_Schulform) {
		this.letzte_Schulform = letzte_Schulform;
	}

	public String getSchulabschluss() {
		return schulabschluss;
	}

	public void setSchulabschluss(String schulabschluss) {
		this.schulabschluss = schulabschluss;
	}

	public String getAnmerkung_Schulabschluss() {
		return anmerkung_Schulabschluss;
	}

	public void setAnmerkung_Schulabschluss(String anmerkung_Schulabschluss) {
		this.anmerkung_Schulabschluss = anmerkung_Schulabschluss;
	}

	public int getFehltage() {
		return fehltage;
	}

	public void setFehltage(int fehltage) {
		this.fehltage = fehltage;
	}

	public int getZuzugsjahr() {
		return zuzugsjahr;
	}

	public void setZuzugsjahr(int zuzugsjahr) {
		this.zuzugsjahr = zuzugsjahr;
	}

}
