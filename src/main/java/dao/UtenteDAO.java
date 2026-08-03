package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UtenteBean;
import utils.DBConnection;

public class UtenteDAO {
	private Connection connection;

    public UtenteDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di un utente in base alla chiave primaria
    public UtenteBean doRetrieveByKey (int idUtente){
    	UtenteBean utente = null;
    	
    	try {
    		String sql = "SELECT * FROM utente WHERE idUtente=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idUtente);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			utente = new UtenteBean();
    			
    			utente.setIdUtente(rs.getInt("idUtente"));
    			utente.setNome(rs.getString("nome"));
    			utente.setCognome(rs.getString("cognome"));
    			utente.setEmail(rs.getString("email"));
    			utente.setPassword(rs.getString("password"));
    			utente.setIndirizzo(rs.getString("indirizzo"));
    			utente.setMetodoPagamento(rs.getString("metodoPagamento"));
    			utente.setRuolo(rs.getString("ruolo"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return utente;
    }
    
    //lettura di tutti gli utenti
    public ArrayList<UtenteBean> doRetrieveAll(){
    	ArrayList<UtenteBean> list = new ArrayList<UtenteBean>();
    	
    	try {
    		String sql = "SELECT * FROM utente";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			UtenteBean utente = new UtenteBean();
    			
    			utente.setIdUtente(rs.getInt("idUtente"));
    			utente.setNome(rs.getString("nome"));
    			utente.setCognome(rs.getString("cognome"));
    			utente.setEmail(rs.getString("email"));
    			utente.setPassword(rs.getString("password"));
    			utente.setIndirizzo(rs.getString("indirizzo"));
    			utente.setMetodoPagamento(rs.getString("metodoPagamento"));
    			utente.setRuolo(rs.getString("ruolo"));
    			
    			list.add(utente);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un utente
    public boolean doSave(UtenteBean utente) {
    	
    	try {
    		String sql = "INSERT INTO utente "
    				+ "(nome, cognome, email, password, indirizzo, metodoPagamento, ruolo) "
    				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, utente.getNome());
    		ps.setString(2, utente.getCognome());
    		ps.setString(3, utente.getEmail());
    		ps.setString(4, utente.getPassword());
    		ps.setString(5, utente.getIndirizzo());
    		ps.setString(6, utente.getMetodoPagamento());
    		ps.setString(7, utente.getRuolo());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un utente
    public boolean doUpdate(UtenteBean utente) {
    	try {
    		String sql = "UPDATE utente "
    				+ "SET nome=?, cognome=?, email=?, password=?, indirizzo=?, metodoPagamento=?, ruolo=? "
    				+ "WHERE idUtente=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, utente.getNome());
    		ps.setString(2, utente.getCognome());
    		ps.setString(3, utente.getEmail());
    		ps.setString(4, utente.getPassword());
    		ps.setString(5, utente.getIndirizzo());
    		ps.setString(6, utente.getMetodoPagamento());
    		ps.setString(7, utente.getRuolo());
    		ps.setInt(8, utente.getIdUtente());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un utente
    public boolean doDelete(int idUtente) {
    	try {
    		String sql = "DELETE FROM utente WHERE idUtente=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idUtente);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public UtenteBean doRetrieveByEmailAndPassword(String email, String password) {
    	UtenteBean utente = null;
    	
    	try {
    		String sql = "SELECT * FROM utente WHERE email=? AND password=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setString(1, email);
    		ps.setString(2, password);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			utente = new UtenteBean();
    			
    			utente.setIdUtente(rs.getInt("idUtente"));
    			utente.setNome(rs.getString("nome"));
    			utente.setCognome(rs.getString("cognome"));
    			utente.setEmail(rs.getString("email"));
    			utente.setPassword(rs.getString("password"));
    			utente.setIndirizzo(rs.getString("indirizzo"));
    			utente.setMetodoPagamento(rs.getString("metodoPagamento"));
    			utente.setRuolo(rs.getString("ruolo"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return utente;
    }
}
