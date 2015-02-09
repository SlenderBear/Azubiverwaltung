package datenbank.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Note;
import objects.Zeugnisposition;
import datenbank.connector.SqliteConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlNoteDAO enth�lt s�mtliche Funktionen zur Datenbankanbindung des Notenobjektes
 *	Die Methoden insert update delete werden nicht ben�tigt, da Noten feststehen und mit der 
 *	Generierung der DB mitgeneriert werden
 */
public class SqliteNoteDAO implements StandardDAO<Note>{
private static final String DAO_NAME= Note.class.getName();
	
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}
	

	@Override
	public Note insert(Note t) {
		return t;
	}

	@Override
	public boolean update(Note t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Note t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Note> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note getByGuid(String noteid) {
		String sql = "select * from note where noteid='"+noteid+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		Note b = new Note();
		try {
			rs.next();
	        b.setNoteID(rs.getString("noteid"));
	        b.setBeschreibung((rs.getString("beschreibung")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	

	@Override
	public boolean isVorhanden(Note t) {
		return true;
	}
}
