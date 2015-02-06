package datenbank.connector;

import java.sql.ResultSet;

/**
 * Stellt die StandardMethoden, die f�r einen SQL-Connector ben�tigt werden, dar.
 * @author dietz.philipp
 *
 */
public interface StandardSqlConnector {
	
	/**
	 * F�hrt ein Insert/Update/Delete aus.
	 * @param sql String
	 * @return true wenn erfolgreich.
	 */
	boolean statementExecute(String sql);
	
	/**
	 * Liefert die Objekte zum Query.
	 * @param sql String
	 * @return {@link ResultSet}
	 */
	ResultSet executeQuery(String sql);
	
	/**
	 * Erzeugt eine GUID.
	 * @return Guid/UUID String
	 */
	String getNewGUID();
}
