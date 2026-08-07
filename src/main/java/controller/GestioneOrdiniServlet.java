package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrdineBean;
import model.OrdineViewBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.OrdineDAO;
import dao.UtenteDAO;

/**
 * Servlet implementation class GestioneOrdiniServlet
 */
@WebServlet("/GestioneOrdiniServlet")
public class GestioneOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneOrdiniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null || !"ADMIN".equals(utente.getRuolo())) {
			request.setAttribute("errore", "Effettuare il login come admin per accedere alla dashboard");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		OrdineDAO ordineDAO = new OrdineDAO();
		UtenteDAO utenteDAO = new UtenteDAO();
		ArrayList<OrdineBean> ordini = ordineDAO.doRetrieveAll();
		ArrayList<OrdineViewBean> ordiniView = new ArrayList<OrdineViewBean>();
		
		for(OrdineBean ordine : ordini) {
			UtenteBean acquirente = utenteDAO.doRetrieveByKey(ordine.getIdUtente());
			OrdineViewBean ordineView = new OrdineViewBean();
			
			ordineView.setOrdine(ordine);
			ordineView.setUtente(acquirente);
			
			ordiniView.add(ordineView);
		}
		
		request.setAttribute("ordini", ordiniView);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gestioneOrdini.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// aggiornare lo stato degli ordini nel db
	}

}
