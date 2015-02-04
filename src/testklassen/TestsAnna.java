package testklassen;

import objects.Azubi;
import pdf.PdfZeugnisA4;

public class TestsAnna {
	
	public TestsAnna(){
		Azubi a = new Azubi();
		a.setName("Minion");
		a.setVorname("Banana");
		a.setGeburtsdatum("11.11.1111");
		PdfZeugnisA4 zeug = new PdfZeugnisA4(a, "Test", "bla");
	}

}
