package businesslogik;

import gui.MainWindow;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import businesslogik.dataprovider.StandardDataProvider;
import objects.Azubi;
import objects.StandardValueObject;

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
		
		@SuppressWarnings("unused")
		MainWindow window = new MainWindow(dbRef);

	}
	
	/**
	 * Delegiert den Insert an den {@link StandardDataProvider}.
	 * @param o {@link Object}
	 * @return {@link Object} das erstellt wurde.
	 */
	public StandardValueObject insert(StandardValueObject o) {
		return dbRef.insert(o);
	}
	
	/**
	 * Delegiert das Update an den {@link StandardDataProvider}.
	 * @param o {@link Object}
	 * 
	 * @return true wenn erfolgreich.
	 */
	public boolean update(StandardValueObject o)  {
		return dbRef.update(o);
	}
	
	/**
	 * Delegiert den Delete an den {@link StandardDataProvider}.
	 * @param o {@link Object}
	 * @return true wenn erfolgreich.
	 */
	public boolean delete(StandardValueObject o) {
		return dbRef.delete(o);
	}
}
