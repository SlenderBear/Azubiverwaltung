package datenbank.dao.mySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Fach;
import datenbank.connector.MySQLConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlAusbilderDAO enthält sämtliche Funktionen zur Datenbankanbindung des Fachobjektes
 */
public class MySqlFachDAO implements StandardDAO<Fach>{
	
	private static final String DAO_NAME= Fach.class.getName();
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}

	@Override
	public Fach insert(Fach t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO fach values('" 
				+ guid 
				+ "','" + t.getBezeichnung() 
				+ "');";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Fach t) {
		String sql = "UPDATE fach"+
				"SET bezeichnung='"+t.getBezeichnung()+
				"' WHERE fachid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Fach t) {
		String sql = "delete from fach"+
				" WHERE fachid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public ArrayList<Fach> getAll() {
		String sql = "select * from fach;";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Fach> fachListe = new ArrayList<Fach>();
		try{
		 while (rs.next())
	      {
	        Fach f = new Fach();
	        f.setID(rs.getString("fachid"));
	        f.setBezeichnung(rs.getString("bezeichnung"));
	       
	        fachListe.add(f);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLFachDAO");
		}
		return fachListe;
	}

	@Override
	public Fach getByGuid(String guid) {
		String sql = "select * from fach where fachid='"+guid+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Fach f = new Fach();
		try {
			rs.next();
	        f.setID(rs.getString("fachid"));
	        f.setBezeichnung(rs.getString("bezeichnung"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
		
	}

	@Override
	public boolean isVorhanden(Fach t) {
		String sql = "select * from fach where "+
				"bezeichnung='"+t.getBezeichnung()
				+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
			try {
				return rs.first();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

}
