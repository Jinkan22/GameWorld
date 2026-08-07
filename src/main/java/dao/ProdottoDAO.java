package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProdottoBean;
import utils.DBConnection;

public class ProdottoDAO {
	private Connection connection;

    public ProdottoDAO() {
        connection = DBConnection.getConnection();
    }

    //lettura di un prodotto in base alla chiave primaria
    public ProdottoBean doRetrieveByKey (int idProdotto){
    	ProdottoBean prodotto = null;
    	
    	try {
    		String sql = "SELECT * FROM prodotto WHERE idProdotto = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			prodotto = new ProdottoBean();
    			
    			prodotto.setIdProdotto(rs.getInt("idProdotto"));
    			prodotto.setNome(rs.getString("nome"));
    			prodotto.setDescrizione(rs.getString("descrizione"));
    			prodotto.setPrezzo(rs.getFloat("prezzo"));
    			prodotto.setQuantitaDisponibile(rs.getInt("quantitaDisponibile"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));
    			prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return prodotto;
    }
    
    //lettura di tutti i prodotti
    public ArrayList<ProdottoBean> doRetrieveAll(){
    	ArrayList<ProdottoBean> list = new ArrayList<ProdottoBean>();
    	
    	try {
    		String sql = "SELECT * FROM prodotto";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			ProdottoBean prodotto = new ProdottoBean();
    			
    			prodotto.setIdProdotto(rs.getInt("idProdotto"));
    			prodotto.setNome(rs.getString("nome"));
    			prodotto.setDescrizione(rs.getString("descrizione"));
    			prodotto.setPrezzo(rs.getFloat("prezzo"));
    			prodotto.setQuantitaDisponibile(rs.getInt("quantitaDisponibile"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));
    			prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
    			
    			list.add(prodotto);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un prodotto
    public boolean doSave(ProdottoBean prodotto) {
    	
    	try {
    		String sql = "INSERT INTO prodotto "
    				+ "(nome, descrizione, prezzo, quantitaDisponibile, immagine, dataUscita, sviluppatore, tipoProdotto) "
    				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    		
    		ps.setString(1, prodotto.getNome());
    		ps.setString(2, prodotto.getDescrizione());
    		ps.setFloat(3, prodotto.getPrezzo());
    		ps.setInt(4, prodotto.getQuantitaDisponibile());
    		ps.setString(5, prodotto.getImmagine());
    		ps.setDate(6, prodotto.getDataUscita());
    		ps.setString(7, prodotto.getSviluppatore());
    		ps.setString(8, prodotto.getTipoProdotto());
    		
    		int result = ps.executeUpdate();
    		
    		ResultSet rs = ps.getGeneratedKeys();
    		
    		if(rs.next()) {
    		    prodotto.setIdProdotto(rs.getInt(1));
    		}
    		
    		rs.close();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un prodotto
    public boolean doUpdate(ProdottoBean prodotto) {
    	try {
    		String sql = "UPDATE prodotto "
    				+ "SET nome=?, descrizione=?, prezzo=?, quantitaDisponibile=?, immagine=?, dataUscita=?, sviluppatore=?, tipoProdotto=? "
    				+ "WHERE idProdotto=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, prodotto.getNome());
    		ps.setString(2, prodotto.getDescrizione());
    		ps.setFloat(3, prodotto.getPrezzo());
    		ps.setInt(4, prodotto.getQuantitaDisponibile());
    		ps.setString(5, prodotto.getImmagine());
    		ps.setDate(6, prodotto.getDataUscita());
    		ps.setString(7, prodotto.getSviluppatore());
    		ps.setString(8, prodotto.getTipoProdotto());
    		ps.setInt(9, prodotto.getIdProdotto());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un prodotto
    public boolean doDelete(int idProdotto) {
    	try {
    		String sql = "DELETE FROM prodotto WHERE idProdotto=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idProdotto);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura di tutti i prodotti disponibili
    public ArrayList<ProdottoBean> doRetrieveDisponibili(){
    	ArrayList<ProdottoBean> list = new ArrayList<ProdottoBean>();
    	
    	try {
    		String sql = "SELECT * FROM prodotto WHERE quantitaDisponibile > 0";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			ProdottoBean prodotto = new ProdottoBean();
    			
    			prodotto.setIdProdotto(rs.getInt("idProdotto"));
    			prodotto.setNome(rs.getString("nome"));
    			prodotto.setDescrizione(rs.getString("descrizione"));
    			prodotto.setPrezzo(rs.getFloat("prezzo"));
    			prodotto.setQuantitaDisponibile(rs.getInt("quantitaDisponibile"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));
    			prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
    			
    			list.add(prodotto);
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
