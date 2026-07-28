package dao;

import java.sql.Connection;
import utils.DBConnection;

public class OrdineDAO {
	private Connection connection;

    public OrdineDAO() {
        connection = DBConnection.getConnection();
    }
}
