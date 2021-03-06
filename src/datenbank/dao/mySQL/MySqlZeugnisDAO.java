package datenbank.dao.mySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Azubi;
import objects.Zeugnis;
import datenbank.connector.MySQLConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlZeugnisDAO enth�lt s�mtliche Funktionen zur Datenbankanbindung des Zeugnisobjektes
 */
public class MySqlZeugnisDAO implements StandardDAO<Zeugnis>{
	
	private static final String DAO_NAME= Zeugnis.class.getName();
	
	
	
	MySqlAzubiDAO azubiDao = new MySqlAzubiDAO();
	MySqlZeugnisPositionDAO zeugnispositionDao = new MySqlZeugnisPositionDAO();
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}
	
	@Override
	public Zeugnis insert(Zeugnis t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO zeugnis values('" 
				+ guid 
				+ "'," + t.getJahr()
				+ ",'" + t.getZeugnisKonferenz()
				+ "','" + t.getAzubi().getID() 
				+ "');";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Zeugnis t) {
		String sql = "UPDATE zeugnis"+
				" SET "
				+ "jahr="+t.getJahr()
				+",zeugniskonferenz='"+t.getZeugnisKonferenz()
				+"',azubiid='"+t.getAzubi().getID()+
				"' WHERE zeugnisid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Zeugnis t) {
		String sql = "delete from zeugnis"+
				" WHERE zeugnisid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public ArrayList<Zeugnis> getAll() {
		String sql = "select * from zeugnis;";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Zeugnis> zeugnisListe = new ArrayList<Zeugnis>();
		try{
		 while (rs.next())
	      {
	        Zeugnis z = new Zeugnis();
	        z.setID(rs.getString("zeugnisid"));
	        z.setJahr(rs.getInt("jahr"));
	        z.setZeugnisKonferenz(rs.getString("zeugniskonferenz"));
	        z.setAzubi(azubiDao.getByGuid(rs.getString("azubiid")));
	        z.setPositionen(zeugnispositionDao.gibPositionenZuZeugnis(z));
	        zeugnisListe.add(z);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLZeugnisDAO");
		}
		return zeugnisListe;
	}

	@Override
	public Zeugnis getByGuid(String guid) {
		String sql = "select * from zeugnis where zeugnisid='"+guid+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Zeugnis z = new Zeugnis();
		try {
			rs.next();
			z.setID(rs.getString("zeugnisid"));
	        z.setJahr(rs.getInt("jahr"));
	        z.setZeugnisKonferenz(rs.getString("zeugniskonferenz"));
	        z.setAzubi(azubiDao.getByGuid(rs.getString("azubiid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return z;
	}
	
	public Zeugnis getZeugnisByAzubi(Azubi a, int jahr) {
		String sql = "select * from zeugnis where azubiid='"+a.getID()+"' and jahr = "+String.valueOf(jahr)+";";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Zeugnis z = new Zeugnis();
		try {
			rs.next();
			z.setID(rs.getString("zeugnisid"));
	        z.setJahr(rs.getInt("jahr"));
	        z.setZeugnisKonferenz(rs.getString("zeugniskonferenz"));
	        z.setAzubi(azubiDao.getByGuid(rs.getString("azubiid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return z;
	}

	@Override
	public boolean isVorhanden(Zeugnis t) {
		String sql = "select * from zeugnis where "
				+ "jahr="+t.getJahr()
				+",zeugniskonferenz='"+t.getZeugnisKonferenz()
				+"',azubiid='"+t.getAzubi().getID()
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
