package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    public static Connection get;
    private final String url="jdbc:mysql://localhost/webapp";
    private final String user="root";
    private final String password="";
    private final String driver="com.mysql.jdbc.Driver";
    private static Connection connection;

    public Connection getConnection()throws ClassNotFoundException,SQLException {
        Class.forName(driver);
        connection= DriverManager.getConnection(url,user,password);
        System.out.println("dabase connection success");
        return connection;

    }
}
