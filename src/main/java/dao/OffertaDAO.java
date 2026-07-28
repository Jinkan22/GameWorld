package dao;

import java.sql.Connection;
import utils.DBConnection;

public class OffertaDAO {
	private Connection connection;

    public OffertaDAO() {
        connection = DBConnection.getConnection();
    }
}
