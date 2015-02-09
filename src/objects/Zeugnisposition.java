package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Zeugnis-Positionen. Diese Klasse enthaelt
 *         alle noetigen Attribute sowie getter- und setter-Methoden um auf
 *         diese zuzugreifen.
 */

public class Zeugnisposition implements StandardValueObject{

	private String ID;
	private Note note;
	private Fach fach;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Fach getFach() {
		return fach;
	}

	public void setFach(Fach fach) {
		this.fach = fach;
	}

}
