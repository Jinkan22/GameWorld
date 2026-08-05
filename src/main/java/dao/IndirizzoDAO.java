package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.IndirizzoBean;
import utils.DBConnection;

public class IndirizzoDAO {
	private Connection connection;

    public IndirizzoDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di un indirizzo in base alla chiave primaria
    public IndirizzoBean doRetrieveByKey (int idIndirizzo){
    	IndirizzoBean indirizzo = null;
    	
    	try {
    		String sql = "SELECT * FROM indirizzo WHERE idIndirizzo=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idIndirizzo);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			indirizzo = new IndirizzoBean();
    			
    			indirizzo.setIdIndirizzo(rs.getInt("idIndirizzo"));
    			indirizzo.setVia(rs.getString("via"));
    			indirizzo.setCitta(rs.getString("citta"));
    			indirizzo.setCap(rs.getString("cap"));
    			indirizzo.setProvincia(rs.getString("provincia"));
    			indirizzo.setPaese(rs.getString("paese"));
    			indirizzo.setIdUtente(rs.getInt("idUtente"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return indirizzo;
    }
    
    //lettura di tutti gli indirizzi
    public ArrayList<IndirizzoBean> doRetrieveAll(){
    	ArrayList<IndirizzoBean> list = new ArrayList<IndirizzoBean>();
    	
    	try {
    		String sql = "SELECT * FROM indirizzo";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			IndirizzoBean indirizzo = new IndirizzoBean();
    			
    			indirizzo.setIdIndirizzo(rs.getInt("idIndirizzo"));
    			indirizzo.setVia(rs.getString("via"));
    			indirizzo.setCitta(rs.getString("citta"));
    			indirizzo.setCap(rs.getString("cap"));
    			indirizzo.setProvincia(rs.getString("provincia"));
    			indirizzo.setPaese(rs.getString("paese"));
    			indirizzo.setIdUtente(rs.getInt("idUtente"));
    			
    			list.add(indirizzo);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un indirizzo
    public boolean doSave(IndirizzoBean indirizzo) {
    	
    	try {
    		String sql = "INSERT INTO indirizzo "
    				+ "(via, citta, cap, provincia, paese, idUtente) "
    				+ "VALUES (?, ?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, indirizzo.getVia());
    		ps.setString(2, indirizzo.getCitta());
    		ps.setString(3, indirizzo.getCap());
    		ps.setString(4, indirizzo.getProvincia());
    		ps.setString(5, indirizzo.getPaese());
    		ps.setInt(6, indirizzo.getIdUtente());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un indirizzo
    public boolean doUpdate(IndirizzoBean indirizzo) {
    	try {
    		String sql = "UPDATE indirizzo "
    				+ "SET via=?, citta=?, cap=?, provincia=?, paese=?, idUtente=? "
    				+ "WHERE idIndirizzo=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, indirizzo.getVia());
    		ps.setString(2, indirizzo.getCitta());
    		ps.setString(3, indirizzo.getCap());
    		ps.setString(4, indirizzo.getProvincia());
    		ps.setString(5, indirizzo.getPaese());
    		ps.setInt(6, indirizzo.getIdUtente());
    		ps.setInt(7, indirizzo.getIdIndirizzo());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un indirizzo
    public boolean doDelete(int idIndirizzo) {
    	try {
    		String sql = "DELETE FROM indirizzo WHERE idIndirizzo=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idIndirizzo);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
  //lettura di tutti gli indirizzi di un determinato utente
    public ArrayList<IndirizzoBean> doRetrieveByIdUtente(int idUtente){
    	ArrayList<IndirizzoBean> list = new ArrayList<IndirizzoBean>();
    	
    	try {
    		String sql = "SELECT * FROM indirizzo WHERE idUtente=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idUtente);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			IndirizzoBean indirizzo = new IndirizzoBean();
    			
    			indirizzo.setIdIndirizzo(rs.getInt("idIndirizzo"));
    			indirizzo.setVia(rs.getString("via"));
    			indirizzo.setCitta(rs.getString("citta"));
    			indirizzo.setCap(rs.getString("cap"));
    			indirizzo.setProvincia(rs.getString("provincia"));
    			indirizzo.setPaese(rs.getString("paese"));
    			indirizzo.setIdUtente(rs.getInt("idUtente"));
    			
    			list.add(indirizzo);
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
