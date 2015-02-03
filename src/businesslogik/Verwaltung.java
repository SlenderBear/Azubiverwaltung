package businesslogik;

import gui.GUIConnector;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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

	/**
	 * Konstruktor
	 */
	public Verwaltung() {
		try {
			// Auslesen des DPS-Properties
			Properties properties = new Properties();
			BufferedInputStream stream = new BufferedInputStream(
					new FileInputStream("verwaltung.properties"));
			properties.load(stream);
			stream.close();

			// Merken des ausgelesen DPS-Properties
			dbWahl = DBOptions.valueOf(properties.getProperty("db"));
			referenziereDB();

			// Erstellen des GUI-Konnektors
			@SuppressWarnings("unused")
			GUIConnector GuiCon = new GUIConnector((new Delete(dbRef)),
					(new Insert(dbRef)), (new Update(dbRef)), (new LeseAus(
							dbRef)));

			// Im Falle eines Fehlers
		} catch (IOException e) {
			// 
			JOptionPane
					.showMessageDialog(
							new JFrame(),
							"Die Anwedung konnte nicht gestartet werden.\nBitte sprechen Sie mit dem zust‰ndigen Administrator.");
			System.exit(0);
		}

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
		case MYSQL: // TO DO Referenziere MySQL Datenbank
			break;
		case XML: // TO DO Referenziere XML
			break;

		}

	}

}
