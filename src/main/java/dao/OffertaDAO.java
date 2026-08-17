package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OffertaBean;
import utils.DBConnection;

public class OffertaDAO {
    public OffertaDAO() {
    	
    }
    
    //lettura di un'offerta in base alla chiave primaria
    public OffertaBean doRetrieveByKey (int idOfferta){
    	OffertaBean offerta = null;
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM offerta WHERE idOfferta=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idOfferta);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			offerta = new OffertaBean();
    			
    			offerta.setIdOfferta(rs.getInt("idOfferta"));
    			offerta.setPercentualeSconto(rs.getInt("percentualeSconto"));
    			offerta.setDataInizio(rs.getDate("dataInizio"));
    			offerta.setDataFine(rs.getDate("dataFine"));
    			offerta.setIdProdotto(rs.getInt("idProdotto"));
    			
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return offerta;
    }
    
    //lettura di tutte le offerte
    public ArrayList<OffertaBean> doRetrieveAll(){
    	ArrayList<OffertaBean> list = new ArrayList<OffertaBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM offerta";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			OffertaBean offerta = new OffertaBean();
    			
    			offerta.setIdOfferta(rs.getInt("idOfferta"));
    			offerta.setPercentualeSconto(rs.getInt("percentualeSconto"));
    			offerta.setDataInizio(rs.getDate("dataInizio"));
    			offerta.setDataFine(rs.getDate("dataFine"));
    			offerta.setIdProdotto(rs.getInt("idProdotto"));
    			
    			list.add(offerta);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un'offerta
    public boolean doSave(OffertaBean offerta) {
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "INSERT INTO offerta "
    				+ "(percentualeSconto, dataInizio, dataFine, idProdotto) "
    				+ "VALUES (?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, offerta.getPercentualeSconto());
    		ps.setDate(2, offerta.getDataInizio());
    		ps.setDate(3, offerta.getDataFine());
    		ps.setInt(4, offerta.getIdProdotto());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un'offerta
    public boolean doUpdate(OffertaBean offerta) {
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "UPDATE offerta "
    				+ "SET percentualeSconto=?, dataInizio=?, dataFine=?, idProdotto=? "
    				+ "WHERE idOfferta=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, offerta.getPercentualeSconto());
    		ps.setDate(2, offerta.getDataInizio());
    		ps.setDate(3, offerta.getDataFine());
    		ps.setInt(4, offerta.getIdProdotto());
    		ps.setInt(5, offerta.getIdOfferta());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un'offerta
    public boolean doDelete(int idOfferta) {
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "DELETE FROM offerta WHERE idOfferta=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idOfferta);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura di tutte le offerte di un determinato prodotto
    public ArrayList<OffertaBean> doRetrieveByIdProdotto(int idProdotto){
    	ArrayList<OffertaBean> list = new ArrayList<OffertaBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM offerta WHERE idProdotto=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			OffertaBean offerta = new OffertaBean();
    			
    			offerta.setIdOfferta(rs.getInt("idOfferta"));
    			offerta.setPercentualeSconto(rs.getInt("percentualeSconto"));
    			offerta.setDataInizio(rs.getDate("dataInizio"));
    			offerta.setDataFine(rs.getDate("dataFine"));
    			offerta.setIdProdotto(rs.getInt("idProdotto"));
    			
    			list.add(offerta);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //controlla se esistono offerte sovrapposte
    public boolean esisteOffertaSovrapposta(int idProdotto, Date dataInizio, Date dataFine) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM offerta WHERE idProdotto=? AND dataInizio<=? AND dataFine>=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idProdotto);
            ps.setDate(2, dataFine);
            ps.setDate(3, dataInizio);

            ResultSet rs = ps.executeQuery();

            boolean esiste = rs.next();

            rs.close();
            ps.close();

            return esiste;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //lettura di un'offerta ATTIVA di un determinato prodotto
    public OffertaBean doRetrieveAttivaByIdProdotto (int idProdotto){
    	OffertaBean offerta = null;
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM offerta WHERE idProdotto=? "
    				+ "AND dataInizio<=CURDATE() AND dataFine>=CURDATE()"
    				+ "LIMIT 1";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			offerta = new OffertaBean();
    			
    			offerta.setIdOfferta(rs.getInt("idOfferta"));
    			offerta.setPercentualeSconto(rs.getInt("percentualeSconto"));
    			offerta.setDataInizio(rs.getDate("dataInizio"));
    			offerta.setDataFine(rs.getDate("dataFine"));
    			offerta.setIdProdotto(rs.getInt("idProdotto"));
    			
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return offerta;
    }
}