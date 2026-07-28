package dao;

import java.sql.Connection;
import utils.DBConnection;

public class ElementoCarrelloDAO {
	private Connection connection;
	
	public ElementoCarrelloDAO() {
		connection = DBConnection.getConnection();
	}
}
