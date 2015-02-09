package businesslogik.dataprovider;


import java.util.ArrayList;

import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;
import objects.Login;
import objects.StandardValueObject;
import objects.Zeugnis;
import objects.Zeugnisposition;
import datenbank.dao.StandardDAO;
import datenbank.dao.mySQL.MySqlAusbilderDAO;
import datenbank.dao.mySQL.MySqlAzubiDAO;
import datenbank.dao.mySQL.MySqlBerechtigungDAO;
import datenbank.dao.mySQL.MySqlBetriebDAO;
import datenbank.dao.mySQL.MySqlFachDAO;
import datenbank.dao.mySQL.MySqlKlasseDAO;
import datenbank.dao.mySQL.MySqlLehrerDAO;
import datenbank.dao.mySQL.MySqlLoginDatenDAO;
import datenbank.dao.mySQL.MySqlNoteDAO;
import datenbank.dao.mySQL.MySqlZeugnisDAO;
import datenbank.dao.mySQL.MySqlZeugnisPositionDAO;
/**
 * 
 * @author mertmann.justin
 * MySqlDataProvider delegiert die Methoden an die jeweiligen DAOS die für die einzelnen Objekte zuständig sind.
 */
public class MySqlDataProvider extends StandardDataProvider{
	
	//Erstellung der DAO-Objekte zur Verwendung der MySQL-Methoden
	private MySqlAusbilderDAO ausbilderDAO = new MySqlAusbilderDAO();
	private MySqlAzubiDAO azubiDAO = new MySqlAzubiDAO();
	private MySqlBerechtigungDAO berechtigungDAO = new MySqlBerechtigungDAO();
	private MySqlBetriebDAO betriebDAO = new MySqlBetriebDAO();
	private MySqlFachDAO fachDAO = new MySqlFachDAO();
	private MySqlKlasseDAO klasseDAO = new MySqlKlasseDAO();
	private MySqlLehrerDAO lehrerDAO = new MySqlLehrerDAO();
	private MySqlLoginDatenDAO loginDatenDAO = new MySqlLoginDatenDAO();
	private MySqlNoteDAO noteDAO = new MySqlNoteDAO();
	private MySqlZeugnisDAO zeugnisDAO = new MySqlZeugnisDAO();
	private MySqlZeugnisPositionDAO zeugnisPositionDAO = new MySqlZeugnisPositionDAO();
	@SuppressWarnings("rawtypes")
	private ArrayList<StandardDAO> daoListe = new ArrayList<StandardDAO>();
	private static MySqlDataProvider provider = null;
	/**
	 * Der Konstruktor von MySqlDataProvider erstellt eine Liste, in der sämtliche DAOS gespeichert werden
	 */
	protected MySqlDataProvider(){
		daoListe.add(ausbilderDAO);
		daoListe.add(azubiDAO);
		daoListe.add(berechtigungDAO);
		daoListe.add(betriebDAO);
		daoListe.add(fachDAO);
		daoListe.add(klasseDAO);
		daoListe.add(lehrerDAO);
		daoListe.add(loginDatenDAO);
		daoListe.add(noteDAO);
		daoListe.add(zeugnisDAO);
		daoListe.add(zeugnisPositionDAO);
	}
	
	public static MySqlDataProvider getInstance() {
		if(provider == null) {
			provider = new MySqlDataProvider();
		}
		return provider;
	}
	
	@Override
	public ArrayList<Lehrer> gibAlleLehrer() {
		return lehrerDAO.getAll();
	}

	@Override
	public ArrayList<Klasse> gibAlleKlassen() {
		return klasseDAO.getAll();
	}

	@Override
	public ArrayList<Betrieb> gibAlleBetriebe() {
		return betriebDAO.getAll();
	}

	@Override
	public ArrayList<Ausbilder> gibAlleAusbilder() {
		return ausbilderDAO.getAll();
	}

	@Override
	public ArrayList<Azubi> gibAzubiVon(Klasse k) {
		return azubiDAO.gibAzubisZuKlasse(k);
	}

	@Override
	public ArrayList<Fach> gibAlleFaecher() {
		return fachDAO.getAll();
	}

	@Override
	public boolean gibtLogin(String login) {
		Login loginObject = new Login();
		loginObject.setLoginName(login);
	return loginDatenDAO.isVorhanden(loginObject);
	}

	@Override
	public boolean gibtAzubi(Azubi azubi) {
		return azubiDAO.isVorhanden(azubi);
	}

	@Override
	public boolean gibtLehrer(Lehrer lehrer) {
		return lehrerDAO.isVorhanden(lehrer);
	}

	@Override
	public boolean gibtAusbilder(Ausbilder ausbilder) {
		return ausbilderDAO.isVorhanden(ausbilder);
	}

	@Override
	public boolean gibtBetrieb(Betrieb betrieb) {
		return betriebDAO.isVorhanden(betrieb);
	}
	
	@Override
	public boolean checkLogin(Login login){
		return loginDatenDAO.checkLogin(login);
	}

	@Override
	public boolean gibtKlasse(Klasse klasse) {
		return klasseDAO.isVorhanden(klasse);
	}

	@Override
	public ArrayList<Zeugnisposition> gibPositionenZuZeugnis(Zeugnis z) {
		ArrayList<Zeugnisposition> liste = zeugnisPositionDAO.gibPositionenZuZeugnis(z);
		return liste;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StandardValueObject insert(StandardValueObject o) {
		try {
			for (StandardDAO dao : daoListe) {
				if (dao.getClassName().compareTo(o.getClass().getName()) == 0) {
					o = dao.insert(o);
					return o;
				}
			}
			return null;
		} catch (Exception e) {
			// Datenbankfehler -> Fehlermeldung zurückgeben.
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean update(StandardValueObject o) {
		try {
			for (StandardDAO dao : daoListe) {
				if (dao.getClassName().compareTo(o.getClass().getName()) == 0) {
					dao.update(o);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			// Datenbankfehler -> Fehlermeldung zurückgeben.
			return false;
		}
	}
	
	public static void main(String[] args) {
		StandardDataProvider.getInstance().update(new Azubi());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean delete(StandardValueObject o) {
		try {
			for (StandardDAO dao : daoListe) {
				if (dao.getClassName().compareTo(o.getClass().getName()) == 0) {
					dao.delete(o);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			// Datenbankfehler -> Fehlermeldung zurückgeben.
			return false;
		}
	}

	@Override
	public ArrayList<Zeugnis> gibAlleZeugnisse() {
		return zeugnisDAO.getAll();
	}
	
	@Override
	public Login getLoginByLoginDaten(Login login) {
		return loginDatenDAO.getLoginByLoginDaten(login);
	}

}
