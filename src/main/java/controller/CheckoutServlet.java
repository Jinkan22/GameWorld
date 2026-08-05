package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DettaglioOrdineBean;
import model.ElementoCarrelloBean;
import model.ElementoCarrelloViewBean;
import model.IndirizzoBean;
import model.MetodoPagamentoBean;
import model.OrdineBean;
import model.ProdottoBean;
import model.UtenteBean;
import utils.CarrelloUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dao.DettaglioOrdineDAO;
import dao.ElementoCarrelloDAO;
import dao.IndirizzoDAO;
import dao.MetodoPagamentoDAO;
import dao.OrdineDAO;
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
			request.setAttribute("errore", "Effettuare il login per completare l'acquisto");
			
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
		HttpSession session = request.getSession();
		
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null) {
			request.setAttribute("errore", "Effettuare il login per completare l'acquisto");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
		ArrayList<ElementoCarrelloBean> carrello = elementoCarrelloDAO.doRetrieveByIdUtente(utente.getIdUtente());
		
		if(carrello == null || carrello.isEmpty()) {
			response.sendRedirect(request.getContextPath()+"/CarrelloServlet");
			return;
		}
		
		OrdineDAO ordineDAO = new OrdineDAO();
		OrdineBean ordine = new OrdineBean();
		
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		
		ordine.setDataOrdine(new Timestamp(System.currentTimeMillis()));
		ordine.setStatoOrdine("IN ATTESA");
		ordine.setIdUtente(utente.getIdUtente());
		
		ordineDAO.doSave(ordine);
		System.out.println("ID ordine dopo save: " + ordine.getIdOrdine());
		
		DettaglioOrdineDAO elementoOrdineDAO = new DettaglioOrdineDAO();
		
		float totale = 0;
		
		for(ElementoCarrelloBean elemento : carrello) {
			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
			
			ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());
			
			totale += prodotto.getPrezzo() * elemento.getQuantita();
			
			dettaglioOrdine.setQuantita(elemento.getQuantita());
			dettaglioOrdine.setPrezzoAcquisto(prodotto.getPrezzo());
			dettaglioOrdine.setIdOrdine(ordine.getIdOrdine());
			dettaglioOrdine.setIdProdotto(elemento.getIdProdotto());
			
			elementoOrdineDAO.doSave(dettaglioOrdine);
		}
		
		ordine.setTotale(totale);
		
		boolean res = ordineDAO.doUpdate(ordine);
		
		System.out.println("ID ordine dopo update: " + ordine.getIdOrdine());
		System.out.println("Totale update: " + ordine.getTotale());
		System.out.println(res);
		
		for(ElementoCarrelloBean elemento : carrello) {
			elementoCarrelloDAO.doDelete(elemento.getIdElementoCarrello());
		}
		
		response.sendRedirect(request.getContextPath()+"/StoricoOrdiniServlet");
	}

}
