package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OrdineBean;
import utils.DBConnection;

public class OrdineDAO {
	private Connection connection;

    public OrdineDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di un ordine in base alla chiave primaria
    public OrdineBean doRetrieveByKey (int idOrdine){
    	OrdineBean ordine = null;
    	
    	try {
    		String sql = "SELECT * FROM ordine WHERE idOrdine = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idOrdine);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			ordine = new OrdineBean();
    			
    			ordine.setIdOrdine(rs.getInt("idOrdine"));
    			ordine.setDataOrdine(rs.getDate("dataOrdine"));
    			ordine.setTotale(rs.getFloat("totale"));
    			ordine.setStatoOrdine(rs.getString("statoOrdine"));
    			ordine.setIdUtente(rs.getInt("idUtente"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return ordine;
    }
    
    //lettura di tutti gli ordini
    public ArrayList<OrdineBean> doRetrieveAll(){
    	ArrayList<OrdineBean> list = new ArrayList<OrdineBean>();
    	
    	try {
    		String sql = "SELECT * FROM ordine";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			OrdineBean ordine = new OrdineBean();
    			
    			ordine.setIdOrdine(rs.getInt("idOrdine"));
    			ordine.setDataOrdine(rs.getDate("dataOrdine"));
    			ordine.setTotale(rs.getFloat("totale"));
    			ordine.setStatoOrdine(rs.getString("statoOrdine"));
    			ordine.setIdUtente(rs.getInt("idUtente"));
    			
    			list.add(ordine);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un ordine
    public boolean doSave(OrdineBean ordine) {
    	
    	try {
    		String sql = "INSERT INTO ordine "
    				+ "(dataOrdine, totale, statoOrdine, idUtente) "
    				+ "VALUES (?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setDate(1, ordine.getDataOrdine());
    		ps.setFloat(2, ordine.getTotale());
    		ps.setString(3, ordine.getStatoOrdine());
    		ps.setInt(4, ordine.getIdUtente());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un ordine
    public boolean doUpdate(OrdineBean ordine) {
    	try {
    		String sql = "UPDATE ordine "
    				+ "SET dataOrdine=?, totale=?, statoOrdine=?, idUtente=? "
    				+ "WHERE idOrdine=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setDate(1, ordine.getDataOrdine());
    		ps.setFloat(2, ordine.getTotale());
    		ps.setString(3, ordine.getStatoOrdine());
    		ps.setInt(4, ordine.getIdUtente());
    		ps.setInt(5, ordine.getIdOrdine());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura di tutti gli ordini di un utente
    public ArrayList<OrdineBean> doRetrieveByIdUtente(int idUtente){
    	ArrayList<OrdineBean> list = new ArrayList<OrdineBean>();
    	
    	try {
    		String sql = "SELECT * FROM ordine WHERE idUtente=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idUtente);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			OrdineBean ordine = new OrdineBean();
    			
    			ordine.setIdOrdine(rs.getInt("idOrdine"));
    			ordine.setDataOrdine(rs.getDate("dataOrdine"));
    			ordine.setTotale(rs.getFloat("totale"));
    			ordine.setStatoOrdine(rs.getString("statoOrdine"));
    			ordine.setIdUtente(rs.getInt("idUtente"));
    			
    			list.add(ordine);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
}