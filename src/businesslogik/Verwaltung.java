package businesslogik;

import gui.MainWindow;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import objects.Azubi;
import objects.StandardValueObject;
import objects.Zeugnis;
import pdf.PdfZeugnis;
import businesslogik.dataprovider.StandardDataProvider;

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
		MainWindow window = new MainWindow(dbRef, this);

	}
	
	/**
	 * Druck alle Zeugnisse einer Klasse.
	 * @param azubis {@link Azubi}
	 * @param jahr int
	 */
	public void druckePdf(ArrayList<Azubi> azubis, int jahr) {
		for (Azubi azubi : azubis) {
			Zeugnis zeugnis = dbRef.gibZeugnisByAzubi(azubi, jahr);
			new PdfZeugnis(azubi, "", zeugnis, String.valueOf(jahr), Short.valueOf("4"));
		}
	}
	
	/**
	 * Druckt ein Zeugnis des Azubis.
	 * @param a {@link Azubi}
	 * @param jahr int
	 */
	public void druckePdf(Azubi a, int jahr) {
		Zeugnis zeugnis = dbRef.gibZeugnisByAzubi(a, jahr);
		new PdfZeugnis(a, "", zeugnis, String.valueOf(jahr), Short.valueOf("4"));
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
