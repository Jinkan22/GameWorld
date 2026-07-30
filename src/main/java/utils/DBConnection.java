package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/GameWorld";
    private static final String USER = "gameworld";
    private static final String PASSWORD = "gameworld";

    private static Connection connection = null;

    
    public static Connection getConnection() {

        if (connection == null) {
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        
        return connection;
    }
}