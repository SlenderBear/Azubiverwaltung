package datenbank.connector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import datenbank.Konstanten;
import datenbank.PropertyHandling;

/**
 * @author dunkel.gregor
 * 
 */
public class SqliteConnector implements StandardSqlConnector {
	
	private static SqliteConnector connector;
	private static Connection con;
	private static final String DEFAULT_PATH = "Erstellung_DB_SQLite.sql";

	/**
	 * Private da Singleton.
	 */
	private SqliteConnector() {
	}
	
	/**
	 * Liefert die Instanz des {@link SqliteConnector}s.
	 * 
	 * @return Connector
	 */
	public static SqliteConnector getInstance() {
		if(connector == null) {
			connector = new SqliteConnector();
			connector.initDB();
		}
		return connector;
	}
	
	/**
	 * Prüft ob die DB bereits vorhanden ist. Ansonsten wird dies neu erstellt.
	 * Öffnet anschließend eine Verbindung.
	 */
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
			PropertyHandling p = new PropertyHandling();
			String path = p.liesPropAus(Konstanten.sqlite_path);
			if(path == null || path.isEmpty()) {
				path = DEFAULT_PATH;
			}
			File f = new File(path);
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
	@Override
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
	@Override
	public boolean statementExecute(String sql) {
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      con.setAutoCommit(false);

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
	
	@Override
	public String getNewGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

 }
