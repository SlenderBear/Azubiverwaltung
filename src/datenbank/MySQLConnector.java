package datenbank;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MySQLConnector {
	Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://localhost:3306/Azubiverwaltung";
    String user = "user";
    String password = "user";

}
