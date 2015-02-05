package datenbank.provider;

import java.util.ArrayList;

import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;
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
		return false;
//				loginDatenDAO.getByGuid(guid)
	}

	@Override
	public boolean gibtAzubi(Azubi azubi) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtLehrer(Lehrer lehrer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtAusbilder(Ausbilder ausbilder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtBetrieb(Betrieb betrieb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtKlasse(Klasse klasse) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
