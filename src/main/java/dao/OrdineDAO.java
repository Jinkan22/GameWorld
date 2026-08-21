package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.DettaglioOrdineViewBean;
import model.OrdineBean;
import model.OrdineViewBean;
import utils.DBConnection;

public class OrdineDAO {
    public OrdineDAO() {
    	
    }
    
    //lettura di un ordine in base alla chiave primaria
    public OrdineBean doRetrieveByKey (int idOrdine){
    	OrdineBean ordine = null;
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM ordine WHERE idOrdine = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idOrdine);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			ordine = new OrdineBean();
    			
    			ordine.setIdOrdine(rs.getInt("idOrdine"));
    			ordine.setAcquirente(rs.getString("acquirente"));
    			ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
    			ordine.setTotale(rs.getBigDecimal("totale"));
    			ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
    			ordine.setIdUtente(rs.getInt("idUtente"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return ordine;
    }
    
    //lettura di tutti gli ordini
    public ArrayList<OrdineBean> doRetrieveAll(){
    	ArrayList<OrdineBean> list = new ArrayList<OrdineBean>();
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM ordine";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			OrdineBean ordine = new OrdineBean();
    			
    			ordine.setIdOrdine(rs.getInt("idOrdine"));
    			ordine.setAcquirente(rs.getString("acquirente"));
    			ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
    			ordine.setTotale(rs.getBigDecimal("totale"));
    			ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
    			ordine.setIdUtente(rs.getInt("idUtente"));
    			
    			list.add(ordine);
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    //salvataggio di un ordine
    public boolean doSave(OrdineBean ordine) {
    	
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "INSERT INTO ordine "
    				+ "(acquirente, dataOrdine, totale, indirizzoFatturazione, idUtente) "
    				+ "VALUES (?, ?, ?, ?, ?)";
    		
    		PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    		ps.setString(1, ordine.getAcquirente());
    		ps.setTimestamp(2, ordine.getDataOrdine());
    		ps.setBigDecimal(3, ordine.getTotale());
    		ps.setString(4, ordine.getIndirizzoFatturazione());
    		ps.setInt(5, ordine.getIdUtente());
    		
    		int result = ps.executeUpdate();
    		
    		ResultSet rs = ps.getGeneratedKeys();

    		if(rs.next()) {
    		    ordine.setIdOrdine(rs.getInt(1));
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
    
    //modifica di un ordine
    public boolean doUpdate(OrdineBean ordine) {
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "UPDATE ordine "
    				+ "SET acquirente=?, dataOrdine=?, totale=?, indirizzoFatturazione=?, idUtente=? "
    				+ "WHERE idOrdine=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, ordine.getAcquirente());
    		ps.setTimestamp(2, ordine.getDataOrdine());
    		ps.setBigDecimal(3, ordine.getTotale());
    		ps.setInt(4, ordine.getIdUtente());
    		ps.setString(5, ordine.getIndirizzoFatturazione());
    		ps.setInt(6, ordine.getIdOrdine());
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //eliminazione di un ordine
    public boolean doDelete(int idOrdine) {
    	try (Connection connection = DBConnection.getConnection()) {
    		String sql = "DELETE FROM ordine WHERE idOrdine=?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ps.setInt(1, idOrdine);
    		
    		int result = ps.executeUpdate();
    		ps.close();
    		
    		return result > 0;
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    //lettura di tutti gli ordini view
    public ArrayList<OrdineViewBean> doRetrieveAllView() {
        ArrayList<OrdineViewBean> list = new ArrayList<OrdineViewBean>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM ordine ORDER BY dataOrdine DESC";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();

            while(rs.next()) {

                OrdineBean ordine = new OrdineBean();

                ordine.setIdOrdine(rs.getInt("idOrdine"));
                ordine.setAcquirente(rs.getString("acquirente"));
                ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
                ordine.setTotale(rs.getBigDecimal("totale"));
                ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
                ordine.setIdUtente(rs.getInt("idUtente"));

                ArrayList<DettaglioOrdineViewBean> dettagli = dettaglioOrdineDAO.doRetrieveViewByIdOrdine(ordine.getIdOrdine());

                OrdineViewBean ordineView = new OrdineViewBean();
                
                ordineView.setOrdine(ordine);
                ordineView.setDettagli(dettagli);

                list.add(ordineView);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //lettura di tutti gli ordini view di un utente
    public ArrayList<OrdineViewBean> doRetrieveViewByIdUtente(int idUtente) {
        ArrayList<OrdineViewBean> list = new ArrayList<OrdineViewBean>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM ordine WHERE idUtente=? ORDER BY dataOrdine DESC";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUtente);

            ResultSet rs = ps.executeQuery();

            DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();

            while(rs.next()) {

                OrdineBean ordine = new OrdineBean();

                ordine.setIdOrdine(rs.getInt("idOrdine"));
                ordine.setAcquirente(rs.getString("acquirente"));
                ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
                ordine.setTotale(rs.getBigDecimal("totale"));
                ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
                ordine.setIdUtente(rs.getInt("idUtente"));

                ArrayList<DettaglioOrdineViewBean> dettagli = dettaglioOrdineDAO.doRetrieveViewByIdOrdine(ordine.getIdOrdine());

                OrdineViewBean ordineView = new OrdineViewBean();
                
                ordineView.setOrdine(ordine);
                ordineView.setDettagli(dettagli);

                list.add(ordineView);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //lettura degli ordini view in base ai filtri
    public ArrayList<OrdineViewBean> doRetrieveViewByFiltri(Timestamp dataInizio, Timestamp dataFine, Integer idUtente) {
        ArrayList<OrdineViewBean> list = new ArrayList<OrdineViewBean>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM ordine WHERE 1=1";

            if (dataInizio != null) {
                sql += " AND dataOrdine >= ?";
            }
            if (dataFine != null) {
                sql += " AND dataOrdine <= ?";
            }
            if (idUtente != null) {
                sql += " AND idUtente = ?";
            }

            sql += " ORDER BY dataOrdine DESC";

            PreparedStatement ps = connection.prepareStatement(sql);

            int index = 1;

            if (dataInizio != null) {
                ps.setTimestamp(index++, dataInizio);
            }
            if (dataFine != null) {
                ps.setTimestamp(index++, dataFine);
            }
            if (idUtente != null) {
                ps.setInt(index++, idUtente);
            }

            ResultSet rs = ps.executeQuery();

            DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();

            while (rs.next()) {
                OrdineBean ordine = new OrdineBean();

                ordine.setIdOrdine(rs.getInt("idOrdine"));
                ordine.setAcquirente(rs.getString("acquirente"));
                ordine.setDataOrdine(rs.getTimestamp("dataOrdine"));
                ordine.setTotale(rs.getBigDecimal("totale"));
                ordine.setIndirizzoFatturazione(rs.getString("indirizzoFatturazione"));
                ordine.setIdUtente(rs.getInt("idUtente"));

                ArrayList<DettaglioOrdineViewBean> dettagli = dettaglioOrdineDAO.doRetrieveViewByIdOrdine(ordine.getIdOrdine());

                OrdineViewBean ordineView = new OrdineViewBean();

                ordineView.setOrdine(ordine);
                ordineView.setDettagli(dettagli);

                list.add(ordineView);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}