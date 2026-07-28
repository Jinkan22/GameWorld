package dao;

import java.sql.Connection;
import utils.DBConnection;

public class PiattaformaDAO {
	private Connection connection;

    public PiattaformaDAO() {
        connection = DBConnection.getConnection();
    }
}
