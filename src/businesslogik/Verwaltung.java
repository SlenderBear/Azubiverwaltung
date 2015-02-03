package businesslogik;

import gui.GUIController;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Verwaltung {

	private enum DBOptions {
		EMPTY, MYSQL, XML
	};

	private DBOptions dbWahl;
	private Object dbRef = null;
	private Speichere speicher;


	public Verwaltung() {
		Properties properties = new Properties();
		try {
			BufferedInputStream stream = new BufferedInputStream(
					new FileInputStream("verwaltung.properties"));
			properties.load(stream);
			stream.close();
			dbWahl = DBOptions.valueOf(properties.getProperty("db"));
			referenziereDB();
			speicher = new Speichere(dbRef);

			@SuppressWarnings("unused")
			GUIController GuiCon = new GUIController(this, speicher);

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(
							new JFrame(),
							"Die Anwedung konnte nicht gestartet werden.\nBitte sprechen Sie mit dem zust�ndigen Administrator.");
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
							"Diese Anwendung wurde nicht ordnungsgem�� konfiguriert.\nBitte sprechen Sie mit dem zust�ndigen Administrator.");
			System.exit(0);
			break;
		case MYSQL: // TO DO Referenziere MySQL Datenbank
			break;
		case XML: // TO DO Referenziere XML
			break;

		}

	}

}
