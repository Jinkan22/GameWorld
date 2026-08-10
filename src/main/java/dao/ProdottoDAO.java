package dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OffertaBean;
import model.ProdottoBean;
import model.ProdottoViewBean;
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
    			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));
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
    			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));
    			
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
    				+ "(nome, descrizione, prezzo, immagine, dataUscita, sviluppatore) "
    				+ "VALUES (?, ?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    		
    		ps.setString(1, prodotto.getNome());
    		ps.setString(2, prodotto.getDescrizione());
    		ps.setBigDecimal(3, prodotto.getPrezzo());
    		ps.setString(4, prodotto.getImmagine());
    		ps.setDate(5, prodotto.getDataUscita());
    		ps.setString(6, prodotto.getSviluppatore());
    		
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
    				+ "SET nome=?, descrizione=?, prezzo=?, immagine=?, dataUscita=?, sviluppatore=? "
    				+ "WHERE idProdotto=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, prodotto.getNome());
    		ps.setString(2, prodotto.getDescrizione());
    		ps.setBigDecimal(3, prodotto.getPrezzo());
    		ps.setString(4, prodotto.getImmagine());
    		ps.setDate(5, prodotto.getDataUscita());
    		ps.setString(6, prodotto.getSviluppatore());
    		ps.setInt(7, prodotto.getIdProdotto());
    		
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
    			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));
    			
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
    
    //lettura di un prodotto view in base alla chiave primaria
    public ProdottoViewBean doRetrieveViewByKey(int idProdotto) {
    	ProdottoViewBean prodottoView = null;

    	try {
    		String sql = "SELECT * FROM prodotto WHERE idProdotto = ?";

    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);

    		ResultSet rs = ps.executeQuery();

    		if(rs.next()) {
    			ProdottoBean prodotto = new ProdottoBean();

    			prodotto.setIdProdotto(rs.getInt("idProdotto"));
    			prodotto.setNome(rs.getString("nome"));
    			prodotto.setDescrizione(rs.getString("descrizione"));
    			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));

    			prodottoView = new ProdottoViewBean();
    			prodottoView.setProdotto(prodotto);

    			GenereDAO genereDAO = new GenereDAO();
    			PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
    			ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
    			OffertaDAO offertaDAO = new OffertaDAO();

    			prodottoView.setGeneri(genereDAO.doRetrieveByIdProdotto(idProdotto));
    			prodottoView.setPiattaforme(piattaformaDAO.doRetrieveByIdProdotto(idProdotto));
    			prodottoView.setProdottoPiattaforme(prodottoPiattaformaDAO.doRetrieveByIdProdotto(idProdotto));

    			OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(idProdotto);
    			prodottoView.setOfferta(offerta);

    			if(offerta != null) {
    				prodottoView.setPrezzoScontato(prodotto.getPrezzo().multiply(
    						BigDecimal.ONE.subtract(
    						BigDecimal.valueOf(offerta.getPercentualeSconto()).
    						divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP));
    			}
    		}

    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return prodottoView;
    }
    
    //lettura di un prodotto view in base alla chiave primaria con le piattaforme disponibili
    public ProdottoViewBean doRetrieveViewDisponibileByKey(int idProdotto) {
    	ProdottoViewBean prodottoView = null;

    	try {
    		String sql = "SELECT * FROM prodotto WHERE idProdotto = ?";

    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idProdotto);

    		ResultSet rs = ps.executeQuery();

    		if(rs.next()) {
    			ProdottoBean prodotto = new ProdottoBean();

    			prodotto.setIdProdotto(rs.getInt("idProdotto"));
    			prodotto.setNome(rs.getString("nome"));
    			prodotto.setDescrizione(rs.getString("descrizione"));
    			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));

    			prodottoView = new ProdottoViewBean();
    			prodottoView.setProdotto(prodotto);

    			GenereDAO genereDAO = new GenereDAO();
    			PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
    			ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
    			OffertaDAO offertaDAO = new OffertaDAO();

    			prodottoView.setGeneri(genereDAO.doRetrieveByIdProdotto(idProdotto));
    			prodottoView.setPiattaforme(piattaformaDAO.doRetrieveDisponibiliByIdProdotto(idProdotto));
    			prodottoView.setProdottoPiattaforme(prodottoPiattaformaDAO.doRetrieveDisponibiliByIdProdotto(idProdotto));

    			OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(idProdotto);
    			prodottoView.setOfferta(offerta);

    			if(offerta != null) {
    				prodottoView.setPrezzoScontato(prodotto.getPrezzo().multiply(
    						BigDecimal.ONE.subtract(
    						BigDecimal.valueOf(offerta.getPercentualeSconto()).
    						divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP));
    			}
    		}

    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return prodottoView;
    }
    
    //lettura di tutti i prodotti view
    public ArrayList<ProdottoViewBean> doRetrieveAllView() {
    	ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

    	try {
    		String sql = "SELECT * FROM prodotto ORDER BY nome ASC";

    		PreparedStatement ps = connection.prepareStatement(sql);

    		ResultSet rs = ps.executeQuery();

    		GenereDAO genereDAO = new GenereDAO();
    		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
    		ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
    		OffertaDAO offertaDAO = new OffertaDAO();

    		while(rs.next()) {
    			ProdottoBean prodotto = new ProdottoBean();

    			prodotto.setIdProdotto(rs.getInt("idProdotto"));
    			prodotto.setNome(rs.getString("nome"));
    			prodotto.setDescrizione(rs.getString("descrizione"));
    			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));

    			ProdottoViewBean prodottoView = new ProdottoViewBean();
    			prodottoView.setProdotto(prodotto);

    			prodottoView.setGeneri(genereDAO.doRetrieveByIdProdotto(prodotto.getIdProdotto()));
    			prodottoView.setPiattaforme(piattaformaDAO.doRetrieveDisponibiliByIdProdotto(prodotto.getIdProdotto()));
    			prodottoView.setProdottoPiattaforme(prodottoPiattaformaDAO.doRetrieveDisponibiliByIdProdotto(prodotto.getIdProdotto()));

    			OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(prodotto.getIdProdotto());
    			prodottoView.setOfferta(offerta);

    			if(offerta != null) {
    				prodottoView.setPrezzoScontato(prodotto.getPrezzo().multiply(
    						BigDecimal.ONE.subtract(
    						BigDecimal.valueOf(offerta.getPercentualeSconto()).
    						divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP));
    			}

    			list.add(prodottoView);
    		}

    		rs.close();
    		ps.close();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}

    	return list;
    }
    
    //lettura di tutti i prodotti view disponibili
    public ArrayList<ProdottoViewBean> doRetrieveViewDisponibili() {
    	ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

    	try {
    		String sql = "SELECT * FROM prodotto "
    				+ "WHERE EXISTS ("
    				+ "SELECT 1 FROM prodottoPiattaforma pp "
    				+ "WHERE pp.idProdotto = prodotto.idProdotto "
    				+ "AND pp.quantitaDisponibile > 0) "
    				+ "ORDER BY nome ASC";

    		PreparedStatement ps = connection.prepareStatement(sql);

    		ResultSet rs = ps.executeQuery();

    		GenereDAO genereDAO = new GenereDAO();
    		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
    		ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
    		OffertaDAO offertaDAO = new OffertaDAO();

    		while(rs.next()) {
    			ProdottoBean prodotto = new ProdottoBean();

    			prodotto.setIdProdotto(rs.getInt("idProdotto"));
    			prodotto.setNome(rs.getString("nome"));
    			prodotto.setDescrizione(rs.getString("descrizione"));
    			prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
    			prodotto.setImmagine(rs.getString("immagine"));
    			prodotto.setDataUscita(rs.getDate("dataUscita"));
    			prodotto.setSviluppatore(rs.getString("sviluppatore"));

    			ProdottoViewBean prodottoView = new ProdottoViewBean();
    			prodottoView.setProdotto(prodotto);

    			prodottoView.setGeneri(genereDAO.doRetrieveByIdProdotto(prodotto.getIdProdotto()));
    			prodottoView.setPiattaforme(piattaformaDAO.doRetrieveDisponibiliByIdProdotto(prodotto.getIdProdotto()));
    			prodottoView.setProdottoPiattaforme(prodottoPiattaformaDAO.doRetrieveDisponibiliByIdProdotto(prodotto.getIdProdotto()));

    			OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(prodotto.getIdProdotto());
    			prodottoView.setOfferta(offerta);

    			if(offerta != null) {
    				prodottoView.setPrezzoScontato(prodotto.getPrezzo().multiply(
    						BigDecimal.ONE.subtract(
    						BigDecimal.valueOf(offerta.getPercentualeSconto()).
    						divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP));
    			}

    			list.add(prodottoView);
    		}

    		rs.close();
    		ps.close();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}

    	return list;
    }
}
