package datenbank.provider;

import java.util.ArrayList;

import datenbank.dao.StandardDAO;
import datenbank.dao.sqlite.SqliteAusbilderDAO;
import datenbank.dao.sqlite.SqliteAzubiDAO;
import datenbank.dao.sqlite.SqliteBerechtigungDAO;
import datenbank.dao.sqlite.SqliteBetriebDAO;
import datenbank.dao.sqlite.SqliteFachDAO;
import datenbank.dao.sqlite.SqliteKlasseDAO;
import datenbank.dao.sqlite.SqliteLehrerDAO;
import datenbank.dao.sqlite.SqliteLoginDatenDAO;
import datenbank.dao.sqlite.SqliteNoteDAO;
import datenbank.dao.sqlite.SqliteZeugnisDAO;
import datenbank.dao.sqlite.SqliteZeugnisPositionDAO;
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
/**
 * 
 * @author mertmann.justin
 * Der SqliteDataProvider delegiert die Methoden an die jeweiligen DAOs weiter, die für das passende Objekt zuständig sind
 */
public class SqliteDataProvider extends StandardDataProvider{
	//Erstellung der DAO-Objekte zur Verwendung der Sqlite-Methoden
		SqliteAusbilderDAO ausbilderDAO = new SqliteAusbilderDAO();
		SqliteAzubiDAO azubiDAO = new SqliteAzubiDAO();
		SqliteBerechtigungDAO berechtigungDAO = new SqliteBerechtigungDAO();
		SqliteBetriebDAO betriebDAO = new SqliteBetriebDAO();
		SqliteFachDAO fachDAO = new SqliteFachDAO();
		SqliteKlasseDAO klasseDAO = new SqliteKlasseDAO();
		SqliteLehrerDAO lehrerDAO = new SqliteLehrerDAO();
		SqliteLoginDatenDAO loginDatenDAO = new SqliteLoginDatenDAO();
		SqliteNoteDAO noteDAO = new SqliteNoteDAO();
		SqliteZeugnisDAO zeugnisDAO = new SqliteZeugnisDAO();
		SqliteZeugnisPositionDAO zeugnisPositionDAO = new SqliteZeugnisPositionDAO();
		@SuppressWarnings("rawtypes")
		ArrayList<StandardDAO> daoListe = new ArrayList<StandardDAO>();
		/**
		 * Konstruktor
		 */
		protected SqliteDataProvider(){
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

		@SuppressWarnings("unchecked")
		@Override
		public boolean delete(StandardValueObject o) {
			try {
				for (@SuppressWarnings("rawtypes") StandardDAO dao : daoListe) {
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
