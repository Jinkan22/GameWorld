package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DettaglioOrdineBean;
import utils.DBConnection;

public class DettaglioOrdineDAO {	
	public DettaglioOrdineDAO() {
		
	}
	
	//lettura di un dettaglio ordine in base alla chiave primaria
    public DettaglioOrdineBean doRetrieveByKey (int idDettaglioOrdine){
    	DettaglioOrdineBean dettaglioOrdine = null;
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM dettaglioOrdine WHERE idDettaglio=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idDettaglioOrdine);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getBigDecimal("prezzoAcquisto"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			dettaglioOrdine.setNomeProdotto(rs.getString("nomeProdotto"));
    			dettaglioOrdine.setNomePiattaforma(rs.getString("nomePiattaforma"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return dettaglioOrdine;
    }
    
    //lettura di tutti i dettagli ordine
    public ArrayList<DettaglioOrdineBean> doRetrieveAll(){
    	ArrayList<DettaglioOrdineBean> list = new ArrayList<DettaglioOrdineBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM dettaglioOrdine";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getBigDecimal("prezzoAcquisto"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			dettaglioOrdine.setNomeProdotto(rs.getString("nomeProdotto"));
    			dettaglioOrdine.setNomePiattaforma(rs.getString("nomePiattaforma"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    			
    			list.add(dettaglioOrdine);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un dettaglio ordine
    public boolean doSave(DettaglioOrdineBean dettaglioOrdine) {
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "INSERT INTO dettaglioOrdine "
    				+ "(quantita, prezzoAcquisto, idProdotto, nomeProdotto, nomePiattaforma, idOrdine) "
    				+ "VALUES (?, ?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, dettaglioOrdine.getQuantita());
    		ps.setBigDecimal(2, dettaglioOrdine.getPrezzoAcquisto());
    		ps.setInt(3, dettaglioOrdine.getIdProdotto());
    		ps.setString(4,  dettaglioOrdine.getNomeProdotto());
    		ps.setString(5, dettaglioOrdine.getNomePiattaforma());
    		ps.setInt(6, dettaglioOrdine.getIdOrdine());
    		
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un dettaglio ordine
    public boolean doDelete(int idDettaglioOrdine) {
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "DELETE FROM dettaglioOrdine WHERE idDettaglio=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idDettaglioOrdine);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    //lettura di tutti i dettagli ordine di un ordine
	public ArrayList<DettaglioOrdineBean> doRetrieveByIdOrdine(int idOrdine) {
		ArrayList<DettaglioOrdineBean> list = new ArrayList<DettaglioOrdineBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM dettaglioOrdine WHERE idOrdine=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idOrdine);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getBigDecimal("prezzoAcquisto"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			dettaglioOrdine.setNomeProdotto(rs.getString("nomeProdotto"));
    			dettaglioOrdine.setNomePiattaforma(rs.getString("nomePiattaforma"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    			
    			list.add(dettaglioOrdine);
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
