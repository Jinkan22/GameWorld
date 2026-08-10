package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ElementoCarrelloBean;
import model.ElementoCarrelloViewBean;
import model.ProdottoBean;
import model.UtenteBean;
import utils.CarrelloUtils;

import java.io.IOException;
import java.util.ArrayList;

import dao.ElementoCarrelloDAO;
import dao.ProdottoDAO;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");

		ArrayList<ElementoCarrelloViewBean> carrelloView;

		if(utente == null) {
			ArrayList<ElementoCarrelloBean> carrello = (ArrayList<ElementoCarrelloBean>) session.getAttribute("carrello");
			carrelloView = CarrelloUtils.creaCarrelloView(carrello);
		}
		else {
			ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
			carrelloView = elementoCarrelloDAO.doRetrieveViewByIdUtente(utente.getIdUtente());
		}
		
		//sposta l'eventuale errore dalla session alla request
		String errore = (String) session.getAttribute("errore");
		if(errore != null) {
			request.setAttribute("errore", errore);
			session.removeAttribute("errore");
		}

		request.setAttribute("carrello", carrelloView);

		RequestDispatcher dispatcher =request.getRequestDispatcher("/jsp/carrello.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
