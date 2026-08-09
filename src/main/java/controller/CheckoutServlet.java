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
		
		//errore se l'utente non ha effettuato il login
		if(utente == null) {
			request.setAttribute("errore", "Effettuare il login per completare l'acquisto");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		OrdineDAO ordineDAO = new OrdineDAO();
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
		DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();
		
		ArrayList<ElementoCarrelloBean> carrello = elementoCarrelloDAO.doRetrieveByIdUtente(utente.getIdUtente());
		OrdineBean ordine = new OrdineBean();
		
		//errore se il carrello è vuoto
		if(carrello == null || carrello.isEmpty()) {
			response.sendRedirect(request.getContextPath()+"/CarrelloServlet");
			return;
		}
		
		//controlla che per ogni elemento del carrello ci sia la disponibilità del prodotto richiesta
		for(ElementoCarrelloBean elemento : carrello) {
			if(!CarrelloUtils.checkDisponibilita(elemento, elemento.getQuantita())) {
				
				ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());
				
				if(prodotto.getQuantitaDisponibile() <= 0)
					elementoCarrelloDAO.doDelete(elemento.getIdElementoCarrello());
				else {
					elemento.setQuantita(prodotto.getQuantitaDisponibile());
					elementoCarrelloDAO.doUpdate(elemento);
				}
				
				request.setAttribute("errore", "La quantità di prodotti richiesta non è disponibile");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CarrelloServlet");
				dispatcher.forward(request, response);
				return;
			}
		}
		
		//crea l'ordine nel database
		ordine.setDataOrdine(new Timestamp(System.currentTimeMillis()));
		ordine.setIdUtente(utente.getIdUtente());
		
		ordineDAO.doSave(ordine);
		
		float totale = 0;
		
		//crea tutti i dettagli ordine nel database
		for(ElementoCarrelloBean elemento : carrello) {
			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
			
			ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());
			
			totale += prodotto.getPrezzo() * elemento.getQuantita();
			
			dettaglioOrdine.setQuantita(elemento.getQuantita());
			dettaglioOrdine.setPrezzoAcquisto(prodotto.getPrezzo());
			dettaglioOrdine.setIdOrdine(ordine.getIdOrdine());
			dettaglioOrdine.setIdProdotto(elemento.getIdProdotto());
			
			dettaglioOrdineDAO.doSave(dettaglioOrdine);
			
			prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() - elemento.getQuantita());
			prodottoDAO.doUpdate(prodotto);
		}
		
		ordine.setTotale(totale);
		ordineDAO.doUpdate(ordine);
		
		//svuota il carrello dopo l'acquisto
		for(ElementoCarrelloBean elemento : carrello) {
			elementoCarrelloDAO.doDelete(elemento.getIdElementoCarrello());
		}
		
		response.sendRedirect(request.getContextPath()+"/StoricoOrdiniServlet");
	}
}
