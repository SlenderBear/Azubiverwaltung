package datenbank;

import java.util.ArrayList;

public interface StandardDAO<T> {
	/**
	 * Fügt ein beliebiges Objekt in die Datenbank ein
	 * @param Objekt das gespeichert werden soll
	 * @return Objekt des selben Eingabetypes mit gefüllter GUID
	 */
	T insert(T t);
	
	/**
	 * Ändert ein beliebiges Objekt in der Datenbank
	 * @param Objekt das geändert werden soll mit vorher gefüllter GUID
	 * @return boolean in Bezug auf den Erfolg des Update Befehls
	 */
	boolean update(T t);
	
	/**
	 * Löscht ein beliebiges Objekt in der Datenbank
	 * @param Objekt das gelöscht werden soll
	 * @return boolean in Bezug auf den Erfolge des Delete Befehls
	 */
	boolean delete(T t);
	
	/**
	 * Gibt alle Objekte eines bestimmten Types zurück
	 * @return Gibt eine ArrayList mit allen Objekten des Typs zurück
	 */
	ArrayList<T> getAll();
	
	/**
	 * 
	 * @param Übergabe einer GUID anhand derer das eindeutig zu identifiezierdene Objekt gesucht wird
	 * @return das gesuchte Objekt wird zurück gegeben
	 */
	T getByGuid(String guid);
	
	/**
	 * 
	 * @param Übergabe des Objektes, welches auf dessen Existenz geprüft werden soll
	 * @return Gibt einen Boolean in Bezug auf das Vorhanden sein des Objektes zurück
	 */
	boolean isVorhanden(T t);
	
	/**
	 * 
	 * @return Gibt den Klassennamen des Objektes wieder, welches in der DAO verwendet wird
	 */
	String getClassName();
}
