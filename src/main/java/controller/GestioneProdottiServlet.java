package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GenereBean;
import model.OffertaBean;
import model.PiattaformaBean;
import model.ProdottoBean;
import model.ProdottoGenereBean;
import model.ProdottoPiattaformaBean;
import model.ProdottoViewBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.GenereDAO;
import dao.OffertaDAO;
import dao.PiattaformaDAO;
import dao.ProdottoDAO;
import dao.ProdottoGenereDAO;
import dao.ProdottoPiattaformaDAO;

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

		ProdottoDAO prodottoDAO = new ProdottoDAO();
		GenereDAO genereDAO = new GenereDAO();
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();

		ArrayList<ProdottoViewBean> prodottiView = prodottoDAO.doRetrieveAllView();
		ArrayList<GenereBean> generi = genereDAO.doRetrieveAll();
		ArrayList<PiattaformaBean> piattaforme = piattaformaDAO.doRetrieveAll();

		request.setAttribute("prodotti", prodottiView);
		request.setAttribute("generi", generi);
		request.setAttribute("piattaforme", piattaforme);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gestioneProdotti.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null || !"ADMIN".equals(utente.getRuolo())) {
			request.setAttribute("errore", "Effettuare il login come admin per accedere alla dashboard");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(idProdotto);
		
		//controlla se il prodotto non esiste
		if(prodotto == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gestioneProdotti.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
		GenereDAO genereDAO = new GenereDAO();
		
		ProdottoViewBean prodottoView = prodottoDAO.doRetrieveViewByKey(idProdotto);
		ArrayList<PiattaformaBean> piattaforme = piattaformaDAO.doRetrieveAll();
		ArrayList<GenereBean> generi = genereDAO.doRetrieveAll();
		
		request.setAttribute("prodotto", prodottoView);
		request.setAttribute("piattaforme", piattaforme);
		request.setAttribute("generi", generi);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/modificaProdotto.jsp");
		dispatcher.forward(request, response);
	}

}
