package utils;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	
	//controlla se c'è la disponibilità prodotti passando un elemento del carrello
	public static boolean checkDisponibilita(ElementoCarrelloBean elemento, int quantita) {
		ProdottoDAO dao = new ProdottoDAO();
		ProdottoBean prodotto = dao.doRetrieveByKey(elemento.getIdProdotto());
		
		return checkDisponibilita(prodotto, quantita);
	}
	
	//controlla se c'è la disponibilità prodotti passando un prodotto
	public static boolean checkDisponibilita(ProdottoBean prodotto, int quantita) {
		if(prodotto == null)
			return false;
		
		if(quantita <= prodotto.getQuantitaDisponibile())
			return true;
		else return false;
	}
		
	//manda alla pagina prodotto se non c'è la disponibilità
	public static boolean gestioneQuantitaNonDisponibile(HttpServletRequest request, HttpServletResponse response, ElementoCarrelloBean elemento, int quantita) throws ServletException, IOException {
		if(!CarrelloUtils.checkDisponibilita(elemento, quantita)) {
			ProdottoDAO dao = new ProdottoDAO();
			
			request.setAttribute("errore", "La quantità richiesta non è disponibile");
			request.setAttribute("prodotto", dao.doRetrieveByKey(elemento.getIdProdotto()));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/PaginaProdottoServlet");
			dispatcher.forward(request, response);
			return false;
		}
		return true;
	}
}
