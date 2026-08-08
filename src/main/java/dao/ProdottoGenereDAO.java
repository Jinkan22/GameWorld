package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.GenereBean;
import model.ProdottoGenereBean;
import utils.DBConnection;

public class ProdottoGenereDAO {
	private Connection connection;

    public ProdottoGenereDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di una relazione prodottoGenere
    public ProdottoGenereBean doRetrieveByKey (int idProdotto, int idGenere){
    	ProdottoGenereBean prodottoGenere = null;
    	
    	try {
    		String sql = "SELECT * FROM prodottoGenere WHERE idProdotto=? AND idGenere=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		ps.setInt(2, idGenere);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			prodottoGenere = new ProdottoGenereBean();
    			
    			prodottoGenere.setIdProdotto(rs.getInt("idProdotto"));
    			prodottoGenere.setIdGenere(rs.getInt("idGenere"));    			
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return prodottoGenere;
    }
    
    //salvataggio di una relazione prodottoGenere
    public boolean doSave(ProdottoGenereBean prodottoGenere) {
    	
    	try {
    		String sql = "INSERT INTO prodottoGenere "
    				+ "(idProdotto, idGenere) "
    				+ "VALUES (?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, prodottoGenere.getIdProdotto());
    		ps.setInt(2, prodottoGenere.getIdGenere());
    		
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
    public boolean doDelete(int idProdotto, int idGenere) {
    	try {
    		String sql = "DELETE FROM prodottoGenere WHERE idProdotto=? AND idGenere=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		ps.setInt(2, idGenere);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura di tutte le relazioni prodottoGenere di un determinato prodotto
    public ArrayList<GenereBean> doRetrieveByIdProdotto(int idProdotto){
    	ArrayList<GenereBean> list = new ArrayList<GenereBean>();
    	
    	try {
    		String sql = "SELECT g.* "
    				+ "FROM genere g "
    				+ "JOIN prodottoGenere pg "
    				+ "ON g.idGenere = pg.idGenere "
    				+ "WHERE pg.idProdotto=? "
    				+ "ORDER BY g.nomeGenere ASC";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			GenereBean genere = new GenereBean();
    			
    			genere.setIdGenere(rs.getInt("idGenere"));
    			genere.setNomeGenere(rs.getString("nomeGenere"));
    			
    			list.add(genere);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //controlla se una relazione prodottoGenere esiste
    public boolean esiste(int idProdotto, int idGenere) {
        try {
        	String sql = "SELECT * FROM prodottoGenere WHERE idProdotto = ? AND idGenere = ? LIMIT 1";
        	
        	PreparedStatement ps = connection.prepareStatement(sql);
        	
            ps.setInt(1, idProdotto);
            ps.setInt(2, idGenere);

            ResultSet rs = ps.executeQuery();
            
            return rs.next();
            
        } catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        }
    }
}
