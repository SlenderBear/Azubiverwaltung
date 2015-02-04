package businesslogik;

import gui.GUIConnector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author backs.kristin-anna
 * 
 *         Hauptklasse der Business-Logik. Diese Klasse wird aus der
 *         Main-Methode aus aufgerufen und bereitet alle fuer den Programmablauf
 *         noetige vor.
 * 
 *         DPS = Datenpersistierungssystem
 */

public class Verwaltung {

	// Enum zur Darstellung der angebotenen DPS
	private enum DBOptions {
		EMPTY, MYSQL, XML
	};

	private DBOptions dbWahl = null; // gewaehltes DPS

	private Object dbRef = null; // Referenz auf das DPS

	private final String filenameProp = "verwaltung.properties";

	/**
	 * Konstruktor
	 */
	public Verwaltung() {
		dbWahl = DBOptions.valueOf(liesPropAus("db"));
		referenziereDB();
		// Erstellen des GUI-Konnektors
		@SuppressWarnings("unused")
		GUIConnector GuiCon = new GUIConnector((new Delete(dbRef)),
				(new Insert(dbRef)), (new Update(dbRef)), (new LeseAus(dbRef)));

	}

	public void referenziereDB() {
		switch (dbWahl) {
		case EMPTY:
		default:
			JOptionPane
					.showMessageDialog(
							new JFrame(),
							"Diese Anwendung wurde nicht ordnungsgem‰ﬂ konfiguriert.\nBitte sprechen Sie mit dem zust‰ndigen Administrator.");
			System.exit(0);
			break;
		case MYSQL:
			break;
		case XML: // TO DO Referenziere XML
			break;

		}

	}

	public String liesPropAus(String prop) {
		// Auslesen des DPS-Properties
		Properties properties = new Properties();
		try {
			BufferedInputStream stream;
			stream = new BufferedInputStream(new FileInputStream(filenameProp));
			properties.load(stream);
			stream.close();
		} catch (Exception e) {
			return "EMPTY";
		}
		return properties.getProperty(prop);
	}

	public boolean schreibeProp(String prop, String value) {
		Properties props = new Properties();
		props.setProperty("db", value);
		OutputStream out;
		try {
			out = new FileOutputStream(new File(filenameProp));
			props.store(out, "");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
