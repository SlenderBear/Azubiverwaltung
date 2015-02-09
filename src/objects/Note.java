package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Noten. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Note implements StandardValueObject{
	
	private String noteID;
	private String beschreibung;


	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getNoteID() {
		return noteID;
	}

	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}
	
	@Override
	public String toString() {
		return this.beschreibung;
	}

}
