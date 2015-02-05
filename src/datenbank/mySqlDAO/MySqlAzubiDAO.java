package datenbank.mySqlDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Ausbilder;
import objects.Azubi;
import datenbank.MySQLConnector;
import datenbank.StandardMySqlDAO;

public class MySqlAzubiDAO implements StandardMySqlDAO<Azubi>{
	private MySqlKlasseDAO daoKlasse= new MySqlKlasseDAO();
	private MySqlBetriebDAO daoBetrieb = new MySqlBetriebDAO();
	private MySqlAusbilderDAO daoAusbilder = new MySqlAusbilderDAO();

	@Override
	public Azubi insert(Azubi t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO azubi values(" 
				+ guid 
				+ ",'" + t.getName()
				+ ",'" + t.getVorname()
				+ ",'" + t.getTelefon() 
				+ ",'" + t.getEmail()
				+ ",'" + t.getGeburtsdatum()
				+ ",'" + t.getStrasse()
				+ ",'" + t.getPlz()
				+ ",'" + t.getOrt()
				+ ",'" + t.getGeschlecht()
				+ ",'" + t.getLehrjahr()
				+ ",'" + t.getKlasse().getID()
				+ ",'" + t.getBetrieb().getID()
				+ ",'" + t.getAusbilder().getID()
				+ ")";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Azubi t) {
		String sql = "UPDATE azubi"+
				"SET name="+t.getName()
				+",vorname="+t.getVorname()
				+",telefonnummer="+t.getTelefon()
				+",email="+t.getEmail()
				+",geburtsdatum="+t.getEmail()
				+",strasse="+t.getEmail()
				+",plz="+t.getEmail()
				+",ort="+t.getEmail()
				+",ort="+t.getEmail()
				+",ort="+t.getEmail()
				+",ort="+t.getEmail()
				+",betriebid="+t.getBetrieb().getID()
				+" WHERE ausbilderid="+t.getID()+";";
		return MySQLConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public boolean delete(Azubi t) {
		String sql = "delete from ausbilder"+
				" WHERE ausbilderid="+t.getID()+";";
		return MySQLConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public ArrayList<Azubi> getAll() {
//		String sql = "select * from ausbilder";
//		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Azubi> ausbilderListe = new ArrayList<Azubi>();
//		try{
//		 while (rs.next())
//	      {
//			 Ausbilder b = new Ausbilder();
//	        b.setID(rs.getString("ausbilderid"));
//	        b.setName(rs.getString("name"));
//	        b.setVorname(rs.getString("vorname"));
//	        b.setTelefon(rs.getString("telefonnummer"));
//	        b.setEmail(rs.getString("email"));  
//	        b.setBetrieb(dao.getByGuid(rs.getString("betriebid")));  
//	        
//	        ausbilderListe.add(b);
//	      }
//		}catch(Exception e){
//			System.out.println("Fehler in MySQLBetriebDAO");
//		}
		return ausbilderListe;
	}

	@Override
	public Azubi getByGuid(String guid) {
		String sql = "select * from ausbilder where ausbilderid="+guid+"";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Azubi a = new Azubi();
		try {
			rs.next();
	        a.setID(rs.getString("ausbilderid"));
	        a.setName(rs.getString("name"));
	        a.setVorname(rs.getString("vorname"));
	        a.setTelefon(rs.getString("telefonnummer"));
	        a.setEmail(rs.getString("email")); 
//	        a.setBetrieb(dao.getByGuid(rs.getString("betriebid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

}
