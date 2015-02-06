package datenbank.provider;

import gui.main;

import java.util.ArrayList;

import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;
import objects.Login;
import objects.Zeugnisposition;
import datenbank.StandardDAO;
import datenbank.mySqlDAO.MySqlAusbilderDAO;
import datenbank.mySqlDAO.MySqlAzubiDAO;
import datenbank.mySqlDAO.MySqlBerechtigungDAO;
import datenbank.mySqlDAO.MySqlBetriebDAO;
import datenbank.mySqlDAO.MySqlFachDAO;
import datenbank.mySqlDAO.MySqlKlasseDAO;
import datenbank.mySqlDAO.MySqlLehrerDAO;
import datenbank.mySqlDAO.MySqlLoginDatenDAO;
import datenbank.mySqlDAO.MySqlNoteDAO;
import datenbank.mySqlDAO.MySqlZeugnisDAO;
import datenbank.mySqlDAO.MySqlZeugnisPositionDAO;

public class MySqlDataProvider extends StandardDataProvider{
	
	//Erstellung der DAO-Objekte zur Verwendung der MySQL-Methoden
	MySqlAusbilderDAO ausbilderDAO = new MySqlAusbilderDAO();
	MySqlAzubiDAO azubiDAO = new MySqlAzubiDAO();
	MySqlBerechtigungDAO berechtigungDAO = new MySqlBerechtigungDAO();
	MySqlBetriebDAO betriebDAO = new MySqlBetriebDAO();
	MySqlFachDAO fachDAO = new MySqlFachDAO();
	MySqlKlasseDAO klasseDAO = new MySqlKlasseDAO();
	MySqlLehrerDAO lehrerDAO = new MySqlLehrerDAO();
	MySqlLoginDatenDAO loginDatenDAO = new MySqlLoginDatenDAO();
	MySqlNoteDAO noteDAO = new MySqlNoteDAO();
	MySqlZeugnisDAO zeugnisDAO = new MySqlZeugnisDAO();
	MySqlZeugnisPositionDAO zeugnisPositionDAO = new MySqlZeugnisPositionDAO();
	ArrayList<StandardDAO> daoListe = new ArrayList<StandardDAO>();
	
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
		
		//TODO SELECT BEI AZUBI
		
		return null;
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
	public boolean gibtKlasse(Klasse klasse) {
		return klasseDAO.isVorhanden(klasse);
	}

	@Override
	public ArrayList<Zeugnisposition> gibPositionenZuZeugnis() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean insert(Object o) {
		try {
			for (StandardDAO dao : daoListe) {
				if (dao.getClassName().compareTo(o.getClass().getName()) == 0) {
					dao.insert(o);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			// Datenbankfehler -> Fehlermeldung zurückgeben.
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean update(Object o) {
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

	@Override
	public boolean delete(Object o) {
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

}
