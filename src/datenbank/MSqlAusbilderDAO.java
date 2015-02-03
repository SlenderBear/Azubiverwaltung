package datenbank;

import java.sql.ResultSet;
import java.util.ArrayList;

import objects.Ausbilder;

public class MSqlAusbilderDAO implements StandardMySqlDAO<Ausbilder> {

	private MySQLConnector connector = new MySQLConnector();

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
		MySqlBetriebDAO dao = new MySqlBetriebDAO();
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
	        b.setBetrieb(dao.getBetriebByGUID(rs.getString("betriebid")));  
	        
	        ausbilderListe.add(b);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLBetriebDAO");
		}
		return ausbilderListe;
	}
	
}
