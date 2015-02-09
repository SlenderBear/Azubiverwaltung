package datenbank.dao.mySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Azubi;
import objects.Klasse;
import datenbank.connector.MySQLConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlAusbilderDAO enthält sämtliche Funktionen zur Datenbankanbindung des Azubiobjektes
 */
public class MySqlAzubiDAO implements StandardDAO<Azubi>{
	private MySqlKlasseDAO daoKlasse= new MySqlKlasseDAO();
	private MySqlAusbilderDAO daoAusbilder = new MySqlAusbilderDAO();
	
	private static final String DAO_NAME= Azubi.class.getName();

	@Override
	public Azubi insert(Azubi t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO azubi values('" 
				+ guid 
				+ "','" + t.getName()
				+ "','" + t.getVorname()
				+ "','" + t.getTelefon() 
				+ "','" + t.getMobiltelefon() 
				+ "','" + t.getEmail()
				+ "','" + t.getStrasse()
				+ "','" + t.getPlz()
				+ "','" + t.getOrt()
				+ "','" + t.getGeburtsdatum()
				+ "','" + t.getVolljaehrigkeit()
				+ "','" + t.getInklusionsberatung()
				+ "','" + t.getGeburtsort()
				+ "','" + t.getGeburtsname()
				+ "','" + t.getGeburtsland()
				+ "','" + t.getStaatsangehoerigkeit_1()
				+ "','" + t.getStaatsangehoerigkeit_2()
				+ "'," + t.getZuzugsjahr()
				+ ",'" + t.getGeburtsland_Vater()
				+ "','" + t.getGeburtsland_Mutter()
				+ "','" + t.getGeschlecht()
				+ "','" + t.getKonfession()
				+ "','" + t.getFachrichtung()
				+ "'," + t.getLehrjahr()
				+ ",'" + t.getAusbildungsbeginn()
				+ "','" + t.getAusbildungsende()
				+ "','" + t.getLetzte_Schulform()
				+ "','" + t.getSchulabschluss()
				+ "','" + t.getAnmerkung_Schulabschluss()
				+ "'," + t.getFehltage()				
				+ ",'" + t.getKlasse().getID()
				+ "','" + t.getAusbilder().getID()
				+ "');";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Azubi t) {
		String sql = "UPDATE azubi"+
				"SET "
				+ "name='"+t.getName()
				+"',vorname='"+t.getVorname()
				+"',telefonnummer='"+t.getTelefon()
				+"',mobiltelefon='"+t.getMobiltelefon()
				+"',email='"+t.getEmail()
				+"',strasse='"+t.getEmail()
				+"',plz='"+t.getEmail()
				+"',ort='"+t.getEmail()
				+"',geburtsdatum='"+t.getEmail()
				+"',volljaehrigkeit='"+t.getVolljaehrigkeit()
				+"',inklusionsberatung='"+t.getInklusionsberatung()
				+"',geburtsort='"+t.getGeburtsort()
				+"',geburtsname='"+t.getGeburtsname()				
				+"',geburtsland='"+t.getGeburtsland()
				+"',staatangehoerigkeit_1='"+t.getStaatsangehoerigkeit_1()
				+"',staatangehoerigkeit_2='"+t.getStaatsangehoerigkeit_2()
				+"',zuzugsjahr="+t.getZuzugsjahr()
				+",geburtsland_vater='"+t.getGeburtsland_Vater() 
				+"',geburtsland_mutter='"+t.getGeburtsland_Mutter() 
				+"',geschlecht='"+t.getGeschlecht()
				+"',konfession='"+t.getKonfession()
				+"',fachrichtung='"+t.getFachrichtung()
				+"',lehrjahr="+t.getLehrjahr()
				+",ausbildungsbeginn='"+t.getAusbildungsbeginn()
				+"',ausbildungsende='"+t.getAusbildungsende()
				+"',letzte_schulform='"+t.getLetzte_Schulform()
				+"',schulabschluss='"+t.getSchulabschluss()
				+"',anmerkung_schulabschluss='"+t.getAnmerkung_Schulabschluss()
				+"',fehltage="+t.getFehltage()
				+",klasseid='"+t.getKlasse().getID()
				+"',ausbilderid='"+t.getAusbilder().getID()
				+"' WHERE azubiid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public boolean delete(Azubi t) {
		String sql = "delete from azubi"+
				" WHERE azubiid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public ArrayList<Azubi> getAll() {
		String sql = "select * from azubi;";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Azubi> azubiListe = new ArrayList<Azubi>();
		try{
		 while (rs.next())
	      {
			 Azubi a = new Azubi();
	        a.setID(rs.getString("azubiid"));
	        a.setName(rs.getString("name"));
	        a.setVorname(rs.getString("vorname"));
	        a.setTelefon(rs.getString("telefonnummer"));
	        a.setEmail(rs.getString("email"));  
	        a.setStrasse(rs.getString("strasse"));  
	        a.setPlz(rs.getString("plz"));  
	        a.setMobiltelefon(rs.getString("mobiltelefon"));  
	        a.setOrt(rs.getString("ort"));  
	        a.setGeburtsdatum(rs.getString("geburtsdatum"));  
	        a.setVolljaehrigkeit(rs.getString("volljaehrigkeit").toCharArray()[0]);  
	        a.setInklusionsberatung(rs.getString("inklusionsberatung").toCharArray()[0]);  
	        a.setGeburtsort(rs.getString("geburtsort"));  
	        a.setGeburtsname(rs.getString("geburtsname"));  
	        a.setGeburtsland(rs.getString("geburtsland"));  
	        a.setStaatsangehoerigkeit_1(rs.getString("Staatsangehoerigkeit_1"));  
	        a.setStaatsangehoerigkeit_2(rs.getString("Staatsangehoerigkeit_2"));  
	        a.setZuzugsjahr(rs.getInt("Zuzugsjahr"));  
	        a.setGeburtsland_Vater(rs.getString("Geburtsland_Vater"));  
	        a.setGeburtsland_Mutter(rs.getString("Geburtsland_Mutter"));  
	        a.setGeschlecht(rs.getString("Geschlecht").toCharArray()[0]);  
	        a.setKonfession(rs.getString("Konfession"));  
	        a.setFachrichtung(rs.getString("Fachrichtung").toCharArray()[0]);  
	        a.setLehrjahr(rs.getInt("Lehrjahr"));  
	        a.setAusbildungsbeginn(rs.getString("Ausbildungsbeginn"));  
	        a.setAusbildungsende(rs.getString("Ausbildungsende"));  
	        a.setLetzte_Schulform(rs.getString("Letzte_Schulform"));  
	        a.setSchulabschluss(rs.getString("Schulabschluss"));  
	        a.setAnmerkung_Schulabschluss(rs.getString("Anmerkung_Schulabschluss"));  
	        a.setFehltage(rs.getInt("Fehltage"));  
	        a.setKlasse(daoKlasse.getByGuid(rs.getString("KlassenID")));  
	        a.setAusbilder(daoAusbilder.getByGuid(rs.getString("AusbilderID")));  
	        
	        azubiListe.add(a);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLAzubiDAO");
		}
		return azubiListe;
	}

	@Override
	public Azubi getByGuid(String guid) {
		String sql = "select * from azubi where azubiid='"+guid+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Azubi b = new Azubi();
		try {
			rs.next();
		        b.setID(rs.getString("azubiid"));
		        b.setName(rs.getString("name"));
		        b.setVorname(rs.getString("vorname"));
		        b.setTelefon(rs.getString("telefonnummer"));
		        b.setEmail(rs.getString("email"));  
		        b.setStrasse(rs.getString("strasse"));  
		        b.setPlz(rs.getString("plz"));  
		        b.setMobiltelefon(rs.getString("mobiltelefon"));  
		        b.setOrt(rs.getString("ort"));  
		        b.setGeburtsdatum(rs.getString("geburtsdatum"));  
		        b.setVolljaehrigkeit(rs.getString("volljaehrigkeit").toCharArray()[0]);  
		        b.setInklusionsberatung(rs.getString("inklusionsberatung").toCharArray()[0]);  
		        b.setGeburtsort(rs.getString("geburtsort"));  
		        b.setGeburtsname(rs.getString("geburtsname"));  
		        b.setGeburtsland(rs.getString("geburtsland"));  
		        b.setStaatsangehoerigkeit_1(rs.getString("Staatsangehoerigkeit_1"));  
		        b.setStaatsangehoerigkeit_2(rs.getString("Staatsangehoerigkeit_2"));  
		        b.setZuzugsjahr(rs.getInt("Zuzugsjahr"));  
		        b.setGeburtsland_Vater(rs.getString("Geburtsland_Vater"));  
		        b.setGeburtsland_Mutter(rs.getString("Geburtsland_Mutter"));  
		        b.setGeschlecht(rs.getString("Geschlecht").toCharArray()[0]);  
		        b.setKonfession(rs.getString("Konfession"));  
		        b.setFachrichtung(rs.getString("Fachrichtung").toCharArray()[0]);  
		        b.setLehrjahr(rs.getInt("Lehrjahr"));  
		        b.setAusbildungsbeginn(rs.getString("Ausbildungsbeginn"));  
		        b.setAusbildungsende(rs.getString("Ausbildungsende"));  
		        b.setLetzte_Schulform(rs.getString("Letzte_Schulform"));  
		        b.setSchulabschluss(rs.getString("Schulabschluss"));  
		        b.setAnmerkung_Schulabschluss(rs.getString("Anmerkung_Schulabschluss"));  
		        b.setFehltage(rs.getInt("Fehltage"));  
		        b.setKlasse(daoKlasse.getByGuid(rs.getString("KlassenID")));  
		        b.setAusbilder(daoAusbilder.getByGuid(rs.getString("AusbilderID")));  
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean isVorhanden(Azubi t) {
		String sql = "select * from azubi where "
				+ "name='"+t.getName()
				+"',vorname='"+t.getVorname()
				+"',telefonnummer='"+t.getTelefon()
				+"',mobiltelefon='"+t.getMobiltelefon()
				+"',email='"+t.getEmail()
				+"',strasse='"+t.getEmail()
				+"',plz='"+t.getEmail()
				+"',ort='"+t.getEmail()
				+"',geburtsdatum='"+t.getEmail()
				+"',volljaehrigkeit='"+t.getVolljaehrigkeit()
				+"',inklusionsberatung='"+t.getInklusionsberatung()
				+"',geburtsort='"+t.getGeburtsort()
				+"',geburtsname='"+t.getGeburtsname()				
				+"',geburtsland='"+t.getGeburtsland()
				+"',staatangehoerigkeit_1='"+t.getStaatsangehoerigkeit_1()
				+"',staatangehoerigkeit_2='"+t.getStaatsangehoerigkeit_2()
				+"',zuzugsjahr="+t.getZuzugsjahr()
				+",geburtsland_vater='"+t.getGeburtsland_Vater() 
				+"',geburtsland_mutter='"+t.getGeburtsland_Mutter() 
				+"',geschlecht='"+t.getGeschlecht()
				+"',konfession='"+t.getKonfession()
				+"',fachrichtung='"+t.getFachrichtung()
				+"',lehrjahr="+t.getLehrjahr()
				+",ausbildungsbeginn='"+t.getAusbildungsbeginn()
				+"',ausbildungsende='"+t.getAusbildungsende()
				+"',letzte_schulform='"+t.getLetzte_Schulform()
				+"',schulabschluss='"+t.getSchulabschluss()
				+"',anmerkung_schulabschluss='"+t.getAnmerkung_Schulabschluss()
				+"',fehltage="+t.getFehltage()
				+",klasseid='"+t.getKlasse().getID()
				+"',ausbilderid='"+t.getAusbilder().getID()
				+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
			try {
				return rs.first();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public String getClassName() {
		return DAO_NAME;
	}
	/**
	 * 
	 * @param Übergabe eines Klassenobjektes
	 * @return Rückgabe aller Azubis, die sich in dieser Klasse befinden
	 */
	public ArrayList<Azubi> gibAzubisZuKlasse(Klasse k){
		String sql = "select * from azubi where klasseid='"
				+ k.getID() + "';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Azubi> azubiListe = new ArrayList<Azubi>(); 
		try{
			 while (rs.next())
		      {
				 Azubi a = new Azubi();
		        a.setID(rs.getString("azubiid"));
		        a.setName(rs.getString("name"));
		        a.setVorname(rs.getString("vorname"));
		        a.setTelefon(rs.getString("telefonnummer"));
		        a.setEmail(rs.getString("email"));  
		        a.setStrasse(rs.getString("strasse"));  
		        a.setPlz(rs.getString("plz"));  
		        a.setMobiltelefon(rs.getString("mobiltelefon"));  
		        a.setOrt(rs.getString("ort"));  
		        a.setGeburtsdatum(rs.getString("geburtsdatum"));  
		        a.setVolljaehrigkeit(rs.getString("volljaehrigkeit").toCharArray()[0]);  
		        a.setInklusionsberatung(rs.getString("inklusionsberatung").toCharArray()[0]);  
		        a.setGeburtsort(rs.getString("geburtsort"));  
		        a.setGeburtsname(rs.getString("geburtsname"));  
		        a.setGeburtsland(rs.getString("geburtsland"));  
		        a.setStaatsangehoerigkeit_1(rs.getString("Staatsangehoerigkeit_1"));  
		        a.setStaatsangehoerigkeit_2(rs.getString("Staatsangehoerigkeit_2"));  
		        a.setZuzugsjahr(rs.getInt("Zuzugsjahr"));  
		        a.setGeburtsland_Vater(rs.getString("Geburtsland_Vater"));  
		        a.setGeburtsland_Mutter(rs.getString("Geburtsland_Mutter"));  
		        a.setGeschlecht(rs.getString("Geschlecht").toCharArray()[0]);  
		        a.setKonfession(rs.getString("Konfession"));  
		        a.setFachrichtung(rs.getString("Fachrichtung").toCharArray()[0]);  
		        a.setLehrjahr(rs.getInt("Lehrjahr"));  
		        a.setAusbildungsbeginn(rs.getString("Ausbildungsbeginn"));  
		        a.setAusbildungsende(rs.getString("Ausbildungsende"));  
		        a.setLetzte_Schulform(rs.getString("Letzte_Schulform"));  
		        a.setSchulabschluss(rs.getString("Schulabschluss"));  
		        a.setAnmerkung_Schulabschluss(rs.getString("Anmerkung_Schulabschluss"));  
		        a.setFehltage(rs.getInt("Fehltage"));  
		        a.setKlasse(daoKlasse.getByGuid(rs.getString("KlassenID")));  
		        a.setAusbilder(daoAusbilder.getByGuid(rs.getString("AusbilderID")));  
		        
		        azubiListe.add(a);
		      }
			}catch(Exception e){
				System.out.println("Fehler in MySQLAzubiDAO");
			}
		return azubiListe;
	}


}
