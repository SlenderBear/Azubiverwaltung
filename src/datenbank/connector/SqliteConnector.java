package datenbank.connector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

/**
 * @author dunkel.gregor
 * 
 */
public class SqliteConnector implements StandardSqlConnector {
	
	private static SqliteConnector connector;
	private static Connection con;

	/**
	 * Private da Singleton.
	 */
	private SqliteConnector() {
	}
	
	/**
	 * Liefert die Instanz des {@link SqliteConnector}s.
	 * 
	 * @return
	 */
	public static SqliteConnector getInstance() {
		if(connector == null) {
			connector = new SqliteConnector();
			connector.initDB();
		}
		return connector;
	}
	
	private void initDB(){
		File f = new File("azubiverwaltung.db");
		if(f.exists()) {
			// Gibts die db schon? -> Connection aufbauen.
			getConnection();
			return;
		}
		// Db nicht vorhanden -> Initialisieren.
		executeInitSkript();
	}
	
	/**
	 * Baut eine Verbindung zur Datenbank auf.
	 * @return {@link Connection}
	 */
	private void getConnection() {
		 if(con == null)
		 {
		    try {
		      Class.forName("org.sqlite.JDBC");
		      con = DriverManager.getConnection("jdbc:sqlite:azubiverwaltung.db");
		    } catch ( Exception e ) {
		    	throw new RuntimeException(this.getClass() + ":" + e.getMessage());
		    }
		 }
	}
	
	/**
	 * Führt das Skript aus, das die Datenbank initialisiert.
	 */
	private void executeInitSkript() {
		try {
			File f = new File("W:\\git\\Azubiverwaltung\\src\\Erstellung_DB_SqLite.sql");
			List<String> sqlList = FileUtils.readLines(f);

			 Class.forName("org.sqlite.JDBC"); // Datenbanktreiber für JDBC
													// Schnittstellen laden.

			// Verbindung zur JDBC-Datenbank herstellen.
			con = DriverManager.getConnection("jdbc:sqlite:azubiverwaltung.db");
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
	 * Führt den übergebenen Select Befehl aus.
	 * @param sql String
	 * @return {@link ResultSet} Ergebnisse.
	 */
	public ResultSet executeQuery(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con.setAutoCommit(false);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			throw new RuntimeException(this.getClass() + ":" + e.getMessage());
		}
		return rs;
	}
	
	/**
	 * Führt den übergebenen Sql-Befehl aus.
	 * @param sql String
	 * @return true wenn erfolgreich.
	 */
	public boolean statementExecute(String sql) {
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      con.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = con.createStatement();
	      if(stmt.executeUpdate(sql) > 0) {
	    	  con.commit();
		      stmt.close();
	    	  return true;
	      }
	    } catch ( Exception e ) {
	    	throw new RuntimeException(this.getClass() + ":" + e.getMessage());
	    }
		return false;
	}
	public String getNewGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

 }
