package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CarrelloViewBean;
import model.ElementoCarrelloBean;
import model.ProdottoBean;
import model.UtenteBean;

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
		
		ArrayList<ElementoCarrelloBean> carrello;
		
		if(utente == null) {
			carrello = (ArrayList<ElementoCarrelloBean>) session.getAttribute("carrello");	
		}
		else {
			ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
			
			carrello = elementoCarrelloDAO.doRetrieveByIdUtente(utente.getIdUtente());
			
		}
		
		if(carrello == null) {
			carrello = new ArrayList<ElementoCarrelloBean>();
		}
		
		ArrayList<CarrelloViewBean> carrelloView = new ArrayList<CarrelloViewBean>();
		
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		
		for(ElementoCarrelloBean elemento : carrello) {
			CarrelloViewBean elementoView = new CarrelloViewBean();
			
			ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());
			
			if(prodotto == null)
				continue;
			
			elementoView.setProdotto(prodotto);
			elementoView.setQuantita(elemento.getQuantita());
			
			carrelloView.add(elementoView);
		}
		
		request.setAttribute("carrello", carrelloView);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/carrello.jsp");
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
