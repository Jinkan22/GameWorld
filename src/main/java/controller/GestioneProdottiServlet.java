package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ProdottoBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProdottoDAO;

/**
 * Servlet implementation class GestioneProdottiServlet
 */
@WebServlet("/GestioneProdottiServlet")
public class GestioneProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneProdottiServlet() {
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
		
		ProdottoDAO dao = new ProdottoDAO();
		ArrayList<ProdottoBean> prodotti = dao.doRetrieveAll();
		
		request.setAttribute("prodotti", prodotti);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gestioneProdotti.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String azione = request.getParameter("azione");
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		
		System.out.println("Azione: " + azione);
		System.out.println("ID prodotto: " + idProdotto);
		
		ProdottoDAO dao = new ProdottoDAO();
		ProdottoBean prodotto = dao.doRetrieveByKey(idProdotto);
		
		//controlla se il prodotto non esiste
		if(prodotto == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gestioneProdotti.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		switch(azione) {
		case "Modifica": {
			request.setAttribute("prodotto", prodotto);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/modificaProdotto.jsp");
			dispatcher.forward(request, response);
			break;
			}
		case "Elimina": {
			prodotto.setQuantitaDisponibile(0);
			dao.doUpdate(prodotto);
			response.sendRedirect(request.getContextPath() + "/GestioneProdottiServlet");
			break;
			}	
		}
	}

}
