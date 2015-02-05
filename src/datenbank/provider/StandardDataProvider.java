package datenbank.provider;

import java.util.ArrayList;

import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;

public abstract class StandardDataProvider {

	abstract ArrayList<Lehrer> gibAlleLehrer();
	abstract ArrayList<Klasse> gibAlleKlassen();
	abstract ArrayList<Betrieb> gibAlleBetriebe();
	abstract ArrayList<Ausbilder> gibAlleAusbilder();
	abstract ArrayList<Azubi> gibAzubiVon(Klasse k);
	abstract ArrayList<Fach> gibAlleFaecher();
	
	public StandardDataProvider getDataProvider(String db) {
		//TODO
			return null;
	}
}
