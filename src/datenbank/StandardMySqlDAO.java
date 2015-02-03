package datenbank;

import java.util.ArrayList;

public interface StandardMySqlDAO<T> {
	T insert(T t);
	boolean update(T t);
	boolean delete(T t);
	ArrayList<T> getAll();
}
