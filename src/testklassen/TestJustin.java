package testklassen;

import java.util.ArrayList;

import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;
import objects.Login;
import objects.Zeugnis;
import objects.Zeugnisposition;
import businesslogik.dataprovider.StandardDataProvider;

public class TestJustin {

	public static void main(String[] args) {
		StandardDataProvider.changeDataProvider(StandardDataProvider.db_optionen.MYSQL.toString());
		StandardDataProvider provider = StandardDataProvider.getInstance();
				System.out.println("--------------Ausbilderliste------------------");
		ArrayList<Ausbilder> ausbilderliste =provider.gibAlleAusbilder();
		for (Ausbilder ausbilder : ausbilderliste) {
			System.out.println(ausbilder.getID());
		}
		System.out.println("--------------Betriebsliste------------------");
		ArrayList<Betrieb> abetriebliste =provider.gibAlleBetriebe();
		for (Betrieb betrieb : abetriebliste) {
			System.out.println(betrieb.getID());
		}
		System.out.println("--------------Fachliste------------------");
		ArrayList<Fach> fl =provider.gibAlleFaecher();
		for (Fach f : fl) {
			System.out.println(f.getBezeichnung());
		}
		System.out.println("--------------Lehrerliste------------------");
		ArrayList<Lehrer> ll =provider.gibAlleLehrer();
		for (Lehrer l : ll) {
			System.out.println(l.getName());
		}
		ArrayList<Klasse> kl =provider.gibAlleKlassen();
		kl.get(0).setLehrer(ll.get(0));
		System.out.println("--------------Klassenliste------------------");
		for (Klasse  k : kl) {
			System.out.println(k.getBezeichnung());
		}
		System.out.println("--------------Azubiliste------------------");
		ArrayList<Azubi> al =provider.gibAzubiVon(kl.get(0));
		for (Azubi  a : al) {
			System.out.println(a.getName());
		}
		System.out.println("--------------Zeugnisliste-----------------");
		ArrayList<Zeugnis> zl = provider.gibAlleZeugnisse();
		for (Zeugnis  z : zl) {
			System.out.println(z.getAzubi());
			System.out.println("------------- Positionen ----------------");
			
			for (Zeugnisposition zeugnis : provider.gibPositionenZuZeugnis(z)) {
				System.out.println(zeugnis.getNote().getBeschreibung());
			}
		}
		


	}

}
