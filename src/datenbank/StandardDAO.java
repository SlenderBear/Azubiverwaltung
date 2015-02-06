package datenbank;

import java.util.ArrayList;

public interface StandardDAO<T> {
	/**
	 * F�gt ein beliebiges Objekt in die Datenbank ein
	 * @param Objekt das gespeichert werden soll
	 * @return Objekt des selben Eingabetypes mit gef�llter GUID
	 */
	T insert(T t);
	
	/**
	 * �ndert ein beliebiges Objekt in der Datenbank
	 * @param Objekt das ge�ndert werden soll mit vorher gef�llter GUID
	 * @return boolean in Bezug auf den Erfolg des Update Befehls
	 */
	boolean update(T t);
	
	/**
	 * L�scht ein beliebiges Objekt in der Datenbank
	 * @param Objekt das gel�scht werden soll
	 * @return boolean in Bezug auf den Erfolge des Delete Befehls
	 */
	boolean delete(T t);
	
	/**
	 * Gibt alle Objekte eines bestimmten Types zur�ck
	 * @return Gibt eine ArrayList mit allen Objekten des Typs zur�ck
	 */
	ArrayList<T> getAll();
	
	/**
	 * 
	 * @param �bergabe einer GUID anhand derer das eindeutig zu identifiezierdene Objekt gesucht wird
	 * @return das gesuchte Objekt wird zur�ck gegeben
	 */
	T getByGuid(String guid);
	
	/**
	 * 
	 * @param �bergabe des Objektes, welches auf dessen Existenz gepr�ft werden soll
	 * @return Gibt einen Boolean in Bezug auf das Vorhanden sein des Objektes zur�ck
	 */
	boolean isVorhanden(T t);
	
	/**
	 * 
	 * @return Gibt den Klassennamen des Objektes wieder, welches in der DAO verwendet wird
	 */
	String getClassName();
}
