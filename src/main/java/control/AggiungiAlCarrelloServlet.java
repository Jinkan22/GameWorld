package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ElementoCarrelloBean;
import model.UtenteBean;
import utils.CarrelloUtils;

import java.io.IOException;
import java.util.ArrayList;

import dao.ElementoCarrelloDAO;

@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AggiungiAlCarrelloServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		int idPiattaforma = Integer.parseInt(request.getParameter("idPiattaforma"));
		
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");	
		
		if(utente == null) {
			ArrayList<ElementoCarrelloBean> carrello = (ArrayList<ElementoCarrelloBean>) session.getAttribute("carrello");
			
			if(carrello == null)
				carrello = new ArrayList<ElementoCarrelloBean>();
			
			boolean found = false;
			for(ElementoCarrelloBean elemento : carrello) {
				if(elemento.getIdProdotto() == idProdotto && elemento.getIdPiattaforma() == idPiattaforma) {
					
					if(!CarrelloUtils.gestioneQuantitaNonDisponibile(request, response, elemento, elemento.getQuantita() + 1))
						return;
					
					elemento.setQuantita(elemento.getQuantita() + 1);
					
					found = true;
					break;
				}
			}
			if(!found) {
				ElementoCarrelloBean elemento = new ElementoCarrelloBean();
				
				elemento.setIdProdotto(idProdotto);
				elemento.setIdPiattaforma(idPiattaforma);
				
				if(!CarrelloUtils.gestioneQuantitaNonDisponibile(request, response, elemento, 1))
					return;
				
				elemento.setQuantita(1);
				
				carrello.add(elemento);
			}
			
			session.setAttribute("carrello", carrello);
		}
		else {
			ElementoCarrelloDAO dao = new ElementoCarrelloDAO();
			
			ElementoCarrelloBean elemento = dao.doRetrieveByIdUtenteIdProdottoIdPiattaforma(utente.getIdUtente(), idProdotto, idPiattaforma);
			
			if(elemento == null) {
				elemento = new ElementoCarrelloBean();
				
				elemento.setIdProdotto(idProdotto);
				elemento.setIdPiattaforma(idPiattaforma);
				elemento.setIdUtente(utente.getIdUtente());
				
				if(!CarrelloUtils.gestioneQuantitaNonDisponibile(request, response, elemento, 1))
					return;
				
				elemento.setQuantita(1);
				
				dao.doSave(elemento);
			}		
			else {
				if(!CarrelloUtils.gestioneQuantitaNonDisponibile(request, response, elemento, elemento.getQuantita() + 1))
					return;
				
				elemento.setQuantita(elemento.getQuantita() + 1);
				dao.doUpdate(elemento);
			}
		}

		response.sendRedirect("Carrello");
	}
}
