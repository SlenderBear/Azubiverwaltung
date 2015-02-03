package datenbank;

import java.sql.ResultSet;
import java.util.ArrayList;

import objects.Betrieb;

public class MySqlBetriebDAO implements StandardMySqlDAO<Betrieb> {
	private MySQLConnector connector = new MySQLConnector();

	@Override
	public Betrieb insert(Betrieb b) {
		String guid = connector.getNewGUID();
		String sql = "INSERT INTO betrieb values(" 
				+ guid 
				+ ",'" + b.getFirmenbezeichnung() 
				+ ",'" + b.getStrasse()
				+ ",'" + b.getPlz() 
				+ ",'" + b.getOrt() 
				+ ")";
		connector.statementExecute(sql);
		b.setID(guid);
		return b;
	}

	@Override
	public boolean update(Betrieb t) {
		String sql = "UPDATE betrieb"+
				"SET firmenbezeichnung="+t.getFirmenbezeichnung()+",strasse="+t.getStrasse()+",plz="+t.getPlz()+",ort="+t.getOrt()+
				" WHERE betriebid="+t.getID()+";";
		return connector.statementExecute(sql);
	}

	@Override
	public boolean delete(Betrieb t) {
		String sql = "delete from betrieb"+
				" WHERE betriebid="+t.getID()+";";
		return connector.statementExecute(sql);
	}

	/* 
	 * @see datenbank.StandardMySqlDAO#getAll()
	 */
	@Override
	public ArrayList<Betrieb> getAll() {
		String sql = "select * from betrieb";
		ResultSet rs = connector.executeQuery(sql);
		ArrayList<Betrieb> betriebsListe = new ArrayList<Betrieb>();
		try{
		 while (rs.next())
	      {
	        Betrieb b = new Betrieb();
	        b.setID(rs.getString("betriebid"));
	        b.setFirmenbezeichnung(rs.getString("firmenbezeichnung"));
	        b.setStrasse(rs.getString("strasse"));
	        b.setPlz(rs.getString("plz"));
	        b.setOrt(rs.getString("ort"));  
	        
	        betriebsListe.add(b);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLBetriebDAO");
		}
		return betriebsListe;
	}

}
