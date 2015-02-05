package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datenbank.MySQLConnector;
import datenbank.StandardMySqlDAO;
import objects.Betrieb;

public class MySqlBetriebDAO implements StandardMySqlDAO<Betrieb> {

	@Override
	public Betrieb insert(Betrieb b) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO betrieb values(" + guid + ",'"
				+ b.getFirmenbezeichnung() + ",'" + b.getStrasse() + ",'"
				+ b.getPlz() + ",'" + b.getOrt() + ",'" + b.geteMail() + ",'"
				+ b.getTelefon() + ",'" + b.getFax() + ")";
		MySQLConnector.getInstance().statementExecute(sql);
		b.setID(guid);
		return b;
	}

	@Override
	public boolean update(Betrieb t) {
		String sql = "UPDATE betrieb" + "SET " + "firmenbezeichnung="
				+ t.getFirmenbezeichnung() + ",strasse=" + t.getStrasse()
				+ ",plz=" + t.getPlz() + ",ort=" + t.getOrt() + ",email="
				+ t.geteMail() + ",telefonnummer=" + t.getTelefon()
				+ ",Faxnummer=" + t.getFax() + " WHERE betriebid=" + t.getID()
				+ ";";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Betrieb t) {
		String sql = "delete from betrieb" + " WHERE betriebid=" + t.getID()
				+ ";";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public ArrayList<Betrieb> getAll() {
		String sql = "select * from betrieb";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
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
		String sql = "select * from betrieb where betriebid=" + guid + "";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
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

}
