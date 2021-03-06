package datenbank.dao.mySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Klasse;
import datenbank.connector.MySQLConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlKlasseDAO  enth�lt s�mtliche Funktionen zur Datenbankanbindung des Klasseobjektes
 */
public class MySqlKlasseDAO implements StandardDAO<Klasse>{
	
	private static final String DAO_NAME= Klasse.class.getName();
	
	private MySqlLehrerDAO dao = new MySqlLehrerDAO();
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}

	@Override
	public Klasse insert(Klasse t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO klasse values('" 
				+ guid 
				+ "','" + t.getBezeichnung()
				+ "'," + t.getJahr()
				+ ",'" + t.getLehrer().getID() 
				+ "');";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Klasse t) {
		String sql = "UPDATE klasse"+
				" SET "
				+ "bezeichnung='"+t.getBezeichnung()
				+"',jahr="+t.getJahr()
				+",lehrerid='"+t.getLehrer().getID()
				+"' WHERE klassenid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Klasse t) {
		String sql = "delete from klasse"+
				" WHERE klassenid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public ArrayList<Klasse> getAll() {
		String sql = "select * from klasse;";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Klasse> klassenListe = new ArrayList<Klasse>();
		try{
		 while (rs.next())
	      {
			 Klasse k = new Klasse();
	        k.setID(rs.getString("klassenid"));
	        k.setBezeichnung(rs.getString("bezeichnung"));
	        k.setJahr(rs.getInt("jahr"));
	        k.setLehrer(dao.getByGuid(rs.getString("lehrerid")));  
	        
	        klassenListe.add(k);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLKlasseDAO");
		}
		return klassenListe;
	}

	@Override
	public Klasse getByGuid(String guid) {
		String sql = "select * from klasse where klassenid='"+guid+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Klasse k = new Klasse();
		try {
			rs.next();
	        k.setID(rs.getString("klassenid"));
	        k.setBezeichnung(rs.getString("bezeichnung"));
	        k.setJahr(rs.getInt("jahr"));
	        k.setLehrer(dao.getByGuid(rs.getString("lehrerid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
	}

	@Override
	public boolean isVorhanden(Klasse t) {
		String sql = "select * from klasse where "
				+ "bezeichnung='"+t.getBezeichnung()
				+"',jahr="+t.getJahr()
				+",lehrerid='"+t.getLehrer().getID()
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
