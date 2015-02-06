package testklassen;

import objects.Azubi;
import objects.Fach;
import objects.Klasse;
import objects.Lehrer;
import objects.Note;
import objects.Zeugnis;
import objects.Zeugnisposition;
import pdf.PdfZeugnisA4;

public class TestsAnna {
	
	public TestsAnna(){
		
		Note n1 = new Note();
		n1.setBeschreibung("sehr gut");
		
		Note n2 = new Note();
		n2.setBeschreibung("gut");
		
		Fach f1 = new Fach();
		f1.setBezeichnung("Deutsch");
		Fach f2 = new Fach();
		f2.setBezeichnung("Politk");
		Fach f3 = new Fach();
		f3.setBezeichnung("Info");
		Fach f4 = new Fach();
		f4.setBezeichnung("Anwendung");
		Fach f5 = new Fach();
		f5.setBezeichnung("Wirtschaft");
		Fach f6 = new Fach();
		f6.setBezeichnung("Englisch");
		Fach f7 = new Fach();
		f7.setBezeichnung("Reli");
		Fach f8 = new Fach();
		f8.setBezeichnung("Sport");
	
	
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
		
		Zeugnis z = new Zeugnis();
		z.setZeugnisKonferenz("01.01.2015");
		z.setAzubi(a);
		
		Zeugnisposition z1 = new Zeugnisposition();
		z1.setFach(f1);
		z1.setNote(n1);
		
		Zeugnisposition z2 = new Zeugnisposition();
		z1.setFach(f2);
		z1.setNote(n2);
		
		Zeugnisposition z3 = new Zeugnisposition();
		z1.setFach(f3);
		z1.setNote(n1);
		
		Zeugnisposition z4 = new Zeugnisposition();
		z1.setFach(f4);
		z1.setNote(n1);
		
		Zeugnisposition z5 = new Zeugnisposition();
		z1.setFach(f5);
		z1.setNote(n1);
		
		Zeugnisposition z6 = new Zeugnisposition();
		z1.setFach(f6);
		z1.setNote(n2);
		
		Zeugnisposition z7 = new Zeugnisposition();
		z1.setFach(f7);
		z1.setNote(n2);
		
		Zeugnisposition z8 = new Zeugnisposition();
		z1.setFach(f8);
		z1.setNote(n1);
		
		
		PdfZeugnisA4 zeug = new PdfZeugnisA4(a, "Test", z, "07.02.2015", (short)3);

	}

}
