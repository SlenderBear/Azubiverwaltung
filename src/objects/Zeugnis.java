package objects;

import java.util.ArrayList;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Zeugnissen. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Zeugnis implements StandardValueObject{

	private String ID;
	private Azubi azubi;
	private int jahr;
	private String zeugnisKonferenz;
	private ArrayList<Zeugnisposition> positionen;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Azubi getAzubi() {
		return azubi;
	}

	public void setAzubi(Azubi azubi) {
		this.azubi = azubi;
	}


	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public String getZeugnisKonferenz() {
		return zeugnisKonferenz;
	}

	public void setZeugnisKonferenz(String zeugnisKonferenz) {
		this.zeugnisKonferenz = zeugnisKonferenz;
	}

	public ArrayList<Zeugnisposition> getPositionen() {
		return positionen;
	}

	public void setPositionen(ArrayList<Zeugnisposition> positionen) {
		this.positionen = positionen;
	}

}
