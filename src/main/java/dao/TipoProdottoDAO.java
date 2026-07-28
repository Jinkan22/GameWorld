package dao;

import java.sql.Connection;
import utils.DBConnection;

public class TipoProdottoDAO {
	private Connection connection;

    public TipoProdottoDAO() {
        connection = DBConnection.getConnection();
    }
}
