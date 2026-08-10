package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProdottoPiattaformaBean;
import utils.DBConnection;

public class ProdottoPiattaformaDAO {
	private Connection connection;

    public ProdottoPiattaformaDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di una relazione prodottoPiattaforma
    public ProdottoPiattaformaBean doRetrieveByKey (int idProdotto, int idPiattaforma){
    	ProdottoPiattaformaBean prodottoPiattaforma = null;
    	
    	try {
    		String sql = "SELECT * FROM prodottoPiattaforma WHERE idProdotto=? AND idPiattaforma=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		ps.setInt(2, idPiattaforma);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			prodottoPiattaforma = new ProdottoPiattaformaBean();
    			
    			prodottoPiattaforma.setIdProdotto(rs.getInt("idProdotto"));
    			prodottoPiattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));  
    			prodottoPiattaforma.setQuantitaDisponibile(rs.getInt("quantitaDisponibile"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return prodottoPiattaforma;
    }
    
    //salvataggio di una relazione prodottoPiattaforma
    public boolean doSave(ProdottoPiattaformaBean prodottoPiattaforma) {
    	
    	try {
    		String sql = "INSERT INTO prodottoPiattaforma "
    				+ "(idProdotto, idPiattaforma, quantitaDisponibile) "
    				+ "VALUES (?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, prodottoPiattaforma.getIdProdotto());
    		ps.setInt(2, prodottoPiattaforma.getIdPiattaforma());
    		ps.setInt(3, prodottoPiattaforma.getQuantitaDisponibile());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di una relazione prodottoPiattaforma
    public boolean doUpdate(ProdottoPiattaformaBean prodottoPiattaforma) {
    	try {
    		String sql = "UPDATE prodottoPiattaforma "
    				+ "SET quantitaDisponibile=? "
    				+ "WHERE idProdotto=? AND idPiattaforma=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, prodottoPiattaforma.getQuantitaDisponibile());
    		ps.setInt(2, prodottoPiattaforma.getIdProdotto());
    		ps.setInt(3, prodottoPiattaforma.getIdPiattaforma());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di una relazione prodottoPiattaforma
    public boolean doDelete(int idProdotto, int idPiattaforma) {
    	try {
    		String sql = "DELETE FROM prodottoPiattaforma WHERE idProdotto=? AND idPiattaforma=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		ps.setInt(2, idPiattaforma);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura di tutte le piattaforme di un determinato prodotto
    public ArrayList<ProdottoPiattaformaBean> doRetrieveByIdProdotto(int idProdotto){
    	ArrayList<ProdottoPiattaformaBean> list = new ArrayList<ProdottoPiattaformaBean>();
    	
    	try {
    		String sql = "SELECT pp.* "
    				+ "FROM piattaforma p "
    				+ "JOIN prodottoPiattaforma pp "
    				+ "ON p.idPiattaforma = pp.idPiattaforma "
    				+ "WHERE pp.idProdotto=? "
    				+ "ORDER BY p.nomePiattaforma ASC";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			ProdottoPiattaformaBean prodottoPiattaforma = new ProdottoPiattaformaBean();
    			
    			prodottoPiattaforma.setIdProdotto(rs.getInt("idProdotto"));
    			prodottoPiattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));  
    			prodottoPiattaforma.setQuantitaDisponibile(rs.getInt("quantitaDisponibile"));
    			
    			list.add(prodottoPiattaforma);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //lettura di tutte le piattaforme di un determinato prodotto che siano disponibili all'acquistp
    public ArrayList<ProdottoPiattaformaBean> doRetrieveDisponibiliByIdProdotto(int idProdotto){
    	ArrayList<ProdottoPiattaformaBean> list = new ArrayList<ProdottoPiattaformaBean>();
    	
    	try {
    		String sql = "SELECT pp.* "
    				+ "FROM piattaforma p "
    				+ "JOIN prodottoPiattaforma pp "
    				+ "ON p.idPiattaforma = pp.idPiattaforma "
    				+ "WHERE pp.idProdotto=? "
    				+ "AND pp.quantitaDisponibile > 0 "
    				+ "ORDER BY p.nomePiattaforma ASC";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			ProdottoPiattaformaBean prodottoPiattaforma = new ProdottoPiattaformaBean();
    			
    			prodottoPiattaforma.setIdProdotto(rs.getInt("idProdotto"));
    			prodottoPiattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));  
    			prodottoPiattaforma.setQuantitaDisponibile(rs.getInt("quantitaDisponibile"));
    			
    			list.add(prodottoPiattaforma);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //controlla se una relazione prodottoPiattaforma esiste
    public boolean esiste(int idProdotto, int idPiattaforma) {
        try {
        	String sql = "SELECT * FROM prodottoPiattaforma WHERE idProdotto = ? AND idPiattaforma = ? LIMIT 1";
        	
        	PreparedStatement ps = connection.prepareStatement(sql);
        	
            ps.setInt(1, idProdotto);
            ps.setInt(2, idPiattaforma);

            ResultSet rs = ps.executeQuery();
            
            return rs.next();
            
        } catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        }
    }
}
