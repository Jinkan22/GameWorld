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
    			ordine.setAcquirente(rs.getString("acquirente"));
    			ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
    			ordine.setTotale(rs.getBigDecimal("totale"));
    			ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
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
    			ordine.setAcquirente(rs.getString("acquirente"));
    			ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
    			ordine.setTotale(rs.getBigDecimal("totale"));
    			ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
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
    				+ "(acquirente, dataOrdine, totale, indirizzoFatturazione, idUtente) "
    				+ "VALUES (?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    		ps.setString(1, ordine.getAcquirente());
    		ps.setTimestamp(2, ordine.getDataOrdine());
    		ps.setBigDecimal(3, ordine.getTotale());
    		ps.setString(4, ordine.getIndirizzoFatturazione());
    		ps.setInt(5, ordine.getIdUtente());
    		
    		int result = ps.executeUpdate();
    		
    		ResultSet rs = ps.getGeneratedKeys();

    		if(rs.next()) {
    		    ordine.setIdOrdine(rs.getInt(1));
    		}
    		
    		rs.close();
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
    				+ "SET acquirente=?, dataOrdine=?, totale=?, indirizzoFatturazione=?, idUtente=? "
    				+ "WHERE idOrdine=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, ordine.getAcquirente());
    		ps.setTimestamp(2, ordine.getDataOrdine());
    		ps.setBigDecimal(3, ordine.getTotale());
    		ps.setInt(4, ordine.getIdUtente());
    		ps.setString(5, ordine.getIndirizzoFatturazione());
    		ps.setInt(6, ordine.getIdOrdine());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un ordine
    public boolean doDelete(int idOrdine) {
    	try {
    		String sql = "DELETE FROM ordine WHERE idOrdine=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idOrdine);
    		
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
    		String sql = "SELECT * FROM ordine WHERE idUtente=? ORDER BY dataOrdine DESC";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idUtente);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			OrdineBean ordine = new OrdineBean();
    			
    			ordine.setIdOrdine(rs.getInt("idOrdine"));
    			ordine.setAcquirente(rs.getString("acquirente"));
    			ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
    			ordine.setTotale(rs.getBigDecimal("totale"));
    			ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
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