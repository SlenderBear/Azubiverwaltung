package datenbank.dao.mySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Berechtigung;
import datenbank.connector.MySQLConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
  *	Die Klasse MySqlBerechtigungDAO enthält sämtliche Funktionen zur Datenbankanbindung des Berechtigungsobjektes
 */
public class MySqlBerechtigungDAO implements StandardDAO<Berechtigung>{
	
	private static final String DAO_NAME= Berechtigung.class.getName();
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}

	@Override
	public Berechtigung insert(Berechtigung t) {
		return t;
	}

	@Override
	public boolean update(Berechtigung t) {

		return false;
	}

	@Override
	public boolean delete(Berechtigung t) {
		return false;
	}

	@Override
	public ArrayList<Berechtigung> getAll() {
		String sql = "select * from berechtigung;";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Berechtigung> berechtigungListe = new ArrayList<Berechtigung>();
		try{
		 while (rs.next())
	      {
			 Berechtigung b = new Berechtigung();
	        b.setID(rs.getInt("berechtigungid"));
	        b.setBezeichnung(rs.getString("bezeichnung"));
	       
	        berechtigungListe.add(b);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLBerechtigungDAO");
		}
		return berechtigungListe;
	}

	@Override
	public Berechtigung getByGuid(String bezeichnung) {
		String sql = "select * from berechtigung where bezeichnung='"+bezeichnung+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Berechtigung b = new Berechtigung();
		try {
			rs.next();
	        b.setID(rs.getInt("berechtigungid"));
	        b.setBezeichnung(rs.getString("bezeichnung"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean isVorhanden(Berechtigung t) {
			return true;
	}

}
