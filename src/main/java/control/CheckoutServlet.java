package control;

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
import model.OffertaBean;
import model.OrdineBean;
import model.PiattaformaBean;
import model.ProdottoBean;
import model.ProdottoPiattaformaBean;
import model.UtenteBean;
import utils.CarrelloUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;

import dao.DettaglioOrdineDAO;
import dao.ElementoCarrelloDAO;
import dao.IndirizzoDAO;
import dao.MetodoPagamentoDAO;
import dao.OffertaDAO;
import dao.OrdineDAO;
import dao.PiattaformaDAO;
import dao.ProdottoDAO;
import dao.ProdottoPiattaformaDAO;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/Checkout")
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
			request.setAttribute("erroreLogin", "Effettuare il login per completare l'acquisto");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginRegistrazione.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
		IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
		MetodoPagamentoDAO metodoPagamentoDAO = new MetodoPagamentoDAO();

		ArrayList<ElementoCarrelloViewBean> carrelloView = elementoCarrelloDAO.doRetrieveViewByIdUtente(utente.getIdUtente());
		ArrayList<IndirizzoBean> indirizzi = indirizzoDAO.doRetrieveByIdUtente(utente.getIdUtente());
		ArrayList<MetodoPagamentoBean> metodiPagamento = metodoPagamentoDAO.doRetrieveByIdUtente(utente.getIdUtente());
		
		if(carrelloView == null || carrelloView.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/Carrello");
			return;
		}
		
		request.setAttribute("carrello", carrelloView);
		request.setAttribute("indirizzi", indirizzi);
		request.setAttribute("metodiPagamento", metodiPagamento);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/checkout.jsp");
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
			request.setAttribute("erroreLogin", "Effettuare il login per completare l'acquisto");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginRegistrazione.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		OrdineDAO ordineDAO = new OrdineDAO();
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		OffertaDAO offertaDAO = new OffertaDAO();
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
		ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
		ElementoCarrelloDAO elementoCarrelloDAO = new ElementoCarrelloDAO();
		DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();
		
		ArrayList<ElementoCarrelloBean> carrello = elementoCarrelloDAO.doRetrieveByIdUtente(utente.getIdUtente());
		OrdineBean ordine = new OrdineBean();
		
		//errore se il carrello è vuoto
		if(carrello == null || carrello.isEmpty()) {
			response.sendRedirect(request.getContextPath()+"/Carrello");
			return;
		}
		
		//controlla che per ogni elemento del carrello ci sia la disponibilità del prodotto richiesta
		for(ElementoCarrelloBean elemento : carrello) {
			if(!CarrelloUtils.checkDisponibilita(elemento, elemento.getQuantita())) {
				
				ProdottoPiattaformaBean prodottoPiattaforma = prodottoPiattaformaDAO.doRetrieveByKey(elemento.getIdProdotto(), elemento.getIdPiattaforma());
				
				if(prodottoPiattaforma.getQuantitaDisponibile() <= 0)
					elementoCarrelloDAO.doDelete(elemento.getIdElementoCarrello());
				else {
					elemento.setQuantita(prodottoPiattaforma.getQuantitaDisponibile());
					elementoCarrelloDAO.doUpdate(elemento);
				}
				
				session.setAttribute("errore", "La quantità di prodotti richiesta non è disponibile");
				
				response.sendRedirect(request.getContextPath() + "/Carrello");
				return;
			}
		}
		
		//ricava il totale
		BigDecimal totale = BigDecimal.ZERO;
		for(ElementoCarrelloBean elemento : carrello) {
			ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());

	        BigDecimal prezzo = prodotto.getPrezzo();

	        // controlla se esiste un'offerta attiva
	        OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(elemento.getIdProdotto());

	        if(offerta != null) {
	            prezzo = prezzo.multiply(BigDecimal.ONE.subtract(
                    	BigDecimal.valueOf(offerta.getPercentualeSconto()).divide(
                    	BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP);   
	        }
	        
	        totale = totale.add(prezzo.multiply(BigDecimal.valueOf(elemento.getQuantita())));
	    }
		
		//ricava l'indirizzo di fatturazione
		int idIndirizzo = Integer.parseInt(request.getParameter("indirizzo"));
		IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
		IndirizzoBean indirizzo = indirizzoDAO.doRetrieveByKey(idIndirizzo);
		String indirizzoFatturazione = 
				indirizzo.getVia() + ", " +
				indirizzo.getCap() + ", " +
				indirizzo.getCitta() + ", " +
				indirizzo.getProvincia() + ", " +
				indirizzo.getPaese();
		
		//crea l'ordine nel database
		ordine.setAcquirente(utente.getNome() + " " +  utente.getCognome());
		ordine.setDataOrdine(new Timestamp(System.currentTimeMillis()));
		ordine.setIndirizzoFatturazione(indirizzoFatturazione);
		ordine.setIdUtente(utente.getIdUtente());
		
		totale = totale.setScale(2, RoundingMode.HALF_UP);
		ordine.setTotale(totale);
		
		ordineDAO.doSave(ordine);
		
		//crea tutti i dettagli ordine nel database
		for(ElementoCarrelloBean elemento : carrello) {
			DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
			
			ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(elemento.getIdProdotto());
			PiattaformaBean piattaforma = piattaformaDAO.doRetrieveByKey(elemento.getIdPiattaforma());
			ProdottoPiattaformaBean prodottoPiattaforma = prodottoPiattaformaDAO.doRetrieveByKey(elemento.getIdProdotto(), elemento.getIdPiattaforma());
			
			dettaglioOrdine.setQuantita(elemento.getQuantita());
			dettaglioOrdine.setIdProdotto(prodotto.getIdProdotto());
			dettaglioOrdine.setNomeProdotto(prodotto.getNome());
			dettaglioOrdine.setNomePiattaforma(piattaforma.getNomePiattaforma());
			dettaglioOrdine.setIdOrdine(ordine.getIdOrdine());
			
			BigDecimal prezzo = prodotto.getPrezzo();

	        // controlla se esiste un'offerta attiva
	        OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(elemento.getIdProdotto());

	        if(offerta != null) {
	            prezzo = prezzo.multiply(BigDecimal.ONE.subtract(
                    	BigDecimal.valueOf(offerta.getPercentualeSconto()).divide(
                    	BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP);   
	        }
	        
	        dettaglioOrdine.setPrezzoAcquisto(prezzo);
			
			dettaglioOrdineDAO.doSave(dettaglioOrdine);
			
			prodottoPiattaforma.setQuantitaDisponibile(prodottoPiattaforma.getQuantitaDisponibile() - elemento.getQuantita());
			prodottoPiattaformaDAO.doUpdate(prodottoPiattaforma);
		}
		
		//svuota il carrello dopo l'acquisto
		for(ElementoCarrelloBean elemento : carrello) {
			elementoCarrelloDAO.doDelete(elemento.getIdElementoCarrello());
		}
		
		response.sendRedirect(request.getContextPath()+"/StoricoOrdini");
	}
}
