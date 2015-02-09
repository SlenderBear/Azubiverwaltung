package datenbank.dao.mySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Login;
import datenbank.connector.MySQLConnector;
import datenbank.connector.SqliteConnector;
import datenbank.dao.StandardDAO;
/**
 *  
 * @author mertmann.justin
 *	Die Klasse MySqlLoginDatenDAO enthält sämtliche Funktionen zur Datenbankanbindung des Login_Datenobjektes
 */
public class MySqlLoginDatenDAO implements StandardDAO<Login> {
	
	private static final String DAO_NAME= Login.class.getName();
	
	private MySqlBerechtigungDAO dao = new MySqlBerechtigungDAO();
	private final int verschluesselungsverschiebung = 26;
	
	@Override
	public String getClassName() {
		return DAO_NAME;
	}
	
	@Override
	public Login insert(Login t) {
		String guid = MySQLConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO login_daten values('" 
				+ guid 
				+ "','" + t.getLoginName()
				+ "','" + verschluesseln(t.getLoginPasswort())
				+ "','" + t.getBerechtigung().getID() 
				+ "');";
		MySQLConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Login t) {
		String sql = "UPDATE login_daten"+
				" SET "
				+ "benutzername='"+t.getLoginName()
				+"',passwort='"+verschluesseln(t.getLoginPasswort())
				+"',berechtigungid='"+t.getBerechtigung().getID()
				+"' WHERE loginid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public boolean delete(Login t) {
		String sql = "delete from login_daten"+
				" WHERE loginid='"+t.getID()+"';";
		return MySQLConnector.getInstance().statementExecute(sql);
	
	}

	@Override
	public ArrayList<Login> getAll() {
		String sql = "select * from login_daten;";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		ArrayList<Login> loginListe = new ArrayList<Login>();
		try{
		 while (rs.next())
	      {
			 Login l = new Login();
	        l.setID(rs.getString("loginid"));
	        l.setLoginName(rs.getString("benutzername"));
	        l.setLoginPasswort(entschluesseln(rs.getString("passwort")));
	        l.setBerechtigung(dao.getByGuid(rs.getString("berechtigungid")));  
	        
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
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
		Login l = new Login();
		try {
			rs.next();
	        l.setID(rs.getString("loginid"));
	        l.setLoginName(rs.getString("benutzername"));
	        l.setLoginPasswort(entschluesseln(rs.getString("passwort")));
	        l.setBerechtigung(dao.getByBerechtigungID(rs.getInt("berechtigungid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	private String verschluesseln(String passwort){
	    return encode(passwort, verschluesselungsverschiebung);
	}
	private String entschluesseln(String passwort){
		    return encode(passwort, 26-verschluesselungsverschiebung);
		}

	@Override
	public boolean isVorhanden(Login t) {
		String sql = "select * from login_daten where "
				+ "benutzername='"+t.getLoginName()
				+"';";
		ResultSet rs = MySQLConnector.getInstance().executeQuery(sql);
			try {
				return rs.first();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public boolean checkLogin(Login l){
		String sql = "select * from login_daten where " + "benutzername='"
				+ l.getLoginName() + "' AND passwort='"+l.getLoginPasswort()+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		try {
			return rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Login getLoginByLoginDaten(Login l){
		String sql = "select * from login_daten where benutzername='"
				+ l.getLoginName() + "' AND passwort='"+l.getLoginPasswort()+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		try {
			rs.next();
			if(rs.isAfterLast()) {
				return null;
			}
	        l.setID(rs.getString("loginid"));
	        l.setLoginName(rs.getString("benutzername"));
	        l.setLoginPasswort(entschluesseln(rs.getString("passwort")));
			l.setBerechtigung(dao.getByBerechtigungID(rs.getInt("berechtigungid")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
	 * 
	 * @param Übergabe einer Zeichenkette die Verschlüsselt werden soll
	 * @param verschiebung die durch die Caesar Verschlüsselung angewendet wird
	 * @return Gibt die verschlüsselte Zeichenkette zurück
	 */
	private String encode(String s, int verschiebung){
	    s = s.toUpperCase();
	    char[] chars = s.toCharArray();
	    for(int i = 0; i < s.length(); i++)
	        chars[i] = encode(chars[i], verschiebung);
	    return String.valueOf(chars);
	}
	/**
	 * 
	 * @param Übergabe eines Characters zur Verschlüsselung
	 * @param verschiebung die mit der Caesarverschlüsselung angewendet wird
	 * @return Gibt den Character verschlüsselt zurück
	 */
	private char encode(char c, int verschiebung){
	    if(c >= 'A' && c <= 'Z')
	        return (char)((c-'A'+verschiebung)%26 + 'A');
	    else
	        return c;
	}
}
