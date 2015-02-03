package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Zeugnissen. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Zeugnis {

	private String ID;
	private Azubi azubi;
	private Fach fach;
	private int zahl;

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

	public Fach getFach() {
		return fach;
	}

	public void setFach(Fach fach) {
		this.fach = fach;
	}

	public int getZahl() {
		return zahl;
	}

	public void setZahl(int zahl) {
		this.zahl = zahl;
	}

}