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
	public ArrayList<Lehrer> gibAlleLehrer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Klasse> gibAlleKlassen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Betrieb> gibAlleBetriebe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ausbilder> gibAlleAusbilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Azubi> gibAzubiVon(Klasse k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Fach> gibAlleFaecher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean gibtLogin(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtAzubi(Azubi azubi) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtLehrer(Lehrer lehrer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtAusbilder(Ausbilder ausbilder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtBetrieb(Betrieb betrieb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gibtKlasse(Klasse klasse) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
