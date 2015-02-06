package datenbank.connector;

import java.sql.ResultSet;

public interface StandardSqlConnector {
	
	boolean statementExecute(String sql);
	
	ResultSet executeQuery(String sql);
	
	String getNewGUID();
}
