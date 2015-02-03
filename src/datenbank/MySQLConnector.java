package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	protected static Connection getInstance() {
		if (con == null)
			mySQLConnection();
		return con;
	}

	protected boolean statementExecute(String sql) {
		if (con != null) {
			// Statement erzeugen.
			Statement st;
			try {
				st = con.createStatement();

				int result = st.executeUpdate(sql);
				if (result > 0) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	protected ResultSet executeQuery(String sql){
		if (con != null) {
			Statement st;
			try {
				st = con.createStatement();
				
				 ResultSet rs = st.executeQuery(sql);
				 
				return rs;
			}
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		}
		return null;
		
	}

	// public void ausbilderEinfuegen(String name, String vorname,
	// String telefonnummer, String eMail, String BetriebID) {
	// con = getInstance();
	//
	// if (con != null) {
	// // Statement erzeugen.
	// Statement st;
	// try {
	// st = con.createStatement();
	//
	// String sql = "INSERT INTO ausbilder values("
	// + getNewGUID()
	// + ",'" + name
	// + ",'" + vorname
	// + ",'" + plz
	// + ",'" + ort
	// + ")";
	// st.executeUpdate(sql);
	//
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	public String getNewGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	// public String getBetriebGUID

}
