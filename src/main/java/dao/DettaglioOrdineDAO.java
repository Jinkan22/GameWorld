package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DettaglioOrdineBean;
import utils.DBConnection;

public class DettaglioOrdineDAO {
private Connection connection;
	
	public DettaglioOrdineDAO() {
		connection = DBConnection.getConnection();
	}
	
	//lettura di un dettaglio ordine in base alla chiave primaria
    public DettaglioOrdineBean doRetrieveByKey (int idDettaglioOrdine){
    	DettaglioOrdineBean dettaglioOrdine = null;
    	
    	try {
    		String sql = "SELECT * FROM dettaglioOrdine WHERE idDettaglio=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idDettaglioOrdine);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			
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
    	
    	try {
    		String sql = "SELECT * FROM dettaglioOrdine";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			
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
    	
    	try {
    		String sql = "INSERT INTO dettaglioOrdine "
    				+ "(quantita, prezzoAcquisto, idOrdine, idProdotto) "
    				+ "VALUES (?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, dettaglioOrdine.getQuantita());
    		ps.setFloat(2, dettaglioOrdine.getPrezzoAcquisto());
    		ps.setInt(3, dettaglioOrdine.getIdOrdine());
    		ps.setInt(4,  dettaglioOrdine.getIdProdotto());
    		
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
