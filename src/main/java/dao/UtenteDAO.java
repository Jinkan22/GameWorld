package dao;

import java.sql.Connection;
import utils.DBConnection;

public class UtenteDAO {
	private Connection connection;

    public UtenteDAO() {
        connection = DBConnection.getConnection();
    }
}
