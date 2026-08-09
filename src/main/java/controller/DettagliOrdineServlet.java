package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DettaglioOrdineBean;
import model.DettaglioOrdineViewBean;
import model.OrdineBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.DettaglioOrdineDAO;
import dao.OrdineDAO;
import dao.ProdottoDAO;

/**
 * Servlet implementation class DettaglioOrdineServlet
 */
@WebServlet("/DettagliOrdineServlet")
public class DettagliOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean)session.getAttribute("utente");
		
		if(utente == null) {
			request.setAttribute("errore", "Effettuare il login per visualizzare lo storico degli ordini");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
		
		OrdineDAO ordineDAO = new OrdineDAO();
		DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();
		
		OrdineBean ordine = ordineDAO.doRetrieveByKey(idOrdine);
		
		if(ordine == null || utente.getIdUtente() != ordine.getIdUtente()) {
			response.sendRedirect(request.getContextPath() + "/StoricoOrdiniServlet");
			return;
		}
		
		ArrayList<DettaglioOrdineViewBean> dettagliOrdineView = dettaglioOrdineDAO.doRetrieveViewByIdOrdine(idOrdine);
		
		request.setAttribute("ordine", ordine);
		request.setAttribute("dettagliOrdine", dettagliOrdineView);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dettagliOrdine.jsp");
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
