package Dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class MySQLConnUtils {
 
public static Connection getMySQLConnection()
        throws ClassNotFoundException, SQLException {
    String hostName = "127.0.0.1";
    String dbName = "dictionary";
    String userName = "root";
    String password = "pydary1207";
    return getMySQLConnection(hostName, dbName, userName, password);
}
 
public static Connection getMySQLConnection(String hostName, String dbName,
        String userName, String password) throws SQLException,
        ClassNotFoundException {

    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?autoReconnect=true&useSSL=false";
 
    Connection conn = DriverManager.getConnection(connectionURL, userName,
            password);
    return conn;
}
}