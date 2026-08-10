package dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ElementoCarrelloBean;
import model.ElementoCarrelloViewBean;
import model.OffertaBean;
import model.PiattaformaBean;
import model.ProdottoBean;
import utils.DBConnection;

public class ElementoCarrelloDAO {
	private Connection connection;
	
	public ElementoCarrelloDAO() {
		connection = DBConnection.getConnection();
	}
	
	//lettura di un elemento del carrello in base alla chiave primaria
    public ElementoCarrelloBean doRetrieveByKey (int idElementoCarrello){
    	ElementoCarrelloBean elementoCarrello = null;
    	
    	try {
    		String sql = "SELECT * FROM elementoCarrello WHERE idElementoCarrello=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idElementoCarrello);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			elementoCarrello = new ElementoCarrelloBean();
    			
    			elementoCarrello.setIdElementoCarrello(rs.getInt("idElementoCarrello"));
    			elementoCarrello.setQuantita(rs.getInt("quantita"));
    			elementoCarrello.setIdUtente(rs.getInt("idUtente"));
    			elementoCarrello.setIdProdotto(rs.getInt("idProdotto"));
    			elementoCarrello.setIdPiattaforma(rs.getInt("idPiattaforma"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return elementoCarrello;
    }
    
    //lettura di tutti gli elementi del carrello
    public ArrayList<ElementoCarrelloBean> doRetrieveAll(){
    	ArrayList<ElementoCarrelloBean> list = new ArrayList<ElementoCarrelloBean>();
    	
    	try {
    		String sql = "SELECT * FROM elementoCarrello";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			ElementoCarrelloBean elementoCarrello = new ElementoCarrelloBean();
    			
    			elementoCarrello.setIdElementoCarrello(rs.getInt("idElementoCarrello"));
    			elementoCarrello.setQuantita(rs.getInt("quantita"));
    			elementoCarrello.setIdUtente(rs.getInt("idUtente"));
    			elementoCarrello.setIdProdotto(rs.getInt("idProdotto"));
    			elementoCarrello.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			
    			list.add(elementoCarrello);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un elemento del carrello
    public boolean doSave(ElementoCarrelloBean elementoCarrello) {
    	
    	try {
    		String sql = "INSERT INTO elementoCarrello "
    				+ "(quantita, idUtente, idProdotto, idPiattaforma) "
    				+ "VALUES (?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, elementoCarrello.getQuantita());
    		ps.setInt(2, elementoCarrello.getIdUtente());
    		ps.setInt(3, elementoCarrello.getIdProdotto());
    		ps.setInt(4, elementoCarrello.getIdPiattaforma());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //modifica di un elemento del carrello
    public boolean doUpdate(ElementoCarrelloBean elementoCarrello) {
    	try {
    		String sql = "UPDATE elementoCarrello "
    				+ "SET quantita=?, idUtente=?, idProdotto=?, idPiattaforma=? "
    				+ "WHERE idElementoCarrello=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, elementoCarrello.getQuantita());
    		ps.setInt(2, elementoCarrello.getIdUtente());
    		ps.setInt(3, elementoCarrello.getIdProdotto());
    		ps.setInt(4, elementoCarrello.getIdPiattaforma());
    		ps.setInt(5, elementoCarrello.getIdElementoCarrello());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un elemento del carrello
    public boolean doDelete(int idElementoCarrello) {
    	try {
    		String sql = "DELETE FROM elementoCarrello WHERE idElementoCarrello=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idElementoCarrello);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura degli elementi del carrello in base a un idUtente
    public ArrayList<ElementoCarrelloBean> doRetrieveByIdUtente(int idUtente) {
    	ArrayList<ElementoCarrelloBean> list = new ArrayList<ElementoCarrelloBean>();
    	
    	try {
    		String sql = "SELECT * FROM elementoCarrello WHERE idUtente=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idUtente);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			ElementoCarrelloBean elementoCarrello = new ElementoCarrelloBean();
    			
    			elementoCarrello.setIdElementoCarrello(rs.getInt("idElementoCarrello"));
    			elementoCarrello.setQuantita(rs.getInt("quantita"));
    			elementoCarrello.setIdUtente(rs.getInt("idUtente"));
    			elementoCarrello.setIdProdotto(rs.getInt("idProdotto"));
    			elementoCarrello.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			
    			list.add(elementoCarrello);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //lettura degli elementi del carrello in base a idUtente, idProdotto e idPiattaforma
    public ElementoCarrelloBean doRetrieveByIdUtenteIdProdottoIdPiattaforma(int idUtente, int idProdotto, int idPiattaforma) {
    	ElementoCarrelloBean elementoCarrello = null;
    	
    	try {
    		String sql = "SELECT * FROM elementoCarrello WHERE idUtente=? AND idProdotto=? AND idPiattaforma=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idUtente);
    		ps.setInt(2, idProdotto);
    		ps.setInt(3, idPiattaforma);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			elementoCarrello = new ElementoCarrelloBean();
    			
    			elementoCarrello.setIdElementoCarrello(rs.getInt("idElementoCarrello"));
    			elementoCarrello.setQuantita(rs.getInt("quantita"));
    			elementoCarrello.setIdUtente(rs.getInt("idUtente"));
    			elementoCarrello.setIdProdotto(rs.getInt("idProdotto"));
    			elementoCarrello.setIdPiattaforma(rs.getInt("idPiattaforma"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return elementoCarrello;
    }
    
    // lettura di tutti gli elementi carrello view di un determinato utente
    public ArrayList<ElementoCarrelloViewBean> doRetrieveViewByIdUtente(int idUtente) {

        ArrayList<ElementoCarrelloViewBean> list = new ArrayList<>();

        try {
            String sql = "SELECT p.*, pi.idPiattaforma, pi.nomePiattaforma, ec.quantita "
                    + "FROM elementoCarrello ec "
                    + "JOIN prodotto p "
                    + "ON ec.idProdotto = p.idProdotto "
                    + "JOIN prodottoPiattaforma pp "
                    + "ON ec.idProdotto = pp.idProdotto "
                    + "AND ec.idPiattaforma = pp.idPiattaforma "
                    + "JOIN piattaforma pi "
                    + "ON pp.idPiattaforma = pi.idPiattaforma "
                    + "WHERE ec.idUtente=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUtente);

            ResultSet rs = ps.executeQuery();

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

                PiattaformaBean piattaforma = new PiattaformaBean();

                piattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));
                piattaforma.setNomePiattaforma(rs.getString("nomePiattaforma"));

                OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(prodotto.getIdProdotto());

                BigDecimal prezzoScontato = null;

                if(offerta != null) {
                    prezzoScontato = prodotto.getPrezzo().multiply(BigDecimal.ONE.subtract(
                    		BigDecimal.valueOf(offerta.getPercentualeSconto()).divide(
                            BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP);
                }

                ElementoCarrelloViewBean elementoView = new ElementoCarrelloViewBean();

                elementoView.setProdotto(prodotto);
                elementoView.setPiattaforma(piattaforma);
                elementoView.setQuantita(rs.getInt("quantita"));
                elementoView.setOfferta(offerta);
                elementoView.setPrezzoScontato(prezzoScontato);

                list.add(elementoView);
            }

            rs.close();
            ps.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
