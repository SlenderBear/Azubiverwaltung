package testklassen;

import objects.Azubi;
import objects.Klasse;
import objects.Lehrer;
import pdf.PdfZeugnisA4;

public class TestsAnna {
	
	public TestsAnna(){
		
		Lehrer l = new Lehrer();
		l.setName("BATMAN");
		
		Klasse k = new Klasse();
		k.setBezeichnung("NANA");
		k.setJahr(2015);
		k.setLehrer(l);
		
		Azubi a = new Azubi();
		a.setName("Minion4");
		a.setVorname("Banana");
		a.setGeburtsdatum("11.11.1111");
		a.setLehrjahr(2);
		a.setKlasse(k);
		a.setFachrichtung('s');
		PdfZeugnisA4 zeug = new PdfZeugnisA4(a, "Test", "22.01.2015", "07.02.2015");

	}

}
