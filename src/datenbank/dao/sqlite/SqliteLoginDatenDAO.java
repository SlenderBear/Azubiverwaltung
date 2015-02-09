package datenbank.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Login;
import datenbank.connector.SqliteConnector;
import datenbank.dao.StandardDAO;

/**
 * 
 * @author mertmann.justin Die Klasse MySqlLoginDatenDAO enth�lt s�mtliche
 *         Funktionen zur Datenbankanbindung des Login_Datenobjektes
 */
public class SqliteLoginDatenDAO implements StandardDAO<Login> {
	private static final String DAO_NAME = Login.class.getName();
	private final int verschl�sselungsverschiebung = 26;

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
		String sql = "UPDATE login_daten" + "SET " + "benutzername='"
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

	// TODO verschl�sseln
	private String verschluesseln(String passwort) {
		return encode(passwort, verschl�sselungsverschiebung);

	}

	// TODO entschl�sseln
	private String entschluesseln(String passwort) {
		return encode(passwort, 26 - verschl�sselungsverschiebung);
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
	        l.setID(rs.getString("loginid"));
	        l.setLoginName(rs.getString("benutzername"));
	        l.setLoginPasswort(entschluesseln(rs.getString("passwort")));
	        l.setBerechtigung(dao.getByGuid(rs.getString("berechtigungid"))); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * 
	 * @param �bergabe
	 *            einer Zeichenkette die Verschl�sselt werden soll
	 * @param verschiebung
	 *            die durch die Caesar Verschl�sselung angewendet wird
	 * @return Gibt die verschl�sselte Zeichenkette zur�ck
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
	 * @param �bergabe
	 *            eines Characters zur Verschl�sselung
	 * @param verschiebung
	 *            die mit der Caesarverschl�sselung angewendet wird
	 * @return Gibt den Character verschl�sselt zur�ck
	 */
	private char encode(char c, int verschiebung) {
		if (c >= 'A' && c <= 'Z')
			return (char) ((c - 'A' + verschiebung) % 26 + 'A');
		else
			return c;
	}
}
