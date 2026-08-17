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
import utils.OrdinamentoProdotti;

public class ProdottoDAO {
    public ProdottoDAO() {
    	
    }

    //lettura di un prodotto in base alla chiave primaria
    public ProdottoBean doRetrieveByKey (int idProdotto){
    	ProdottoBean prodotto = null;
    	
    	try (Connection connection = DBConnection.getConnection()) {
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
    	
    	try (Connection connection = DBConnection.getConnection()) {
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
    	
    	try (Connection connection = DBConnection.getConnection()) {
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
    	try (Connection connection = DBConnection.getConnection()) {
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
    	try (Connection connection = DBConnection.getConnection()) {
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
    	
    	try (Connection connection = DBConnection.getConnection()) {
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

    	try (Connection connection = DBConnection.getConnection()) {
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
    						BigDecimal.ONE.subtract(BigDecimal.valueOf(offerta.getPercentualeSconto()).
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

    	try (Connection connection = DBConnection.getConnection()) {
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
    						BigDecimal.ONE.subtract(BigDecimal.valueOf(offerta.getPercentualeSconto()).
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

    	try (Connection connection = DBConnection.getConnection()) {
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
    						BigDecimal.ONE.subtract(BigDecimal.valueOf(offerta.getPercentualeSconto()).
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

    	try (Connection connection = DBConnection.getConnection()) {
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
    						BigDecimal.ONE.subtract(BigDecimal.valueOf(offerta.getPercentualeSconto()).
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
    
    // lettura dei prodotti view disponibili tramite ricerca, filtri e ordinamento
    public ArrayList<ProdottoViewBean> doRetrieveViewByRicercaFiltriOrdinamento(
    		String ricerca,
    		ArrayList<Integer> idPiattaforme,
    		ArrayList<Integer> idGeneri,
    		OrdinamentoProdotti ordinamento,
    		int limite) {

    	ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

    	try (Connection connection = DBConnection.getConnection()) {

    		String sql = "SELECT p.* ";

    		// Più venduti
    		if(ordinamento == OrdinamentoProdotti.PIU_VENDUTI) {
    			sql += ", SUM(d.quantita) AS totaleVenduto ";
    		}

    		sql += "FROM prodotto p ";

    		// Migliori offerte
    		if(ordinamento == OrdinamentoProdotti.MIGLIORI_OFFERTE) {
    			sql += "JOIN offerta o "
    					+ "ON p.idProdotto = o.idProdotto ";
    		}

    		// Più venduti
    		if(ordinamento == OrdinamentoProdotti.PIU_VENDUTI) {
    			sql += "JOIN dettaglioOrdine d "
    					+ "ON p.idProdotto = d.idProdotto "
    					+ "JOIN ordine ord "
    					+ "ON d.idOrdine = ord.idOrdine ";
    		}

    		sql += "WHERE EXISTS ("
    				+ "SELECT 1 "
    				+ "FROM prodottoPiattaforma pp "
    				+ "WHERE pp.idProdotto = p.idProdotto "
    				+ "AND pp.quantitaDisponibile > 0) ";

    		// Ricerca testuale
    		if(ricerca != null && !ricerca.trim().isEmpty()) {

    			sql += "AND (p.nome LIKE ? "
    					+ "OR p.sviluppatore LIKE ? "
    					+ "OR p.descrizione LIKE ?) ";
    		}

    		// Filtro piattaforme
    		if(idPiattaforme != null && !idPiattaforme.isEmpty()) {

    			sql += "AND EXISTS ("
    					+ "SELECT 1 "
    					+ "FROM prodottoPiattaforma pp2 "
    					+ "WHERE pp2.idProdotto = p.idProdotto "
    					+ "AND pp2.idPiattaforma IN (";

    			for(int i = 0; i < idPiattaforme.size(); i++) {

    				if(i > 0)
    					sql += ", ";

    				sql += "?";
    			}

    			sql += ")) ";
    		}

    		// Filtro generi
    		if(idGeneri != null && !idGeneri.isEmpty()) {

    			sql += "AND EXISTS ("
    					+ "SELECT 1 "
    					+ "FROM prodottoGenere pg "
    					+ "WHERE pg.idProdotto = p.idProdotto "
    					+ "AND pg.idGenere IN (";

    			for(int i = 0; i < idGeneri.size(); i++) {

    				if(i > 0)
    					sql += ", ";

    				sql += "?";
    			}

    			sql += ")) ";
    		}

    		// Filtro nuove uscite
    		
    		if(ordinamento == OrdinamentoProdotti.NUOVE_USCITE) {
    			sql += "AND p.dataUscita <= CURDATE() "
    					+ "AND p.dataUscita >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) ";
    		}

    		// Filtro offerte attive
    		if(ordinamento == OrdinamentoProdotti.MIGLIORI_OFFERTE) {
    			
    			sql += "AND o.dataInizio <= CURDATE() "
    					+ "AND o.dataFine >= CURDATE() ";
    		}

    		// Raggruppamento per i più venduti
    		if(ordinamento == OrdinamentoProdotti.PIU_VENDUTI) {
    			
    			sql += "GROUP BY p.idProdotto ";
    		}

    		// Ordinamento
    		switch(ordinamento) {
    		case NOME:
    			sql += "ORDER BY p.nome ASC ";
    			break;
    		case NUOVE_USCITE:
    			sql += "ORDER BY p.dataUscita DESC ";
    			break;
    		case MIGLIORI_OFFERTE:
    			sql += "ORDER BY o.percentualeSconto DESC ";
    			break;
    		case PIU_VENDUTI:
    			sql += "ORDER BY totaleVenduto DESC ";
    			break;
    		case CASUALE:
    			sql += "ORDER BY RAND() ";
    			break;
    		}

    		// Limite
    		if(limite > 0) {
    			sql += "LIMIT ? ";
    		}

    		PreparedStatement ps = connection.prepareStatement(sql);

    		int parametro = 1;

    		// Parametri ricerca
    		if(ricerca != null && !ricerca.trim().isEmpty()) {
    			ps.setString(parametro++, "%" + ricerca.trim() + "%");
    			ps.setString(parametro++, "%" + ricerca.trim() + "%");
    			ps.setString(parametro++, "%" + ricerca.trim() + "%");
    		}

    		// Parametri piattaforme
    		if(idPiattaforme != null && !idPiattaforme.isEmpty()) {
    			for(Integer idPiattaforma : idPiattaforme) {
    				ps.setInt(parametro++, idPiattaforma);
    			}
    		}

    		// Parametri generi
    		if(idGeneri != null && !idGeneri.isEmpty()) {
    			for(Integer idGenere : idGeneri) {
    				ps.setInt(parametro++, idGenere);
    			}
    		}

    		// Limite
    		if(limite > 0) {
    			ps.setInt(parametro++, limite);
    		}

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
    						BigDecimal.ONE.subtract(BigDecimal.valueOf(offerta.getPercentualeSconto()).
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