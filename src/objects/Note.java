package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Noten. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Note {
	
	private String noteID;
	private String Beschreibung;


	public String getBeschreibung() {
		return Beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		Beschreibung = beschreibung;
	}

	public String getNoteID() {
		return noteID;
	}

	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}

}
