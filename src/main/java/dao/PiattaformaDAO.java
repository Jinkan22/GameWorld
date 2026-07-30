package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PiattaformaBean;
import utils.DBConnection;

public class PiattaformaDAO {
	private Connection connection;

    public PiattaformaDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di una piattaforma in base alla chiave primaria
    public PiattaformaBean doRetrieveByKey (int idPiattaforma){
    	PiattaformaBean piattaforma = null;
    	
    	try {
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
    	
    	try {
    		String sql = "SELECT * FROM piattaforma";
    		
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
    	
    	try {
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
    	try {
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
}
