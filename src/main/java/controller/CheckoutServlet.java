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
import model.IndirizzoBean;
import model.MetodoPagamentoBean;
import model.ProdottoBean;
import model.UtenteBean;
import utils.CarrelloUtils;

import java.io.IOException;
import java.util.ArrayList;

import dao.ElementoCarrelloDAO;
import dao.IndirizzoDAO;
import dao.MetodoPagamentoDAO;
import dao.ProdottoDAO;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null) {
			request.setAttribute("errore", "Effettuare il login per finalizzare l'acquisto");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
		
		ArrayList<ElementoCarrelloBean> carrelloDB = elementoCarrelloDAO.doRetrieveByIdUtente(utente.getIdUtente());
		
		ArrayList<ElementoCarrelloViewBean> carrelloView = CarrelloUtils.creaCarrelloView(carrelloDB);
		
		IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
		
		ArrayList<IndirizzoBean> indirizzi = indirizzoDAO.doRetrieveByIdUtente(utente.getIdUtente());
		
		MetodoPagamentoDAO metodoPagamentoDAO = new MetodoPagamentoDAO();
		
		ArrayList<MetodoPagamentoBean> metodiPagamento = metodoPagamentoDAO.doRetrieveByIdUtente(utente.getIdUtente());
		
		request.setAttribute("carrello", carrelloView);
		request.setAttribute("indirizzi", indirizzi);
		request.setAttribute("metodiPagamento", metodiPagamento);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/checkout.jsp");
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
