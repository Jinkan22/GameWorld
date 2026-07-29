package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TipoProdottoBean;
import utils.DBConnection;

public class TipoProdottoDAO {
	private Connection connection;

    public TipoProdottoDAO() {
        connection = DBConnection.getConnection();
    }
    
    //lettura di un tipo di prodotti in base alla chiave primaria
    public TipoProdottoBean doRetrieveByKey (int idTipoProdotto){
    	TipoProdottoBean tipoProdotto = null;
    	
    	try {
    		String sql = "SELECT * FROM tipoProdotto WHERE idTipoProdotto = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		ps.setInt(1, idTipoProdotto);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			tipoProdotto = new TipoProdottoBean();
    			
    			tipoProdotto.setIdTipoProdotto(rs.getInt("idTipoProdotto"));
    			tipoProdotto.setNomeTipo(rs.getString("nomeTipo"));
    		}
    		rs.close();
    		ps.close();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	return tipoProdotto;
    }
    
    //lettura di tutti i tipi di prodotti
    public ArrayList<TipoProdottoBean> doRetrieveAll(){
    	ArrayList<TipoProdottoBean> list = new ArrayList<TipoProdottoBean>();
    	
    	try {
    		String sql = "SELECT * FROM tipoProdotto";
    		
    		PreparedStatement ps = connection.prepareStatement(sql);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			TipoProdottoBean tipoProdotto = new TipoProdottoBean();
    			
    			tipoProdotto.setIdTipoProdotto(rs.getInt("idTipoProdotto"));
    			tipoProdotto.setNomeTipo(rs.getString("nomeTipo"));
    			
    			list.add(tipoProdotto);
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
