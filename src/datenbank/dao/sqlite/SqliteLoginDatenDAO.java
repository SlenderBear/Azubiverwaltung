package datenbank.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Login;
import datenbank.connector.SqliteConnector;
import datenbank.dao.StandardDAO;
/**
 * 
 * @author mertmann.justin
 *	Die Klasse MySqlLoginDatenDAO enthält sämtliche Funktionen zur Datenbankanbindung des Login_Datenobjektes
 */
public class SqliteLoginDatenDAO implements StandardDAO<Login> {
	private static final String DAO_NAME= Login.class.getName();
	
	private SqliteBerechtigungDAO dao = new SqliteBerechtigungDAO();
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}
	
	@Override
	public Login insert(Login t) {
		String guid = SqliteConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO login_daten values('" 
				+ guid 
				+ "','" + t.getLoginName()
				+ "','" + verschluesseln(t.getLoginPasswort())
				+ "','" + t.getBerechtigung().getID() 
				+ "');";
		SqliteConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Login t) {
		String sql = "UPDATE login_daten"+
				"SET "
				+ "benutzername='"+t.getLoginName()
				+"',passwort='"+verschluesseln(t.getLoginPasswort())
				+"',berechtigungid='"+t.getBerechtigung().getID()
				+"' WHERE loginid='"+t.getID()+"';";
		return SqliteConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public boolean delete(Login t) {
		String sql = "delete from login_daten"+
				" WHERE loginid='"+t.getID()+"';";
		return SqliteConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public ArrayList<Login> getAll() {
		String sql = "select * from login_daten;";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		ArrayList<Login> loginListe = new ArrayList<Login>();
		try{
		 while (rs.next())
	      {
			 Login l = new Login();
	        l.setID(rs.getString("loginid"));
	        l.setLoginName(rs.getString("benutzername"));
	        l.setLoginPasswort(entschluesseln(rs.getString("passwort")));
	        l.setBerechtigung(dao.getByGuid(rs.getString("bezeichnung")));  
	        
	        loginListe.add(l);
	      }
		}catch(Exception e){
			System.out.println("Fehler in MySQLLoginDAO");
		}
		return loginListe;
	}

	@Override
	public Login getByGuid(String loginid) {
		String sql = "select * from login_daten where loginid='"+loginid+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		Login l = new Login();
		try {
			rs.next();
	        l.setID(rs.getString("loginid"));
	        l.setLoginName(rs.getString("benutzername"));
	        l.setLoginPasswort(entschluesseln(rs.getString("passwort")));
	        l.setBerechtigung(dao.getByGuid(rs.getString("bezeichnung"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	//TODO verschlüsseln
	private String verschluesseln(String passwort){
//	String verschlüsseltesPasswort = "";
	return passwort;
	}
	//TODO entschlüsseln
	private String entschluesseln(String passwort){
//	String unverschlüsseltesPasswort = "";
	return passwort;
	}

	@Override
	public boolean isVorhanden(Login t) {
		String sql = "select * from login_daten where "
				+ "benutzername='"+t.getLoginName()
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
