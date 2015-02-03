package businesslogik;

import objects.Ausbilder;
import objects.Azubi;
import objects.Berechtigung;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;
import objects.Login;
import objects.Zeugnis;

/**
 * @author backs.kristin-anna
 * 
 *         Verbindungsklasse zwischen Der GUI und dem DPS in Bezug auf das
 *         Schreiben geaenderter Daten in das DPS.
 * 
 *         DPS = Datenpersistierungssystem
 * 
 */ 

public class Update {
	Object dbRef;

	public Update(Object dbRef) {
		this.dbRef = dbRef;
	}

	public void update(Ausbilder ausbilder) {

	}

	/**
	 * @param azubi
	 */
	public void update(Azubi azubi) {

	}

	public void update(Berechtigung berechtigung) {

	}

	public void update(Betrieb betrieb) {

	}

	public void update(Fach fach) {

	}

	public void update(Lehrer lehrer) {

	}

	public void update(Klasse klasse) {

	}

	public void update(Login login) {

	}

	public void update(Zeugnis zeignis) {

	}
}
