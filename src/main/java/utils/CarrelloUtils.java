package utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import dao.OffertaDAO;
import dao.PiattaformaDAO;
import dao.ProdottoDAO;
import dao.ProdottoPiattaformaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElementoCarrelloBean;
import model.ElementoCarrelloViewBean;
import model.OffertaBean;
import model.PiattaformaBean;
import model.ProdottoBean;
import model.ProdottoPiattaformaBean;

public class CarrelloUtils {
	
	// crea il carrelloView con gli elementi da mostrare nel carrello
	public static ArrayList<ElementoCarrelloViewBean> creaCarrelloView(
	        ArrayList<ElementoCarrelloBean> carrelloDB) {

	    if(carrelloDB == null) {
	        carrelloDB = new ArrayList<ElementoCarrelloBean>();
	    }

	    ArrayList<ElementoCarrelloViewBean> carrelloView = new ArrayList<ElementoCarrelloViewBean>();

	    ProdottoDAO prodottoDAO = new ProdottoDAO();
	    PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
	    OffertaDAO offertaDAO = new OffertaDAO();

	    for(ElementoCarrelloBean elemento : carrelloDB) {
	        ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());
	        PiattaformaBean piattaforma = piattaformaDAO.doRetrieveByKey(elemento.getIdPiattaforma());

	        if(prodotto == null || piattaforma == null)
	            continue;

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
	        elementoView.setQuantita(elemento.getQuantita());
	        elementoView.setOfferta(offerta);
	        elementoView.setPrezzoScontato(prezzoScontato);

	        carrelloView.add(elementoView);
	    }

	    return carrelloView;
	}
	
	//controlla se c'è la disponibilità prodotti passando un elemento del carrello
	public static boolean checkDisponibilita(ElementoCarrelloBean elemento, int quantita) {
		ProdottoPiattaformaDAO dao = new ProdottoPiattaformaDAO();
		ProdottoPiattaformaBean prodottoPiattaforma = dao.doRetrieveByKey(elemento.getIdProdotto(), elemento.getIdPiattaforma());
		
		return checkDisponibilita(prodottoPiattaforma, quantita);
	}
	
	//controlla se c'è la disponibilità prodotti passando un prodotto
	public static boolean checkDisponibilita(ProdottoPiattaformaBean prodottoPiattaforma, int quantita) {
		if(prodottoPiattaforma == null)
			return false;
		
		if(quantita <= prodottoPiattaforma.getQuantitaDisponibile())
			return true;
		else return false;
	}
		
	//manda alla pagina prodotto se non c'è la disponibilità
	public static boolean gestioneQuantitaNonDisponibile(HttpServletRequest request, HttpServletResponse response, ElementoCarrelloBean elemento, int quantita) throws ServletException, IOException {
		if(!CarrelloUtils.checkDisponibilita(elemento, quantita)) {			
			request.setAttribute("errore", "La quantità richiesta non è disponibile");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CarrelloServlet");
			dispatcher.forward(request, response);
			return false;
		}
		return true;
	}
}
