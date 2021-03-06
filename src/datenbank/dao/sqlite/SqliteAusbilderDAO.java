package datenbank.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Ausbilder;
import datenbank.connector.SqliteConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqliteAusbilderDAO enth�lt s�mtliche Funktionen zur Datenbankanbindung des Ausbilderobjektes
 */
public class SqliteAusbilderDAO implements StandardDAO<Ausbilder>{

	private static final String DAO_NAME= Ausbilder.class.getName();
	
	private SqliteBetriebDAO dao = new SqliteBetriebDAO();
	
	@Override
	public Ausbilder insert(Ausbilder b) {
		String guid = SqliteConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO ausbilder values('" 
				+ guid 
				+ "','" + b.getName()
				+ "','" + b.getVorname()
				+ "','" + b.getTelefon() 
				+ "','" + b.getEmail()
				+ "','" + b.getBetrieb().getID()
				+ "');";
		SqliteConnector.getInstance().statementExecute(sql);
		b.setID(guid);
		return b;
	}

	@Override
	public boolean update(Ausbilder t) {
		String sql = "UPDATE ausbilder"+
				" SET name='"+t.getName()
				+"',vorname='"+t.getVorname()
				+"',telefonnummer='"+t.getTelefon()
				+"',email='"+t.getEmail()
				+"',betriebid='"+t.getBetrieb().getID()
				+"' WHERE ausbilderid='"+t.getID()+"';";
		return SqliteConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Ausbilder t) {
		String sql = "delete from ausbilder"+
				" WHERE ausbilderid='"+t.getID()+"';";
		return SqliteConnector.getInstance().statementExecute(sql);
	}


	@Override
	public ArrayList<Ausbilder> getAll() {
		String sql = "select * from ausbilder;";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		ArrayList<Ausbilder> ausbilderListe = new ArrayList<Ausbilder>();
		try{
		 while (rs.next())
	      {
			 Ausbilder b = new Ausbilder();
	        b.setID(rs.getString("ausbilderid"));
	        b.setName(rs.getString("name"));
	        b.setVorname(rs.getString("vorname"));
	        b.setTelefon(rs.getString("telefonnummer"));
	        b.setEmail(rs.getString("email"));  
	        b.setBetrieb(dao.getByGuid(rs.getString("betriebid")));  
	        
	        ausbilderListe.add(b);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLBetriebDAO");
		}
		return ausbilderListe;
	}

	@Override
	public Ausbilder getByGuid(String guid) {
		String sql = "select * from ausbilder where ausbilderid='"+guid+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		Ausbilder a = new Ausbilder();
		try {
			rs.next();
	        a.setID(rs.getString("ausbilderid"));
	        a.setName(rs.getString("name"));
	        a.setVorname(rs.getString("vorname"));
	        a.setTelefon(rs.getString("telefonnummer"));
	        a.setEmail(rs.getString("email")); 
	        a.setBetrieb(dao.getByGuid(rs.getString("betriebid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
		
	}

	@Override
	public boolean isVorhanden(Ausbilder t) {
		String sql = "select * from ausbilder where name='"+t.getName()
				+"',vorname='"+t.getVorname()
				+"',telefonnummer='"+t.getTelefon()
				+"',email='"+t.getEmail()
				+"',betriebid='"+t.getBetrieb().getID()
				+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
			try {
				return rs.first();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
			
	}

	@Override
	public String getClassName() {
		return DAO_NAME;
	}
}
