package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

public class MySQLConnector {
	String sql = "";

	String url = "mysql://localhost:3306/Azubiverwaltung";

	private static Connection con = null;
	private static String dbHost = "localhost"; // Hostname
	private static String dbPort = "3306"; // Port -- Standard: 3306
	private static String dbName = "azubiverwaltung"; // Datenbankname
	private static String dbUser = "user"; // Datenbankuser
	private static String dbPass = "user"; // Datenbankpasswort

	private static void mySQLConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber für JDBC
													// Schnittstellen laden.

			// Verbindung zur JDBC-Datenbank herstellen.
			con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + dbName + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPass);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Verbindung nicht moglich");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	private static Connection getInstance() {
		if (con == null)
			mySQLConnection();
		return con;
	}

	// Gebe Tabelle in die Konsole aus
	public void betriebEinfuegen(String firmenBezeichnung, String strasse,
			String plz, String ort) {
		con = getInstance();

		if (con != null) {
			// Statement erzeugen.
			Statement st;
			try {
				st = con.createStatement();

				// Tabelle anzeigen
				String sql = "INSERT INTO betrieb values(" 
						+ getNewGUID()
						+ ",'" + firmenBezeichnung 
						+ ",'" + strasse 
						+ ",'" + plz 
						+ ",'" + ort 
						+ ")";
				st.executeUpdate(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getNewGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
