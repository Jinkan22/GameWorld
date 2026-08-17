package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PiattaformaBean;
import utils.DBConnection;

public class PiattaformaDAO {
    public PiattaformaDAO() {
    	
    }
    
    //lettura di una piattaforma in base alla chiave primaria
    public PiattaformaBean doRetrieveByKey (int idPiattaforma){
    	PiattaformaBean piattaforma = null;
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM piattaforma WHERE idPiattaforma = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idPiattaforma);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			piattaforma = new PiattaformaBean();
    			
    			piattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			piattaforma.setNomePiattaforma(rs.getString("nomePiattaforma"));
    			
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return piattaforma;
    }
    
    //lettura di tutte le piattaforme
    public ArrayList<PiattaformaBean> doRetrieveAll(){
    	ArrayList<PiattaformaBean> list = new ArrayList<PiattaformaBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM piattaforma ORDER BY nomePiattaforma ASC";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			PiattaformaBean piattaforma = new PiattaformaBean();
    			
    			piattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			piattaforma.setNomePiattaforma(rs.getString("nomePiattaforma"));
    			
    			list.add(piattaforma);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di una piattaforma
    public boolean doSave(PiattaformaBean piattaforma) {
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "INSERT INTO piattaforma "
    				+ "(nomePiattaforma) "
    				+ "VALUES (?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, piattaforma.getNomePiattaforma());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di una piattaforma
    public boolean doUpdate(PiattaformaBean piattaforma) {
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "UPDATE piattaforma "
    				+ "SET nomePiattaforma=? "
    				+ "WHERE idPiattaforma=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, piattaforma.getNomePiattaforma());
    		ps.setInt(2, piattaforma.getIdPiattaforma());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di una piattaforma
    public boolean doDelete(int idPiattaforma) {
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "DELETE FROM piattaforma WHERE idPiattaforma=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idPiattaforma);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //controlla se la piattaforma è utilizzata
    public boolean isUtilizzata(int idPiattaforma) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM prodottoPiattaforma WHERE idPiattaforma=? LIMIT 1";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idPiattaforma);

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
    
    //lettura di tutte le piattaforme di un determinato prodotto
    public ArrayList<PiattaformaBean> doRetrieveByIdProdotto(int idProdotto) {
    	ArrayList<PiattaformaBean> list = new ArrayList<PiattaformaBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT p.* "
    				+ "FROM piattaforma p "
    				+ "JOIN prodottoPiattaforma pp "
    				+ "ON p.idPiattaforma = pp.idPiattaforma "
    				+ "WHERE pp.idProdotto=? "
    				+ "ORDER BY p.nomePiattaforma ASC";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			PiattaformaBean piattaforma = new PiattaformaBean();
    			
    			piattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			piattaforma.setNomePiattaforma(rs.getString("nomePiattaforma"));
    			
    			list.add(piattaforma);
    		}
    		
    		rs.close();
    		ps.close();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //lettura di tutte le piattaforme di un determinato prodotto che siano disponibili all'acquistp
    public ArrayList<PiattaformaBean> doRetrieveDisponibiliByIdProdotto(int idProdotto){
    	ArrayList<PiattaformaBean> list = new ArrayList<PiattaformaBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT p.* "
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
    			PiattaformaBean piattaforma = new PiattaformaBean();
    			
    			piattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			piattaforma.setNomePiattaforma(rs.getString("nomePiattaforma"));
    			
    			list.add(piattaforma);
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
