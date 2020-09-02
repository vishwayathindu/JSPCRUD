package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public static Connection get;
    private static Connection connection;
    private final String url = "jdbc:mysql://localhost:3307/webapp";
    private final String user = "vishwa";
    private final String password = "12345";
    private final String driver = "com.mysql.jdbc.Driver";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        //System.out.println("dabase connection success");
        return connection;

    }
}
