package datenbank.SqliteDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Zeugnis;
import datenbank.SqliteConnector;
import datenbank.StandardDAO;
import datenbank.SqliteDAO.SqliteAzubiDAO;

public class SqliteZeugnisDAO implements StandardDAO<Zeugnis>{
private static final String DAO_NAME= Zeugnis.class.getName();
	
	
	
	SqliteAzubiDAO dao = new SqliteAzubiDAO();
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}
	
	@Override
	public Zeugnis insert(Zeugnis t) {
		String guid = SqliteConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO zeugnis values('" 
				+ guid 
				+ "'," + t.getJahr()
				+ ",'" + t.getZeugnisKonferenz()
				+ "','" + t.getAzubi().getID() 
				+ "');";
		SqliteConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Zeugnis t) {
		String sql = "UPDATE zeugnis"+
				"SET "
				+ "jahr="+t.getJahr()
				+",zeugniskonferenz='"+t.getZeugnisKonferenz()
				+"',azubiid='"+t.getAzubi().getID()+
				"' WHERE zeugnisid='"+t.getID()+"';";
		return SqliteConnector.getInstance().statementExecute(sql);
	}

	@Override
	public boolean delete(Zeugnis t) {
		String sql = "delete from zeugnis"+
				" WHERE zeugnisid='"+t.getID()+"';";
		return SqliteConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public ArrayList<Zeugnis> getAll() {
		String sql = "select * from zeugnis;";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		ArrayList<Zeugnis> zeugnisListe = new ArrayList<Zeugnis>();
		try{
		 while (rs.next())
	      {
	        Zeugnis z = new Zeugnis();
	        z.setID(rs.getString("lehrerid"));
	        z.setJahr(rs.getInt("jahr"));
	        z.setZeugnisKonferenz(rs.getString("zeugniskonferenz"));
	        z.setAzubi(dao.getByGuid(rs.getString("azubiid")));
	        
	        zeugnisListe.add(z);
	      }
		}catch(Exception e){
			System.out.println("Fehler in SqliteZeugnisDAO");
		}
		return zeugnisListe;
	}

	@Override
	public Zeugnis getByGuid(String guid) {
		String sql = "select * from zeugnis where zeugnisid='"+guid+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		Zeugnis z = new Zeugnis();
		try {
			rs.next();
			z.setID(rs.getString("lehrerid"));
	        z.setJahr(rs.getInt("jahr"));
	        z.setZeugnisKonferenz(rs.getString("zeugniskonferenz"));
	        z.setAzubi(dao.getByGuid(rs.getString("azubiid"))); 
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
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
			try {
				return rs.first();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

}
