package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MetodoPagamentoBean;
import utils.DBConnection;

public class MetodoPagamentoDAO {
	private Connection connection;

    public MetodoPagamentoDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di un metodo di pagamento in base alla chiave primaria
    public MetodoPagamentoBean doRetrieveByKey (int idMetodoPagamento){
    	MetodoPagamentoBean metodoPagamento = null;
    	
    	try {
    		String sql = "SELECT * FROM metodoPagamento WHERE idMetodoPagamento=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idMetodoPagamento);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			metodoPagamento = new MetodoPagamentoBean();
    			
    			metodoPagamento.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
    			metodoPagamento.setNumeroCarta(rs.getString("numeroCarta"));
    			metodoPagamento.setIntestatario(rs.getString("intestatario"));
    			metodoPagamento.setDataScadenza(rs.getDate("dataScadenza"));
    			metodoPagamento.setCircuito(rs.getString("circuito"));
    			metodoPagamento.setIdUtente(rs.getInt("idUtente"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return metodoPagamento;
    }
    
    //lettura di tutti i metodi di pagamento
    public ArrayList<MetodoPagamentoBean> doRetrieveAll(){
    	ArrayList<MetodoPagamentoBean> list = new ArrayList<MetodoPagamentoBean>();
    	
    	try {
    		String sql = "SELECT * FROM metodoPagamento";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			MetodoPagamentoBean metodoPagamento = new MetodoPagamentoBean();
    			
    			metodoPagamento.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
    			metodoPagamento.setNumeroCarta(rs.getString("numeroCarta"));
    			metodoPagamento.setIntestatario(rs.getString("intestatario"));
    			metodoPagamento.setDataScadenza(rs.getDate("dataScadenza"));
    			metodoPagamento.setCircuito(rs.getString("circuito"));
    			metodoPagamento.setIdUtente(rs.getInt("idUtente"));
    			
    			list.add(metodoPagamento);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un metodo di pagamento
    public boolean doSave(MetodoPagamentoBean metodoPagamento) {
    	
    	try {
    		String sql = "INSERT INTO metodoPagamento "
    				+ "(numeroCarta, intestatario, dataScadenza, circuito, idUtente) "
    				+ "VALUES (?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, metodoPagamento.getNumeroCarta());
    		ps.setString(2, metodoPagamento.getIntestatario());
    		ps.setDate(3, metodoPagamento.getDataScadenza());
    		ps.setString(4, metodoPagamento.getCircuito());
    		ps.setInt(5, metodoPagamento.getIdUtente());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un metodo di pagamento
    public boolean doUpdate(MetodoPagamentoBean metodoPagamento) {
    	try {
    		String sql = "UPDATE metodoPagamento "
    				+ "SET numeroCarta=?, intestatario=?, dataScadenza=?, circuito=?, idUtente=? "
    				+ "WHERE idMetodoPagamento=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, metodoPagamento.getNumeroCarta());
    		ps.setString(2, metodoPagamento.getIntestatario());
    		ps.setDate(3, metodoPagamento.getDataScadenza());
    		ps.setString(4, metodoPagamento.getCircuito());
    		ps.setInt(5, metodoPagamento.getIdUtente());
    		ps.setInt(6, metodoPagamento.getIdMetodoPagamento());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un metodo di pagamento
    public boolean doDelete(int idMetodoPagamento) {
    	try {
    		String sql = "DELETE FROM metodoPagamento WHERE idMetodoPagamento=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idMetodoPagamento);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
  //lettura di tutti i metodi di pagamento di un determinato utente
    public ArrayList<MetodoPagamentoBean> doRetrieveByIdUtente(int idUtente){
    	ArrayList<MetodoPagamentoBean> list = new ArrayList<MetodoPagamentoBean>();
    	
    	try {
    		String sql = "SELECT * FROM metodoPagamento WHERE idUtente=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idUtente);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			MetodoPagamentoBean metodoPagamento = new MetodoPagamentoBean();
    			
    			metodoPagamento.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
    			metodoPagamento.setNumeroCarta(rs.getString("numeroCarta"));
    			metodoPagamento.setIntestatario(rs.getString("intestatario"));
    			metodoPagamento.setDataScadenza(rs.getDate("dataScadenza"));
    			metodoPagamento.setCircuito(rs.getString("circuito"));
    			metodoPagamento.setIdUtente(rs.getInt("idUtente"));
    			
    			list.add(metodoPagamento);
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
