package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Zeugnis;
import objects.Zeugnisposition;
import datenbank.StandardDAO;
import datenbank.connector.MySQLConnector;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlAusbilderDAO enthält sämtliche Funktionen zur Datenbankanbindung des ZeugnisPositionsobjektes
 */
public class MySqlZeugnisPositionDAO implements StandardDAO<Zeugnisposition> {
	
	private static final String DAO_NAME= Zeugnisposition.class.getName();
	
	MySqlZeugnisDAO zeugnisDao = new MySqlZeugnisDAO();
	MySqlNoteDAO noteDao = new MySqlNoteDAO();
	MySqlFachDAO fachDao = new MySqlFachDAO();

	@Override
	public String getClassName() {
		return DAO_NAME;
	}
	
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
		String sql = "UPDATE zeugnisposition" + "SET "
				+ "zeugnisid='"	+ t.getZeugnis().getID()
				+ "',noteid='" + t.getNote().getNoteID()
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

	@Override
	public boolean isVorhanden(Zeugnisposition t) {
		String sql = "select * from zeugnisposition where "
				+ "zeugnisid='"	+ t.getZeugnis().getID()
				+ "',noteid='" + t.getNote().getNoteID()
				+ "',fachid='" + t.getFach().getID()
				+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
			try {
				return rs.first();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public ArrayList<Zeugnisposition> gibPositionenZuZeugnis(Zeugnis z){
		String sql = "select * from zeugnisposition where zeugnisid='"
				+ z.getID() + "';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Zeugnisposition> positionenListe = new ArrayList<Zeugnisposition>(); 
		try {
			while (rs.next()) {
				Zeugnisposition zp = new Zeugnisposition();
				zp.setID(rs.getString("zeugnispositionid"));
				zp.setZeugnis(zeugnisDao.getByGuid(rs.getString("zeugnisID")));
				zp.setNote(noteDao.getByGuid(rs.getString("noteid")));
				zp.setFach(fachDao.getByGuid(rs.getString("fachID")));

				positionenListe.add(zp);
			}
		} catch (Exception e) {
			System.out.println("Fehler in MySQLZeugnispositionDAO");
		}
		return positionenListe;
	}

}
