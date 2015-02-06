package datenbank.provider;

import java.util.ArrayList;

import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;
import objects.StandardValueObject;
import objects.Zeugnis;
import objects.Zeugnisposition;
import datenbank.PropertyHandling;

/**
 * Stellt den {@link StandardDataProvider} dar. Er fasst alle Methoden, die den
 * Zugriff auf die Datenbank erm�glichen, zusammen.
 * 
 * @author dunkel.gregor
 * 
 */
public abstract class StandardDataProvider {

	public enum db_optionen {
		MYSQL, SQLITE
	}
	
	private static StandardDataProvider provider = null;
	public static final String DB_PROPERTY = "db";
	private static String akt_db = "";
	private static final PropertyHandling propertieHandler = new PropertyHandling();

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
	 * Liefert alle F�cher-.
	 * @return {@link ArrayList}
	 */
	public abstract ArrayList<Fach> gibAlleFaecher();
	
	/**
	 * Liefert die Zeugnispositionenzum �bergebenen Zeugnis.
	 * @param z {@link Zeugnis}
	 * @return Arraylist mit Zeugnispositionen
	 */
	public abstract ArrayList<Zeugnisposition> gibPositionenZuZeugnis(Zeugnis z);
	
	/**
	 * F�hrt den Insert durch.
	 * @param o {@link Object}
	 * @return
	 */
	public abstract StandardValueObject insert(StandardValueObject o);

	public abstract boolean update(StandardValueObject o);
	
	public abstract boolean delete(StandardValueObject o);
	
	/**
	 * Pr�ft ob Loginname bereits vorhanden.
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
	 * Propertie-String wird �bergeben und der eingestellte Provider zur�ck
	 * geliefert.
	 * 
	 * @param db
	 *            String
	 * @return {@link StandardDataProvider}
	 */
	private static StandardDataProvider getDataProvider(String db) {
		if (db.compareToIgnoreCase(db_optionen.MYSQL.toString()) == 0) {
			akt_db = db_optionen.MYSQL.toString();
			return new MySqlDataProvider();
		} else if (db.compareToIgnoreCase(db_optionen.SQLITE.toString()) == 0) {
			akt_db = db_optionen.SQLITE.toString();
			return new SqliteDataProvider();
		}
		return null;
	}
	
	/**
	 * Liefert die Instanz vom {@link StandardDataProvider}.
	 * @return {@link StandardDataProvider} vom Typ wie in verwaltung.properties
	 * angegeben.
	 */
	public static StandardDataProvider getInstance() {
		if(provider == null) {
			provider = initAndGetProvider();
		}
		return provider;
	}
	
	/**
	 * Initialisiert den DataProvider und liefert diesen zur�ck.
	 * 
	 * @return {@link StandardDataProvider} null wenn Property Empty oder keine
	 *         entsprechende Datenbank gefunden werden konnte.
	 */
	public static StandardDataProvider initAndGetProvider() {
		String db_property = propertieHandler.liesPropAus(DB_PROPERTY);
		if (db_property.compareToIgnoreCase(PropertyHandling.PROP_EMPTY) == 0) {
			//TODO Fehlerbehandlung
			return null;
		}
		return getDataProvider(db_property);
	}

	/**
	 * �ndert die Einstellung im verwaltung.properties.
	 * 
	 * @param db
	 *            String
	 * @return true: der DataProvider wurde ge�ndert <br>
	 *         false:  hat keinen passenden Provider gefunden wurde.
	 *         null: Wurde nicht ge�ndert, da bereits ausgew�hlt.
	 */
	public static Boolean changeDataProvider(String db) {
		if (db.compareTo(akt_db) == 0) {
			return null;
		}
		if (db.compareToIgnoreCase(db_optionen.MYSQL.toString()) == 0) {
			return propertieHandler.schreibeProp(DB_PROPERTY, db_optionen.MYSQL.toString());
		} else if (db.compareToIgnoreCase(db_optionen.SQLITE.toString()) == 0) {
			return propertieHandler.schreibeProp(DB_PROPERTY, db_optionen.SQLITE.toString());
		}

		return false;
	}
}
