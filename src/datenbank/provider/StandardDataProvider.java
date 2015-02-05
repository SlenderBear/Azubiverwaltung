package datenbank.provider;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import datenbank.PropertyHandling;
import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;

/**
 * Stellt den {@link StandardDataProvider} dar. Er fasst alle Methoden, die den
 * Zugriff auf die Datenbank ermöglichen, zusammen.
 * 
 * @author dunkel.gregor
 * 
 */
public abstract class StandardDataProvider {

	public static final String DB_MYSQL = "mysql";
	public static final String DB_SQLITE = "sqlite";
	public static final String DB_PROPERTY = "db";
	private String akt_db;
	PropertyHandling propertieHandler;

	/**
	 * Liefert alle Lehrer.
	 * @return {@link ArrayList}
	 */
	public abstract ArrayList<Lehrer> gibAlleLehrer();

	/**
	 * Liefert alle Klassen.
	 * @return {@link ArrayList}
	 */
	public abstract ArrayList<Klasse> gibAlleKlassen();

	/**
	 * Liefert alle Betriebe.
	 * @return {@link ArrayList}
	 */
	public abstract ArrayList<Betrieb> gibAlleBetriebe();

	/**
	 * Liefert alle Ausbilder.
	 * @return {@link ArrayList}
	 */
	public abstract ArrayList<Ausbilder> gibAlleAusbilder();

	/**
	 * Liefert alle Azubis einer Klasse.
	 * @param k {@link Klasse}
	 * @return {@link ArrayList}
	 */
	public abstract ArrayList<Azubi> gibAzubiVon(Klasse k);

	/**
	 * Liefert alle Fächer-.
	 * @return {@link ArrayList}
	 */
	public abstract ArrayList<Fach> gibAlleFaecher();
	
	
	/**
	 * Prüft ob Loginname bereits vorhanden.
	 * @param login {@link String}
	 * @return true wenn vorhanden.
	 */
	public abstract boolean gibtLogin(String login);

	public abstract boolean gibtAzubi(Azubi azubi);

	public abstract boolean gibtLehrer(Lehrer lehrer);

	public abstract boolean gibtAusbilder(Ausbilder ausbilder);

	public abstract boolean gibtBetrieb(Betrieb betrieb);

	public abstract boolean gibtKlasse(Klasse klasse);

	/**
	 * Propertie-String wird übergeben und der eingestellte Provider zurück
	 * geliefert.
	 * 
	 * @param db
	 *            String
	 * @return {@link StandardDataProvider}
	 */
	public StandardDataProvider getDataProvider(String db) {
		if (db.compareTo(DB_MYSQL) == 0) {
			akt_db = DB_MYSQL;
			return new MySqlDataProvider();
		} else if (db.compareTo(DB_SQLITE) == 0) {
			akt_db = DB_SQLITE;
			return new SqliteDataProvider();
		}
		return null;
	}
	
	/**
	 * Konstruktor.
	 * Initialisiert den DataProvider.
	 * Wirft eine Exception wenn DB nicht gestartet werden konnte.
	 */
	public StandardDataProvider() {
		if(initAndGetProvider() == null) {
			throw new RuntimeException("DB konnte nicht gestartet werden.");
		}
	}

	/**
	 * Initialisiert den DataProvider und liefert diesen zurück.
	 * 
	 * @return {@link StandardDataProvider} null wenn Property Empty oder keine
	 *         entsprechende Datenbank gefunden werden konnte.
	 */
	private StandardDataProvider initAndGetProvider() {
		if (propertieHandler == null) {
			propertieHandler = new PropertyHandling();
		}
		String db_property = propertieHandler.liesPropAus(DB_PROPERTY);
		if (db_property.compareTo(PropertyHandling.PROP_EMPTY) == 0) {
			return null;
		}

		return getDataProvider(db_property);
	}

	/**
	 * Ändert die Einstellung im verwaltung.properties.
	 * 
	 * @param db
	 *            String
	 * @return true: der DataProvider wurde geändert <br>
	 *         false:  hat keinen passenden Provider gefunden wurde.
	 *         null: Wurde nicht geändert, da bereits ausgewählt.
	 */
	public Boolean changeDataProvider(String db) {
		if (db.compareTo(akt_db) == 0) {
			return null;
		}
		if (db.compareTo(DB_MYSQL) == 0) {
			propertieHandler.schreibeProp(DB_PROPERTY, DB_MYSQL);
			return true;
		} else if (db.compareTo(DB_SQLITE) == 0) {
			propertieHandler.schreibeProp(DB_PROPERTY, DB_SQLITE);
			return true;
		}

		return false;
	}
}
