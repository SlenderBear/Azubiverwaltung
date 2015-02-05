package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author dunkel.gregor
 * 
 */
public class SqliteConnector {
	
	private static SqliteConnector connector;
	private static Connection con;

	/**
	 * Private da Singleton.
	 */
	private SqliteConnector() {
	}
	
	/**
	 * Liefert die Instanz des {@link SqliteConnector}s.
	 * @return
	 */
	public static SqliteConnector getInstance() {
		if(connector == null) {
			connector = new SqliteConnector();
		}
		return connector;
	}
	
	/**
	 * Baut eine Verbindung zur Datenbank auf.
	 * @return {@link Connection}
	 */
	private Connection getConnection() {
		 Connection c = null;
		 if(con == null)
		 {
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:azubiverwaltung.db");
		    } catch ( Exception e ) {
		    	throw new RuntimeException(this.getClass() + ":" + e.getMessage());
		    }
		 }
		 return c;
	}

	/**
	 * Führt den übergebenen Select Befehl aus.
	 * @param sql String
	 * @return {@link ResultSet} Ergebnisse.
	 */
	public ResultSet executeQuery(String sql) {
		Connection c = this.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c.setAutoCommit(false);

			stmt = c.createStatement();
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
	    Connection c = this.getConnection();
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      if(stmt.executeUpdate(sql) > 0) {
	    	  c.commit();
		      stmt.close();
		      c.close();
	    	  return true;
	      }
	    } catch ( Exception e ) {
	    	throw new RuntimeException(this.getClass() + ":" + e.getMessage());
	    }
		return false;
	}
 }
