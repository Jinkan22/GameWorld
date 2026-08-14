package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OffertaBean;
import model.ProdottoBean;
import model.UtenteBean;

import java.io.IOException;
import java.sql.Date;

import dao.OffertaDAO;
import dao.ProdottoDAO;

/**
 * Servlet implementation class CreazioneOffertaServlet
 */
@WebServlet("/CreazioneOffertaServlet")
public class CreazioneOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreazioneOffertaServlet() {
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
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null || !"ADMIN".equals(utente.getRuolo())) {
			request.setAttribute("erroreLogin", "Effettuare il login come admin per accedere alla dashboard");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/loginRegistrazione.jsp");
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
		
		String percentualeSconto = request.getParameter("percentualeSconto");
		String dataInizio = request.getParameter("dataInizio");
		String dataFine = request.getParameter("dataFine");
		
		OffertaDAO offertaDAO = new OffertaDAO();
		OffertaBean nuovaOfferta = new OffertaBean();
		
		nuovaOfferta.setPercentualeSconto(Integer.parseInt(percentualeSconto));
		nuovaOfferta.setDataInizio(Date.valueOf(dataInizio));
		nuovaOfferta.setDataFine(Date.valueOf(dataFine));
		nuovaOfferta.setIdProdotto(prodotto.getIdProdotto());
		
		//controlla che non ci siano già offerte che si sovrappongano
		if(offertaDAO.esisteOffertaSovrapposta(idProdotto, nuovaOfferta.getDataInizio(), nuovaOfferta.getDataFine())) {
			request.getSession().setAttribute("errore", "Il prodotto selezionato è già in offerta nelle date selezionate");
			
			response.sendRedirect(request.getContextPath() + "/GestioneOfferteServlet");
			return;
		}
		
		offertaDAO.doSave(nuovaOfferta);
		
		response.sendRedirect(request.getContextPath() + "/GestioneOfferteServlet");
	}

}
