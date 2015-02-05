package testklassen;

import objects.Azubi;
import objects.Klasse;
import pdf.PdfZeugnisA4;

public class TestsAnna {
	
	public TestsAnna(){
		Klasse k = new Klasse();
		k.setBezeichnung("NANA");
		k.setJahr(2015);
		
		Azubi a = new Azubi();
		a.setName("Minion2");
		a.setVorname("Banana");
		a.setGeburtsdatum("11.11.1111");
		a.setLehrjahr(2);
		a.setKlasse(k);
		PdfZeugnisA4 zeug = new PdfZeugnisA4(a, "Test", "bla");
	}

}
