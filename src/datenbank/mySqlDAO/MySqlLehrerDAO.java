package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Lehrer;
import datenbank.MySQLConnector;
import datenbank.StandardMySqlDAO;

public class MySqlLehrerDAO implements StandardMySqlDAO<Lehrer>{
	private MySqlLoginDatenDAO dao = new MySqlLoginDatenDAO();

	@Override
	public Lehrer insert(Lehrer t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO lehrer values(" 
				+ guid 
				+ ",'" + t.getName()
				+ ",'" + t.getVorname()
				+ ",'" + t.getTelefon() 
				+ ",'" + t.getLogin().getID() 
				+ ")";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Lehrer t) {
		String sql = "UPDATE lehrer"+
				"SET name="+t.getName()+",vorname="+t.getVorname()+",telefonnummer="+t.getTelefon()+",loginid="+t.getLogin().getID()+
				" WHERE lehrerid="+t.getID()+";";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Lehrer t) {
		String sql = "delete from lehrer"+
				" WHERE lehrerid="+t.getID()+";";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public ArrayList<Lehrer> getAll() {
		String sql = "select * from lehrer";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Lehrer> lehrerListe = new ArrayList<Lehrer>();
		try{
		 while (rs.next())
	      {
	        Lehrer l = new Lehrer();
	        l.setID(rs.getString("lehrerid"));
	        l.setName(rs.getString("name"));
	        l.setVorname(rs.getString("vorname"));
	        l.setTelefon(rs.getString("telefonnummer"));
	        l.setLogin(dao.getByGuid(rs.getString("loginid")));  
	        
	        lehrerListe.add(l);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLLehrerDAO");
		}
		return lehrerListe;
	}

	@Override
	public Lehrer getByGuid(String guid) {
		String sql = "select * from lehrer where lehrerid="+guid+"";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Lehrer l = new Lehrer();
		try {
			rs.next();
	        l.setID(rs.getString("lehrerid"));
	        l.setName(rs.getString("name"));
	        l.setVorname(rs.getString("vorname"));
	        l.setTelefon(rs.getString("telefonnummer"));
	        l.setLogin(dao.getByGuid(rs.getString("loginid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

}
