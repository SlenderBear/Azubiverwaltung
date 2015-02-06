package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import businesslogik.Verwaltung;

/**
 * @author TEAM DUNKEL: Anna Backs - Philipp Dietz - Gregor Dunkel - Maksim
 *         Imaev - Justin Mertmann
 * 
 *         Main-Methode zum Starten der Anwendung zur Verwaltung von
 *         Azubi-Daten.
 * 
 */
public class Main {

	public static void main(String[] args) {
		try{
			
		@SuppressWarnings("unused")
		Verwaltung v = new Verwaltung();
			
		} catch(Exception e){
			JOptionPane.showMessageDialog(new JFrame(), "Es trat ein unerwarteter Fehler auf.\nBitte starten Sie die Anwednung neu. Sorry.");
			System.exit(0);
		}

	}

}
