package gui;
import businesslogik.Delete;
import businesslogik.Insert;
import businesslogik.LeseAus;
import businesslogik.Update;

/**
 * @author backs.kristin-anna, imeav.maksim
 * 
 *         Verbindung zwischen GUI und Businesslogik. Der Konstruktor dieser
 *         Klasse wird aus der Businesslogik heraus aufgerufen und startet die
 *         GUI.
 */

public class GUIConnector {

	private Delete delete;
	private Insert insert;
	private Update update;
	private LeseAus lese;

	public GUIConnector(Delete d, Insert i, Update u, LeseAus l) {
		this.delete = d;
		this.insert = i;
		this.update = u;
		this.lese = l;

		// TO DO Rufe GUI-Start an dieser Stelle auf
	}

}
