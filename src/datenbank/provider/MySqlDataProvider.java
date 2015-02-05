package datenbank.provider;

import java.util.ArrayList;

import datenbank.mySqlDAO.MySqlAusbilderDAO;
import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;

public class MySqlDataProvider extends StandardDataProvider{

	@Override
	ArrayList<Lehrer> gibAlleLehrer() {
		return null;
	}

	@Override
	ArrayList<Klasse> gibAlleKlassen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ArrayList<Betrieb> gibAlleBetriebe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ArrayList<Ausbilder> gibAlleAusbilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ArrayList<Azubi> gibAzubiVon(Klasse k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ArrayList<Fach> gibAlleFaecher() {
		// TODO Auto-generated method stub
		return null;
	}

}
