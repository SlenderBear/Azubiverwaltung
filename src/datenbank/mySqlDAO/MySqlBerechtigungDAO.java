package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Berechtigung;
import datenbank.MySQLConnector;
import datenbank.StandardMySqlDAO;

public class MySqlBerechtigungDAO implements StandardMySqlDAO<Berechtigung>{
	private MySQLConnector connector = new MySQLConnector();

	@Override
	public Berechtigung insert(Berechtigung t) {
		String guid = connector.getNewGUID();
		String sql = "INSERT INTO berechtigung values(" 
				+ guid 
				+ ",'" + t.getBezeichnung() 
				+ ")";
		connector.statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Berechtigung t) {
		String sql = "UPDATE berechtigung"+
				"SET bezeichnung="+t.getBezeichnung()+
				" WHERE berechtigungid="+t.getID()+";";
		return connector.statementExecute(sql);
	}

	@Override
	public boolean delete(Berechtigung t) {
		String sql = "delete from berechtigung"+
				" WHERE berechtigungid="+t.getID()+";";
		return connector.statementExecute(sql);
	}

	@Override
	public ArrayList<Berechtigung> getAll() {
		String sql = "select * from berechtigung";
		ResultSet rs = connector.executeQuery(sql);
		ArrayList<Berechtigung> berechtigungListe = new ArrayList<Berechtigung>();
		try{
		 while (rs.next())
	      {
			 Berechtigung b = new Berechtigung();
	        b.setID(rs.getString("berechtigungid"));
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
		String sql = "select * from berechtigung where bezeichnung="+bezeichnung+"";
		ResultSet rs = connector.executeQuery(sql);
		Berechtigung b = new Berechtigung();
		try {
			rs.next();
	        b.setID(rs.getString("berechtigungid"));
	        b.setBezeichnung(rs.getString("bezeichnung"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

}
