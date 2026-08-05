package utils;

import java.util.ArrayList;

import dao.ProdottoDAO;
import model.ElementoCarrelloBean;
import model.ElementoCarrelloViewBean;
import model.ProdottoBean;

public class CarrelloUtils {
	
	//crea il carrelloView con gli elementi da mostrare nel carrello
	public static ArrayList<ElementoCarrelloViewBean> creaCarrelloView(ArrayList<ElementoCarrelloBean> carrelloDB) {
		
		if(carrelloDB == null) {
			carrelloDB = new ArrayList<ElementoCarrelloBean>();
		}
		
		ArrayList<ElementoCarrelloViewBean> carrelloView = new ArrayList<ElementoCarrelloViewBean>();
		
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		
		for(ElementoCarrelloBean elemento : carrelloDB) {
			ElementoCarrelloViewBean elementoView = new ElementoCarrelloViewBean();
			
			ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());
			
			if(prodotto == null)
				continue;
			
			elementoView.setProdotto(prodotto);
			elementoView.setQuantita(elemento.getQuantita());
			
			carrelloView.add(elementoView);
		}
		
		return carrelloView;
	}
	
	//controlla se c'è la disponibilità prodotti
	public static boolean checkDisponibilita(ElementoCarrelloBean elemento, int incremento) {
		ProdottoDAO dao = new ProdottoDAO();
		ProdottoBean prodotto = dao.doRetrieveByKey(elemento.getIdProdotto());
		
		if(prodotto == null)
			return false;
		
		if(elemento.getQuantita() + incremento <= prodotto.getQuantitaDisponibile())
			return true;
		else return false;
	}
}
