package datenbank;

import java.util.ArrayList;

public interface StandardDAO<T> {
	T insert(T t);
	boolean update(T t);
	boolean delete(T t);
	ArrayList<T> getAll();
	T getByGuid(String guid);
	boolean isVorhanden(T t);
}
