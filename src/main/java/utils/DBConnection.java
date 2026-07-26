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
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Errore nella connessione al database: " + e.getMessage());
                return null;
            }
        }
        
        return connection;
    }
}