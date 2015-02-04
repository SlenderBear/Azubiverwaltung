package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datenbank.MySQLConnector;
import datenbank.StandardMySqlDAO;
import objects.Ausbilder;
import objects.Betrieb;

public class MSqlAusbilderDAO implements StandardMySqlDAO<Ausbilder> {

	private MySQLConnector connector = new MySQLConnector();
	private MySqlBetriebDAO dao = new MySqlBetriebDAO();

	@Override
	public Ausbilder insert(Ausbilder b) {
		String guid = connector.getNewGUID();
		String sql = "INSERT INTO ausbilder values(" 
				+ guid 
				+ ",'" + b.getName()
				+ ",'" + b.getVorname()
				+ ",'" + b.getTelefon() 
				+ ",'" + b.getEmail()
				+ ",'" + b.getBetrieb().getID()
				+ ")";
		connector.statementExecute(sql);
		b.setID(guid);
		return b;
	}

	@Override
	public boolean update(Ausbilder t) {
		String sql = "UPDATE ausbilder"+
				"SET name="+t.getName()
				+",vorname="+t.getVorname()
				+",telefonnummer="+t.getTelefon()
				+",email="+t.getEmail()
				+",betriebid="+t.getBetrieb().getID()
				+" WHERE ausbilderid="+t.getID()+";";
		return connector.statementExecute(sql);
	}

	@Override
	public boolean delete(Ausbilder t) {
		String sql = "delete from ausbilder"+
				" WHERE ausbilderid="+t.getID()+";";
		return connector.statementExecute(sql);
	}


	@Override
	public ArrayList<Ausbilder> getAll() {
		String sql = "select * from ausbilder";
		ResultSet rs = connector.executeQuery(sql);
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
		String sql = "select * from ausbilder where ausbilderid="+guid+"";
		ResultSet rs = connector.executeQuery(sql);
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
	
}
