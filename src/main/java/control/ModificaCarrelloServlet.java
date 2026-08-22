package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ElementoCarrelloBean;
import model.ElementoCarrelloViewBean;
import model.UtenteBean;
import utils.CarrelloUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import dao.ElementoCarrelloDAO;

@WebServlet("/ModificaCarrello")
public class ModificaCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificaCarrelloServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    String azione = request.getParameter("azione");

	    HttpSession session = request.getSession();
	    UtenteBean utente = (UtenteBean) session.getAttribute("utente");

	    int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
	    int idPiattaforma = Integer.parseInt(request.getParameter("idPiattaforma"));

	    int nuovaQuantita = 0;

	    if(utente == null) {

	        ArrayList<ElementoCarrelloBean> carrello = (ArrayList<ElementoCarrelloBean>) session.getAttribute("carrello");

	        if(carrello == null || carrello.isEmpty()) {
	            response.getWriter().write("{\"successo\":false,\"errore\":\"Il carrello è vuoto\"}");
	            return;
	        }

	        ElementoCarrelloBean trovato = null;

	        for(ElementoCarrelloBean elemento : carrello) {

	            if(elemento.getIdProdotto() == idProdotto &&
	               elemento.getIdPiattaforma() == idPiattaforma) {

	                trovato = elemento;
	                break;
	            }
	        }

	        if(trovato == null) {
	            response.getWriter().write("{\"successo\":false,\"errore\":\"Prodotto non trovato nel carrello\"}");
	            return;
	        }

	        switch(azione) {
	        case "+":

	            if(!CarrelloUtils.checkDisponibilita(trovato, trovato.getQuantita() + 1)) {
	                response.getWriter().write("{\"successo\":false,\"errore\":\"La quantità richiesta non è disponibile\"}");
	                return;
	            }
	            trovato.setQuantita(trovato.getQuantita() + 1);
	            nuovaQuantita = trovato.getQuantita();
	            break;
	        case "-":
	            trovato.setQuantita(trovato.getQuantita() - 1);

	            if(trovato.getQuantita() <= 0) {
	                carrello.remove(trovato);
	                nuovaQuantita = 0;
	            }
	            else {
	                nuovaQuantita = trovato.getQuantita();
	            }
	            break;
	        case "Rimuovi":
	            carrello.remove(trovato);
	            nuovaQuantita = 0;
	            break;
	        }
	        session.setAttribute("carrello", carrello);
	    }

	    else {
	        ElementoCarrelloDAO dao = new ElementoCarrelloDAO();
	        ElementoCarrelloBean elemento = dao.doRetrieveByIdUtenteIdProdottoIdPiattaforma(utente.getIdUtente(), idProdotto, idPiattaforma);

	        if(elemento == null) {
	            response.getWriter().write("{\"successo\":false,\"errore\":\"Prodotto non trovato nel carrello\"}");
	            return;
	        }

	        switch(azione) {
	        case "+":
	            if(!CarrelloUtils.checkDisponibilita(elemento, elemento.getQuantita() + 1)) {
	                response.getWriter().write("{\"successo\":false,\"errore\":\"La quantità richiesta non è disponibile\"}");
	                return;
	            }
	            elemento.setQuantita(elemento.getQuantita() + 1);
	            dao.doUpdate(elemento);
	            
	            nuovaQuantita = elemento.getQuantita();
	            break;
	        case "-":
	            if(elemento.getQuantita() > 1) {
	                elemento.setQuantita(elemento.getQuantita() - 1);
	                dao.doUpdate(elemento);
	                
	                nuovaQuantita = elemento.getQuantita();
	            }
	            else {
	                dao.doDelete(elemento.getIdElementoCarrello());
	                nuovaQuantita = 0;
	            }
	            break;
	        case "Rimuovi":
	            dao.doDelete(elemento.getIdElementoCarrello());
	            nuovaQuantita = 0;
	            break;
	        }
	    }

	    ArrayList<ElementoCarrelloBean> carrelloAggiornato;

	    if(utente == null) {
	        carrelloAggiornato = (ArrayList<ElementoCarrelloBean>) session.getAttribute("carrello");
	    }
	    else {
	        ElementoCarrelloDAO dao = new ElementoCarrelloDAO();
	        carrelloAggiornato = dao.doRetrieveByIdUtente(utente.getIdUtente());
	    }

	    ArrayList<ElementoCarrelloViewBean> carrelloView = CarrelloUtils.creaCarrelloView(carrelloAggiornato);

	    BigDecimal totale = BigDecimal.ZERO;
	    BigDecimal totaleScontato = BigDecimal.ZERO;

	    for(ElementoCarrelloViewBean elementoView : carrelloView) {
	    	
	        BigDecimal prezzo = elementoView.getProdotto().getPrezzo();
	        totale = totale.add(prezzo.multiply(BigDecimal.valueOf(elementoView.getQuantita())));
	        BigDecimal prezzoFinale = prezzo;

	        if(elementoView.getOfferta() != null) {
	            prezzoFinale = elementoView.getPrezzoScontato();
	        }
	        totaleScontato = totaleScontato.add(prezzoFinale.multiply(BigDecimal.valueOf(elementoView.getQuantita())));
	    }
	    
	    BigDecimal sconto = totale.subtract(totaleScontato);

	    if(carrelloAggiornato == null || carrelloAggiornato.isEmpty()) {
	    	response.getWriter().write(
		            "{\"successo\":true,"
	    			+ "\"errore\":\"Il carrello è vuoto\","
		            + "\"quantita\":" + nuovaQuantita + ","
		            + "\"totale\":" + totale + ","
		            + "\"sconto\":" + sconto + ","
		            + "\"totaleScontato\":" + totaleScontato
		            + "}"
		    );
        }
	    else {
	    	response.getWriter().write(
		            "{\"successo\":true,"
		            + "\"quantita\":" + nuovaQuantita + ","
		            + "\"totale\":" + totale + ","
		            + "\"sconto\":" + sconto + ","
		            + "\"totaleScontato\":" + totaleScontato
		            + "}"
		    );
	    }
	}
}
