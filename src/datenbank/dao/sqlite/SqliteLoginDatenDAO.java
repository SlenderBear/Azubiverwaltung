package datenbank.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Login;
import datenbank.connector.SqliteConnector;
import datenbank.dao.StandardDAO;

/**
 * 
 * @author mertmann.justin Die Klasse MySqlLoginDatenDAO enthält sämtliche
 *         Funktionen zur Datenbankanbindung des Login_Datenobjektes
 */
public class SqliteLoginDatenDAO implements StandardDAO<Login> {
	private static final String DAO_NAME = Login.class.getName();
	private final int verschlüsselungsverschiebung = 26;

	private SqliteBerechtigungDAO dao = new SqliteBerechtigungDAO();

	@Override
	public String getClassName() {
		return DAO_NAME;
	}

	@Override
	public Login insert(Login t) {
		String guid = SqliteConnector.getInstance().getNewGUID();
		String sql = "INSERT INTO login_daten values('" + guid + "','"
				+ t.getLoginName() + "','"
				+ verschluesseln(t.getLoginPasswort()) + "','"
				+ t.getBerechtigung().getID() + "');";
		SqliteConnector.getInstance().statementExecute(sql);
		t.setID(guid);
		return t;
	}

	@Override
	public boolean update(Login t) {
		String sql = "UPDATE login_daten" + " SET " + "benutzername='"
				+ t.getLoginName() + "',passwort='"
				+ verschluesseln(t.getLoginPasswort()) + "',berechtigungid='"
				+ t.getBerechtigung().getID() + "' WHERE loginid='" + t.getID()
				+ "';";
		return SqliteConnector.getInstance().statementExecute(sql);

	}

	@Override
	public boolean delete(Login t) {
		String sql = "delete from login_daten" + " WHERE loginid='" + t.getID()
				+ "';";
		return SqliteConnector.getInstance().statementExecute(sql);

	}

	@Override
	public ArrayList<Login> getAll() {
		String sql = "select * from login_daten;";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		ArrayList<Login> loginListe = new ArrayList<Login>();
		try {
			while (rs.next()) {
				Login l = new Login();
				l.setID(rs.getString("loginid"));
				l.setLoginName(rs.getString("benutzername"));
				l.setLoginPasswort(entschluesseln(rs.getString("passwort")));
				l.setBerechtigung(dao.getByGuid(rs.getString("berechtigungid")));

				loginListe.add(l);
			}
		} catch (Exception e) {
			System.out.println("Fehler in MySQLLoginDAO");
		}
		return loginListe;
	}

	@Override
	public Login getByGuid(String loginid) {
		String sql = "select * from login_daten where loginid='" + loginid
				+ "';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
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

	// TODO verschlüsseln
	private String verschluesseln(String passwort) {
		return encode(passwort, verschlüsselungsverschiebung);

	}

	// TODO entschlüsseln
	private String entschluesseln(String passwort) {
		return encode(passwort, 26 - verschlüsselungsverschiebung);
	}

	@Override
	public boolean isVorhanden(Login t) {
		String sql = "select * from login_daten where " + "benutzername='"
				+ t.getLoginName() + "';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		try {
			return rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkLogin(Login l){
		String sql = "select * from login_daten where benutzername='"
				+ l.getLoginName() + "' AND passwort='"+l.getLoginPasswort()+"';";
		ResultSet rs = SqliteConnector.getInstance().executeQuery(sql);
		try {
			if(rs.isClosed()) {
				return false;
			}
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
			if(rs.isAfterLast()) {
				return null;
			}
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

	/**
	 * 
	 * @param Übergabe
	 *            einer Zeichenkette die Verschlüsselt werden soll
	 * @param verschiebung
	 *            die durch die Caesar Verschlüsselung angewendet wird
	 * @return Gibt die verschlüsselte Zeichenkette zurück
	 */
	private String encode(String s, int verschiebung) {
		s = s.toUpperCase();
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++)
			chars[i] = encode(chars[i], verschiebung);
		return String.valueOf(chars);
	}

	/**
	 * 
	 * @param Übergabe
	 *            eines Characters zur Verschlüsselung
	 * @param verschiebung
	 *            die mit der Caesarverschlüsselung angewendet wird
	 * @return Gibt den Character verschlüsselt zurück
	 */
	private char encode(char c, int verschiebung) {
		if (c >= 'A' && c <= 'Z')
			return (char) ((c - 'A' + verschiebung) % 26 + 'A');
		else
			return c;
	}
}
