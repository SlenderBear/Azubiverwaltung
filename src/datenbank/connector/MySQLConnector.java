package datenbank.connector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import datenbank.Konstanten;
import datenbank.PropertyHandling;

public class MySQLConnector implements StandardSqlConnector {
	
	private static final String DEFAULT_PATH = "src\\Erstellung_DB_mySQL.sql";
	private static Connection con = null;
	private static String dbHost = "localhost"; // Hostname
	private static String dbPort = "3306"; // Port -- Standard: 3306
	private static String dbName = "azubiverwaltung"; // Datenbankname
	private static String dbUser = "root"; // Datenbankuser
	private static MySQLConnector connector;
	
	/**
	 * Stellt die Verbindung zur MySQL DB her.
	 */
	private void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber f�r JDBC
													// Schnittstellen laden.

			// Verbindung zur JDBC-Datenbank herstellen.
			con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + dbName + "?" + "user=" + dbUser);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Verbindung nicht moglich");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			throw new RuntimeException();
		}
	}
	
	/**
	 * Erstellt die Datenbank / Tabellen.
	 */
	private void initDB(){
		try{
			getConnection();
		}catch(Exception e){
			executeInitSkript();
		}
	}


	/**
	 * F�hrt das Skript aus, das die Datenbank initialisiert.
	 */
	private void executeInitSkript() {
		try {
			PropertyHandling p = new PropertyHandling();
			String path = p.liesPropAus(Konstanten.mysql_path);
			if(path == null || path.isEmpty()) {
				path = DEFAULT_PATH;
			}
			File f = new File(path);
			List<String> sqlList = FileUtils.readLines(f);

			Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber f�r JDBC
													// Schnittstellen laden.

			// Verbindung zur JDBC-Datenbank herstellen.
			con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + "?" + "user=root");
			System.out.println(con.getClientInfo());
			
			for (int i = 0; i < sqlList.size(); i++) {
				if(sqlList.get(i).isEmpty() || sqlList.get(i).compareTo(" ") == 0) {
					continue;
				}
				statementExecute(sqlList.get(i));
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Singleton.
	 * @return {@link MySQLConnector}
	 */
	public static MySQLConnector getInstance() {
		if (connector == null) {
			connector = new MySQLConnector();
			connector.initDB();
		}
		return connector;
	}
	
	@Override
	public boolean statementExecute(String sql) {
		if (con == null) {
			getConnection();
		}
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
			return false;
		}

	}

	@Override
	public ResultSet executeQuery(String sql){
		if (con == null) {
			getConnection();
		}

		Statement st;
		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			return rs;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return null;
		}

	}

	@Override
	public String getNewGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
