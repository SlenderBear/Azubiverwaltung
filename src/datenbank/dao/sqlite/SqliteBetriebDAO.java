package datenbank.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Betrieb;
import datenbank.connector.SqliteConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlBetriebDAO enthält sämtliche Funktionen zur Datenbankanbindung des Betriebsobjektes
 */
public class SqliteBetriebDAO implements StandardDAO<Betrieb>{

private static final String DAO_NAME= Betrieb.class.getName();
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}

	@Override
	public Betrieb insert(Betrieb b) {
		String guid = SqliteConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO betrieb values('" 
				+ guid + "','"
				+ b.getFirmenbezeichnung() + "','" 
				+ b.getStrasse() + "','"
				+ b.getPlz() + "','"
				+ b.getOrt() + "','" 
				+ b.geteMail() + "','"
				+ b.getTelefon() + "','"
				+ b.getFax() + "');";
		SqliteConnector.getInstance().statementExecute(sql);
		b.setID(guid);
		return b;
	}

	@Override
	public boolean update(Betrieb t) {
		String sql = "UPDATE betrieb SET " 
				+ "firmenbezeichnung='"+ t.getFirmenbezeichnung()
				+ "',strasse='" + t.getStrasse()
				+ "',plz='" + t.getPlz()
				+ "',ort='" + t.getOrt()
				+ "',email='"	+ t.geteMail()
				+ "',telefonnummer='" + t.getTelefon()
				+ "',Faxnummer='" + t.getFax()
				+ "' WHERE betriebid='" + t.getID()
				+ "';";
		return SqliteConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Betrieb t) {
		String sql = "delete from betrieb" + " WHERE betriebid='" + t.getID()
				+ "';";
		return SqliteConnector.getInstance().statementExecute(sql);
	}

	@Override
	public ArrayList<Betrieb> getAll() {
		String sql = "select * from betrieb'";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		ArrayList<Betrieb> betriebsListe = new ArrayList<Betrieb>();
		try {
			while (rs.next()) {
				Betrieb b = new Betrieb();
				b.setID(rs.getString("betriebid"));
				b.setFirmenbezeichnung(rs.getString("firmenbezeichnung"));
				b.setStrasse(rs.getString("strasse"));
				b.setPlz(rs.getString("plz"));
				b.setOrt(rs.getString("ort"));
				b.setOrt(rs.getString("email"));
				b.setOrt(rs.getString("telefonnummer"));
				b.setOrt(rs.getString("faxnummer"));

				betriebsListe.add(b);
			}
		} catch (Exception e) {
			System.out.println("Fehler in MySQLBetriebDAO");
		}
		return betriebsListe;
	}

	public Betrieb getByGuid(String guid) {
		String sql = "select * from betrieb where betriebid='" + guid + "';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		Betrieb b = new Betrieb();
		try {
			rs.next();
			b.setID(rs.getString("betriebid"));
			b.setFirmenbezeichnung(rs.getString("firmenbezeichnung"));
			b.setStrasse(rs.getString("strasse"));
			b.setPlz(rs.getString("plz"));
			b.setOrt(rs.getString("ort"));
			b.setOrt(rs.getString("email"));
			b.setOrt(rs.getString("telefonnummer"));
			b.setOrt(rs.getString("faxnummer"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;

	}

	@Override
	public boolean isVorhanden(Betrieb t) {
		String sql = "select * from betrieb where "
				+ "firmenbezeichnung='"+ t.getFirmenbezeichnung()
				+ "',strasse='" + t.getStrasse()
				+ "',plz='" + t.getPlz()
				+ "',ort='" + t.getOrt()
				+ "',email='"	+ t.geteMail()
				+ "',telefonnummer='" + t.getTelefon()
				+ "',Faxnummer='" + t.getFax()
				+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
			try {
				return rs.first();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

}
