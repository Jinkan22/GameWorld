package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DettaglioOrdineBean;
import model.DettaglioOrdineViewBean;
import model.PiattaformaBean;
import model.ProdottoBean;
import utils.DBConnection;

public class DettaglioOrdineDAO {
private Connection connection;
	
	public DettaglioOrdineDAO() {
		connection = DBConnection.getConnection();
	}
	
	//lettura di un dettaglio ordine in base alla chiave primaria
    public DettaglioOrdineBean doRetrieveByKey (int idDettaglioOrdine){
    	DettaglioOrdineBean dettaglioOrdine = null;
    	
    	try {
    		String sql = "SELECT * FROM dettaglioOrdine WHERE idDettaglio=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idDettaglioOrdine);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			dettaglioOrdine.setIdPiattaforma(rs.getInt("idPiattaforma"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return dettaglioOrdine;
    }
    
    //lettura di tutti i dettagli ordine
    public ArrayList<DettaglioOrdineBean> doRetrieveAll(){
    	ArrayList<DettaglioOrdineBean> list = new ArrayList<DettaglioOrdineBean>();
    	
    	try {
    		String sql = "SELECT * FROM dettaglioOrdine";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			dettaglioOrdine.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			
    			list.add(dettaglioOrdine);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un dettaglio ordine
    public boolean doSave(DettaglioOrdineBean dettaglioOrdine) {
    	
    	try {
    		String sql = "INSERT INTO dettaglioOrdine "
    				+ "(quantita, prezzoAcquisto, idOrdine, idProdotto, idPiattaforma) "
    				+ "VALUES (?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, dettaglioOrdine.getQuantita());
    		ps.setFloat(2, dettaglioOrdine.getPrezzoAcquisto());
    		ps.setInt(3, dettaglioOrdine.getIdOrdine());
    		ps.setInt(4,  dettaglioOrdine.getIdProdotto());
    		ps.setInt(5, dettaglioOrdine.getIdPiattaforma());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un dettaglio ordine
    public boolean doDelete(int idDettaglioOrdine) {
    	try {
    		String sql = "DELETE FROM dettaglioOrdine WHERE idDettaglio=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idDettaglioOrdine);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    //lettura di tutti i dettagli ordine di un ordine
	public ArrayList<DettaglioOrdineBean> doRetrieveByIdOrdine(int idOrdine) {
		ArrayList<DettaglioOrdineBean> list = new ArrayList<DettaglioOrdineBean>();
    	
    	try {
    		String sql = "SELECT * FROM dettaglioOrdine WHERE idOrdine=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idOrdine);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
    			
    			dettaglioOrdine.setIdDettaglio(rs.getInt("idDettaglio"));
    			dettaglioOrdine.setQuantita(rs.getInt("quantita"));
    			dettaglioOrdine.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));
    			dettaglioOrdine.setIdOrdine(rs.getInt("idOrdine"));
    			dettaglioOrdine.setIdProdotto(rs.getInt("idProdotto"));
    			dettaglioOrdine.setIdPiattaforma(rs.getInt("idPiattaforma"));
    			
    			list.add(dettaglioOrdine);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
	}
	
	// lettura di tutti i dettagli ordine view di un ordine
	public ArrayList<DettaglioOrdineViewBean> doRetrieveViewByIdOrdine(int idOrdine) {
		ArrayList<DettaglioOrdineViewBean> list = new ArrayList<DettaglioOrdineViewBean>();

		try {
			String sql = "SELECT p.*, pi.*, d.quantita, d.prezzoAcquisto "
					+ "FROM dettaglioOrdine d "
					+ "JOIN prodotto p "
					+ "ON d.idProdotto = p.idProdotto "
					+ "JOIN piattaforma pi "
					+ "ON d.idPiattaforma = pi.idPiattaforma "
					+ "WHERE d.idOrdine=?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, idOrdine);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				ProdottoBean prodotto = new ProdottoBean();

				prodotto.setIdProdotto(rs.getInt("idProdotto"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setPrezzo(rs.getFloat("prezzo"));
				prodotto.setImmagine(rs.getString("immagine"));
				prodotto.setDataUscita(rs.getDate("dataUscita"));
				prodotto.setSviluppatore(rs.getString("sviluppatore"));

				PiattaformaBean piattaforma = new PiattaformaBean();

				piattaforma.setIdPiattaforma(rs.getInt("idPiattaforma"));
				piattaforma.setNomePiattaforma(rs.getString("nomePiattaforma"));

				DettaglioOrdineViewBean dettaglioOrdine =new DettaglioOrdineViewBean();
				
				dettaglioOrdine.setProdotto(prodotto);
				dettaglioOrdine.setPiattaforma(piattaforma);
				dettaglioOrdine.setQuantita(rs.getInt("quantita"));
				dettaglioOrdine.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));

				list.add(dettaglioOrdine);
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
