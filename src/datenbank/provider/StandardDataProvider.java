package datenbank.provider;

import java.util.ArrayList;

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

	abstract ArrayList<Lehrer> gibAlleLehrer();

	abstract ArrayList<Klasse> gibAlleKlassen();

	abstract ArrayList<Betrieb> gibAlleBetriebe();

	abstract ArrayList<Ausbilder> gibAlleAusbilder();

	abstract ArrayList<Azubi> gibAzubiVon(Klasse k);

	abstract ArrayList<Fach> gibAlleFaecher();

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
	 * Initialisiert den DataProvider und liefert diesen zurück.
	 * 
	 * @return {@link StandardDataProvider} null wenn Property Empty oder keine
	 *         entsprechende Datenbank gefunden werden konnte.
	 */
	public StandardDataProvider initAndGetProvider() {
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
	 * @param db String
	 * @return true: der DataProvider wurde geändert
	 * <br>false: wenn sich der Provider nicht geändert hat oder
	 * 		  kein passender Provider gefunden wurde.
	 */
	public boolean changeDataProvider(String db) {
		if(db.compareTo(akt_db) != 0) {
			return false;
		}
		if(db.compareTo(DB_MYSQL) == 0) {
			propertieHandler.schreibeProp(DB_PROPERTY, DB_MYSQL);
			return true;
		} else if (db.compareTo(DB_SQLITE) == 0) {
			propertieHandler.schreibeProp(DB_PROPERTY, DB_SQLITE);
			return true;
		} 
		
		return false;
	}
}
