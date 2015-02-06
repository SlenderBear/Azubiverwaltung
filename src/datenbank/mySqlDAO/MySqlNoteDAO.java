package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Note;
import datenbank.MySQLConnector;
import datenbank.StandardDAO;

public class MySqlNoteDAO implements StandardDAO<Note>{
	
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
	public Note getByGuid(String beschreibung) {
		String sql = "select * from note where beschreibung='"+beschreibung+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
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
