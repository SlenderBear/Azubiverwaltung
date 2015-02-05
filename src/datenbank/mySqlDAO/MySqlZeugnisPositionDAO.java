package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Zeugnisposition;
import datenbank.MySQLConnector;
import datenbank.StandardDAO;

public class MySqlZeugnisPositionDAO implements StandardDAO<Zeugnisposition> {
	MySqlZeugnisDAO zeugnisDao = new MySqlZeugnisDAO();
	MySqlNoteDAO noteDao = new MySqlNoteDAO();
	MySqlFachDAO fachDao = new MySqlFachDAO();

	@Override
	public Zeugnisposition insert(Zeugnisposition t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO zeugnisposition values('" + guid + "','"
				+ t.getZeugnis().getID() + "','" + t.getNote().getNoteID()
				+ "','" + t.getFach().getID() + "');";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Zeugnisposition t) {
		String sql = "UPDATE zeugnisposition" + "SET zeugnisid='"
				+ t.getZeugnis().getID() + "',noteid='" + t.getNote().getNoteID()
				+ "',fachid='" + t.getFach().getID()
				+ "' WHERE zeugnispositionid='" + t.getID() + "';";
		return MySQLConnector.getInstance().statementExecute(sql);

	}

	@Override
	public boolean delete(Zeugnisposition t) {
		String sql = "delete from zeugnisposition"
				+ " WHERE zeugnispositionid='" + t.getID() + "';";
		return MySQLConnector.getInstance().statementExecute(sql);

	}

	@Override
	public ArrayList<Zeugnisposition> getAll() {
		String sql = "select * from zeugnisposition;";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Zeugnisposition> zeugnisPositionsListe = new ArrayList<Zeugnisposition>();
		try {
			while (rs.next()) {
				Zeugnisposition z = new Zeugnisposition();
				z.setID(rs.getString("zeugnispositionid"));
				z.setZeugnis(zeugnisDao.getByGuid(rs.getString("zeugnisID")));
				z.setNote(noteDao.getByGuid(rs.getString("noteid")));
				z.setFach(fachDao.getByGuid(rs.getString("fachID")));

				zeugnisPositionsListe.add(z);
			}
		} catch (Exception e) {
			System.out.println("Fehler in MySQLZeugnispositionDAO");
		}
		return zeugnisPositionsListe;
	}

	@Override
	public Zeugnisposition getByGuid(String guid) {
		String sql = "select * from zeugnisposition where zeugnispositionid='"
				+ guid + "';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Zeugnisposition z = new Zeugnisposition();
		try {
			rs.next();
			z.setID(rs.getString("zeugnispositionid"));
			z.setZeugnis(zeugnisDao.getByGuid(rs.getString("zeugnisID")));
			z.setNote(noteDao.getByGuid(rs.getString("noteid")));
			z.setFach(fachDao.getByGuid(rs.getString("fachID")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return z;
	}

}
