package businesslogik;

import gui.GUIConnector;

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
	private Delete delete;
	private Insert insert;
	private Update update;
	private LeseAus lese;


	public Verwaltung() {
		Properties properties = new Properties();
		try {
			BufferedInputStream stream = new BufferedInputStream(
					new FileInputStream("verwaltung.properties"));
			properties.load(stream);
			stream.close();
			dbWahl = DBOptions.valueOf(properties.getProperty("db"));
			referenziereDB();
			delete = new Delete(dbRef);
			insert = new Insert(dbRef);
			update = new Update(dbRef);
			lese = new LeseAus(dbRef);

			@SuppressWarnings("unused")
			GUIConnector GuiCon = new GUIConnector(delete, insert, update, lese);

		} catch (IOException e) {
			e.printStackTrace();
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
