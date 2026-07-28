package dao;

import java.sql.Connection;
import utils.DBConnection;

public class GenereDAO {
	private Connection connection;

    public GenereDAO() {
        connection = DBConnection.getConnection();
    }
}
