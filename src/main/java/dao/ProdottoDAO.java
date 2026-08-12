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
    
    //lettura di tutti i prodotti view disponibili tramite ricerca e filtri
    public ArrayList<ProdottoViewBean> doRetrieveViewDisponibiliByRicercaFiltri(String ricerca, ArrayList<Integer> idPiattaforme, ArrayList<Integer> idGeneri) {

        ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

        try {
            String sql = "SELECT * FROM prodotto "
                    + "WHERE EXISTS ("
                    + "SELECT 1 FROM prodottoPiattaforma pp "
                    + "WHERE pp.idProdotto = prodotto.idProdotto "
                    + "AND pp.quantitaDisponibile > 0) ";

            // ricerca testuale
            if(ricerca != null && !ricerca.trim().isEmpty()) {
                sql += "AND (nome LIKE ? "
                        + "OR sviluppatore LIKE ? "
                        + "OR descrizione LIKE ?) ";
            }

            // filtro piattaforme
            if(idPiattaforme != null && !idPiattaforme.isEmpty()) {

                sql += "AND EXISTS ("
                        + "SELECT 1 "
                        + "FROM prodottoPiattaforma pp2 "
                        + "WHERE pp2.idProdotto = prodotto.idProdotto "
                        + "AND pp2.idPiattaforma IN (";

                for(int i = 0; i < idPiattaforme.size(); i++) {

                    if(i > 0)
                        sql += ", ";

                    sql += "?";
                }

                sql += ")) ";
            }

            // filtro generi
            if(idGeneri != null && !idGeneri.isEmpty()) {

                sql += "AND EXISTS ("
                        + "SELECT 1 "
                        + "FROM prodottoGenere pg "
                        + "WHERE pg.idProdotto = prodotto.idProdotto "
                        + "AND pg.idGenere IN (";

                for(int i = 0; i < idGeneri.size(); i++) {

                    if(i > 0)
                        sql += ", ";

                    sql += "?";
                }

                sql += ")) ";
            }

            sql += "ORDER BY nome ASC";

            PreparedStatement ps =connection.prepareStatement(sql);

            int parametro = 1;

            // parametri ricerca
            if(ricerca != null && !ricerca.trim().isEmpty()) {
                ps.setString(parametro++, "%" + ricerca.trim() + "%");
                ps.setString(parametro++, "%" + ricerca.trim() + "%");
                ps.setString(parametro++, "%" + ricerca.trim() + "%");
            }

            // parametri piattaforme
            if(idPiattaforme != null && !idPiattaforme.isEmpty()) {
                for(Integer idPiattaforma : idPiattaforme) {
                    ps.setInt(parametro++, idPiattaforma);
                }
            }

            // parametri generi
            if(idGeneri != null && !idGeneri.isEmpty()) {
                for(Integer idGenere : idGeneri) {
                    ps.setInt(parametro++, idGenere);
                }
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

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //lettura delle nuove uscite
    public ArrayList<ProdottoViewBean> doRetrieveViewNuoveUscite(int limite) {
    	ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

    	try {
    		String sql = "SELECT * FROM prodotto "
    				+ "WHERE dataUscita <= CURDATE() "
    				+ "AND dataUscita >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) "
    				+ "AND EXISTS ("
    				+ "SELECT 1 FROM prodottoPiattaforma pp "
    				+ "WHERE pp.idProdotto = prodotto.idProdotto "
    				+ "AND pp.quantitaDisponibile > 0) "
    				+ "ORDER BY dataUscita DESC "
    				+ "LIMIT ?";

    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, limite);

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

    	} catch(SQLException e) {
    		e.printStackTrace();
    	}

    	return list;
    }
    
    //lettura dei prodotti con le migliori offerte
    public ArrayList<ProdottoViewBean> doRetrieveViewMiglioriOfferte(int limite) {
    	ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

    	try {
    		String sql = "SELECT p.* FROM prodotto p "
    				+ "JOIN offerta o ON p.idProdotto = o.idProdotto "
    				+ "WHERE o.dataInizio <= CURDATE() "
    				+ "AND o.dataFine >= CURDATE() "
    				+ "AND EXISTS ("
    				+ "SELECT 1 FROM prodottoPiattaforma pp "
    				+ "WHERE pp.idProdotto = p.idProdotto "
    				+ "AND pp.quantitaDisponibile > 0) "
    				+ "ORDER BY o.percentualeSconto DESC "
    				+ "LIMIT ?";

    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, limite);

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

    	} catch(SQLException e) {
    		e.printStackTrace();
    	}

    	return list;
    }
    
    // lettura dei prodotti più venduti
    public ArrayList<ProdottoViewBean> doRetrieveViewPiuVenduti(int limite) {
    	ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

    	try {
    		String sql = "SELECT p.*, SUM(d.quantita) AS totaleVenduto "
    				+ "FROM prodotto p "
    				+ "JOIN dettaglioOrdine d ON p.idProdotto = d.idProdotto "
    				+ "JOIN ordine o ON d.idOrdine = o.idOrdine "
    				+ "WHERE EXISTS ("
    				+ "SELECT 1 FROM prodottoPiattaforma pp "
    				+ "WHERE pp.idProdotto = p.idProdotto "
    				+ "AND pp.quantitaDisponibile > 0) "
    				+ "GROUP BY p.idProdotto "
    				+ "ORDER BY totaleVenduto DESC "
    				+ "LIMIT ?";

    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, limite);

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

    	} catch(SQLException e) {
    		e.printStackTrace();
    	}

    	return list;
    }
    
 // lettura dei prodotti disponibili di una piattaforma
    public ArrayList<ProdottoViewBean> doRetrieveViewDisponibiliByPiattaforma(int idPiattaforma, int limite) {
    	ArrayList<ProdottoViewBean> list = new ArrayList<ProdottoViewBean>();

    	try {
    		String sql = "SELECT p.* FROM prodotto p "
    				+ "JOIN prodottoPiattaforma pp "
    				+ "ON p.idProdotto = pp.idProdotto "
    				+ "WHERE pp.idPiattaforma = ? "
    				+ "AND pp.quantitaDisponibile > 0 "
    				+ "ORDER BY RAND() "
    				+ "LIMIT ?";

    		PreparedStatement ps = connection.prepareStatement(sql);

    		ps.setInt(1, idPiattaforma);
    		ps.setInt(2, limite);

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

    	} catch(SQLException e) {
    		e.printStackTrace();
    	}

    	return list;
    }
}
