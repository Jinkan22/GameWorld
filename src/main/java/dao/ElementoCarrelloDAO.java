package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ElementoCarrelloBean;
import utils.DBConnection;

public class ElementoCarrelloDAO {
	private Connection connection;
	
	public ElementoCarrelloDAO() {
		connection = DBConnection.getConnection();
	}
	
	//lettura di un elemento del carrello in base alla chiave primaria
    public ElementoCarrelloBean doRetrieveByKey (int idElementoCarrello){
    	ElementoCarrelloBean elementoCarrello = null;
    	
    	try {
    		String sql = "SELECT * FROM elementoCarrello WHERE idElementoCarrello=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idElementoCarrello);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			elementoCarrello = new ElementoCarrelloBean();
    			
    			elementoCarrello.setIdElementoCarrello(rs.getInt("idElementoCarrello"));
    			elementoCarrello.setQuantita(rs.getInt("quantita"));
    			elementoCarrello.setIdUtente(rs.getInt("idUtente"));
    			elementoCarrello.setIdProdotto(rs.getInt("idProdotto"));
    			
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return elementoCarrello;
    }
    
    //lettura di tutti gli elementi del carrello
    public ArrayList<ElementoCarrelloBean> doRetrieveAll(){
    	ArrayList<ElementoCarrelloBean> list = new ArrayList<ElementoCarrelloBean>();
    	
    	try {
    		String sql = "SELECT * FROM elementoCarrello";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			ElementoCarrelloBean elementoCarrello = new ElementoCarrelloBean();
    			
    			elementoCarrello.setIdElementoCarrello(rs.getInt("idElementoCarrello"));
    			elementoCarrello.setQuantita(rs.getInt("quantita"));
    			elementoCarrello.setIdUtente(rs.getInt("idUtente"));
    			elementoCarrello.setIdProdotto(rs.getInt("idProdotto"));
    			
    			list.add(elementoCarrello);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un elemento del carrello
    public boolean doSave(ElementoCarrelloBean elementoCarrello) {
    	
    	try {
    		String sql = "INSERT INTO elementoCarrello "
    				+ "(quantita, idUtente, idProdotto) "
    				+ "VALUES (?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, elementoCarrello.getQuantita());
    		ps.setInt(2, elementoCarrello.getIdUtente());
    		ps.setInt(3, elementoCarrello.getIdProdotto());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un elemento del carrello
    public boolean doUpdate(ElementoCarrelloBean elementoCarrello) {
    	try {
    		String sql = "UPDATE elementoCarrello "
    				+ "SET quantita=?, idUtente=?, idProdotto=? "
    				+ "WHERE idElementoCarrello=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, elementoCarrello.getQuantita());
    		ps.setInt(2, elementoCarrello.getIdUtente());
    		ps.setInt(3, elementoCarrello.getIdProdotto());
    		ps.setInt(4, elementoCarrello.getIdElementoCarrello());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un elemento del carrello
    public boolean doDelete(int idElementoCarrello) {
    	try {
    		String sql = "DELETE FROM elementoCarrello WHERE idElementoCarrello=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idElementoCarrello);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}
