package businesslogik;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import datenbank.provider.StandardDataProvider;

/**
 * @author backs.kristin-anna, imeav.maksim
 * 
 *         Verbindung zwischen GUI und Businesslogik. Der Konstruktor dieser
 *         Klasse wird aus der Businesslogik heraus aufgerufen und startet die
 *         GUI.
 * @author backs.kristin-anna
 * 
 *         Hauptklasse der Business-Logik. Diese Klasse wird aus der
 *         Main-Methode aus aufgerufen und bereitet alle fuer den Programmablauf
 *         noetige vor.
 * 
 *         DPS = Datenpersistierungssystem
 */

public class Verwaltung {

	private StandardDataProvider dbRef = null; // Referenz auf das DPS

	/**
	 * Konstruktor
	 */
	public Verwaltung() {
		if ((dbRef = StandardDataProvider.getInstance()) == null) {
			JOptionPane
					.showMessageDialog(
							new JFrame(),
							"Das Programm wurde nicht ordnungsgem‰ﬂ konfiguriert."
									+ "\nBitte sprechen Sie mit dem zust‰ndigen Administrator.");
			System.exit(0);
		}

		// TO DO RUFE GUI AUF
//		Delete delete = new Delete(dbRef);
//		Insert insert = new Insert(dbRef);
//		Update update = new Update(dbRef);
//		LeseAus lese = new LeseAus(dbRef);

	}
}
