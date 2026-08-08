package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.GenereBean;
import utils.DBConnection;

public class GenereDAO {
	private Connection connection;

    public GenereDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di un genere in base alla chiave primaria
    public GenereBean doRetrieveByKey (int idGenere){
    	GenereBean genere = null;
    	
    	try {
    		String sql = "SELECT * FROM genere WHERE idGenere = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idGenere);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			genere = new GenereBean();
    			
    			genere.setIdGenere(rs.getInt("idGenere"));
    			genere.setNomeGenere(rs.getString("nomeGenere"));
    			
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return genere;
    }
    
    //lettura di tutti i generi
    public ArrayList<GenereBean> doRetrieveAll(){
    	ArrayList<GenereBean> list = new ArrayList<GenereBean>();
    	
    	try {
    		String sql = "SELECT * FROM genere ORDER BY nomeGenere ASC";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
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
    
    //salvataggio di un genere
    public boolean doSave(GenereBean genere) {
    	
    	try {
    		String sql = "INSERT INTO genere "
    				+ "(nomeGenere) "
    				+ "VALUES (?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, genere.getNomeGenere());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un genere
    public boolean doUpdate(GenereBean genere) {
    	try {
    		String sql = "UPDATE genere "
    				+ "SET nomeGenere=? "
    				+ "WHERE idGenere=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, genere.getNomeGenere());
    		ps.setInt(2, genere.getIdGenere());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un genere
    public boolean doDelete(int idGenere) {
    	try {
    		String sql = "DELETE FROM genere WHERE idGenere=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idGenere);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura di tutti i generi di un determinato prodotto
    public ArrayList<GenereBean> doRetrieveByIdProdotto(int idProdotto){
    	ArrayList<GenereBean> list = new ArrayList<GenereBean>();
    	
    	try {
    		String sql = "SELECT * FROM genere WHERE idProdotto=?";
    		
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
    
    //controlla se il genere è utilizzato
    public boolean isUtilizzato(int idGenere) {
        try {
            String sql = "SELECT * FROM prodottoGenere WHERE idGenere=? LIMIT 1";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idGenere);

            ResultSet rs = ps.executeQuery();

            boolean utilizzata = rs.next();

            rs.close();
            ps.close();

            return utilizzata;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
