package dao;

import java.sql.Connection;
import utils.DBConnection;

public class ProdottoDAO {
	private Connection connection;

    public ProdottoDAO() {
        connection = DBConnection.getConnection();
    }
}
