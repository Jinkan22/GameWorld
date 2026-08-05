package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ElementoCarrelloBean;
import model.ProdottoBean;
import model.UtenteBean;
import utils.CarrelloUtils;

import java.io.IOException;
import java.util.ArrayList;

import dao.ElementoCarrelloDAO;
import dao.ProdottoDAO;

/**
 * Servlet implementation class ModificaCarrelloServlet
 */
@WebServlet("/ModificaCarrelloServlet")
public class ModificaCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaCarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("azione");
		
		HttpSession session = request.getSession();
		
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		
		if(utente == null) {
			
			ArrayList<ElementoCarrelloBean> carrello = (ArrayList<ElementoCarrelloBean>)session.getAttribute("carrello");
			
			if(carrello == null || carrello.isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/CarrelloServlet");
				return;
			}
			
			ElementoCarrelloBean trovato = null;
			
			for(ElementoCarrelloBean elemento : carrello) {
				if(elemento.getIdProdotto() == idProdotto) {
					trovato = elemento;
					break;
				}
			}
			
			if(trovato == null) {
				response.sendRedirect(request.getContextPath() + "/CarrelloServlet");
			    return;
			}
			
			switch(azione) {
			case "+":
				if(CarrelloUtils.checkDisponibilita(trovato, 1)) {
					trovato.setQuantita(trovato.getQuantita()+1);
				}
				break;
			case "-":
				trovato.setQuantita(trovato.getQuantita()-1);
				if(trovato.getQuantita() <= 0)
					carrello.remove(trovato);
				break;
			case "rimuovi":
				carrello.remove(trovato);
				break;
			}
			
			session.setAttribute("carrello", carrello);
		}
		else {
			
			ElementoCarrelloDAO dao = new ElementoCarrelloDAO();
			
			ElementoCarrelloBean elemento = dao.doRetrieveByIdUtenteAndIdProdotto(utente.getIdUtente(), idProdotto);
			
			if(elemento == null) {
				response.sendRedirect(request.getContextPath() + "/CarrelloServlet");
				return;
			}
			
			switch(azione) {
			case "+":
				if(CarrelloUtils.checkDisponibilita(elemento, 1)) {
					elemento.setQuantita(elemento.getQuantita() + 1);
					dao.doUpdate(elemento);
				}
				break;
			case "-":
				if(elemento.getQuantita() > 1) {
					elemento.setQuantita(elemento.getQuantita() - 1);
					dao.doUpdate(elemento);
				}
				else dao.doDelete(elemento.getIdElementoCarrello());
				break;
			case "rimuovi":
				dao.doDelete(elemento.getIdElementoCarrello());
				break;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/CarrelloServlet");
	}
}
