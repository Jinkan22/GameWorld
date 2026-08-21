package control;

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
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import dao.GenereDAO;
import dao.OffertaDAO;
import dao.PiattaformaDAO;
import dao.ProdottoDAO;
import dao.ProdottoGenereDAO;
import dao.ProdottoPiattaformaDAO;

/**
 * Servlet implementation class ModificaProdottoServlet
 */
@WebServlet("/ModificaProdotto")
public class ModificaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdottoServlet() {
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
			request.setAttribute("erroreLogin", "Effettuare il login come admin per accedere alla dashboard");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginRegistrazione.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//sposta l'eventuale messaggio dalla session alla request
		String messaggio = (String) session.getAttribute("messaggio");
		if(messaggio != null) {
			request.setAttribute("messaggio", messaggio);
			session.removeAttribute("messaggio");
		}
		
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(idProdotto);
		
		//controlla se il prodotto non esiste
		if(prodotto == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/gestioneProdotti.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
		GenereDAO genereDAO = new GenereDAO();
		OffertaDAO offertaDAO = new OffertaDAO();
		
		ProdottoViewBean prodottoView = prodottoDAO.doRetrieveViewByKey(idProdotto);
		ArrayList<PiattaformaBean> piattaforme = piattaformaDAO.doRetrieveAll();
		ArrayList<GenereBean> generi = genereDAO.doRetrieveAll();
		ArrayList<OffertaBean> offerte = offertaDAO.doRetrieveByIdProdotto(idProdotto);
		
		request.setAttribute("prodotto", prodottoView);
		request.setAttribute("piattaforme", piattaforme);
		request.setAttribute("generi", generi);
		request.setAttribute("offerte", offerte);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/modificaProdotto.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(idProdotto);
		
		if(prodotto == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/GestioneProdotti");
			dispatcher.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		String azione = request.getParameter("azione");
		
		switch(azione) {
		case "Modifica prodotto": {
			String nome = request.getParameter("nome");
			String descrizione = request.getParameter("descrizione");
			String prezzo = request.getParameter("prezzo");
			String immagine = request.getParameter("immagine");
			String dataUscita = request.getParameter("dataUscita");
			String sviluppatore = request.getParameter("sviluppatore");
			
			if(nome != null && !nome.isEmpty())
				prodotto.setNome(nome);
			
			if(descrizione != null && !descrizione.isEmpty())
				prodotto.setDescrizione(descrizione);
			
			if(prezzo != null && !prezzo.isEmpty())
				prodotto.setPrezzo(new BigDecimal(prezzo));
			
			if(immagine != null && !immagine.isEmpty())
				prodotto.setImmagine(immagine);
			
			if(dataUscita != null && !dataUscita.isEmpty())
				prodotto.setDataUscita(Date.valueOf(dataUscita));
			
			if(sviluppatore != null && !sviluppatore.isEmpty())
				prodotto.setSviluppatore(sviluppatore);
			
			prodottoDAO.doUpdate(prodotto);
			
			session.setAttribute("messaggio", "Prodotto modificato con successo!");
			break;
			}
		case "Elimina prodotto": {
			prodottoDAO.doDelete(idProdotto);
			
			response.sendRedirect(request.getContextPath() + "/GestioneProdotti");
			return;
			}
		case "Crea offerta": {
			String percentualeSconto = request.getParameter("percentualeSconto");
			String dataInizio = request.getParameter("dataInizio");
			String dataFine = request.getParameter("dataFine");
			
			OffertaDAO offertaDAO = new OffertaDAO();
			OffertaBean nuovaOfferta = new OffertaBean();
			
			nuovaOfferta.setPercentualeSconto(Integer.parseInt(percentualeSconto));
			nuovaOfferta.setDataInizio(Date.valueOf(dataInizio));
			nuovaOfferta.setDataFine(Date.valueOf(dataFine));
			nuovaOfferta.setIdProdotto(idProdotto);
			
			//controlla che non ci siano già offerte che si sovrappongano
			if(offertaDAO.esisteOffertaSovrapposta(idProdotto, nuovaOfferta.getDataInizio(), nuovaOfferta.getDataFine())) {
				session.setAttribute("messaggio", "Il prodotto selezionato è già in offerta nelle date selezionate");
				break;
			}
			
			offertaDAO.doSave(nuovaOfferta);
			
			session.setAttribute("messaggio", "Offerta creata con successo!");
			break;
			}
		case "Elimina offerta": {
			String idOfferta = request.getParameter("idOfferta");
			
			if(idOfferta == null || idOfferta.isEmpty())
				break;
			
			OffertaDAO offertaDAO = new OffertaDAO();
			
			offertaDAO.doDelete(Integer.parseInt(idOfferta));
			
			session.setAttribute("messaggio", "Offerta eliminata con successo!");
			break;
			}
		case "Aggiungi piattaforma": {
			String idPiattaforma = request.getParameter("idPiattaforma");
			
			if(idPiattaforma == null || idPiattaforma.isEmpty())
				break;
			
			ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
			ProdottoPiattaformaBean prodottoPiattaforma = new ProdottoPiattaformaBean();
			
			prodottoPiattaforma.setIdProdotto(idProdotto);
			prodottoPiattaforma.setIdPiattaforma(Integer.parseInt(idPiattaforma));
			
			//controlla se la relazione già esiste
			if(prodottoPiattaformaDAO.esiste(prodottoPiattaforma.getIdProdotto(), prodottoPiattaforma.getIdPiattaforma())) {
				session.setAttribute("messaggio", "La piattaforma è già associata a questo prodotto");
				break;
			}
			
			prodottoPiattaformaDAO.doSave(prodottoPiattaforma);
			
			session.setAttribute("messaggio", "Piattaforma aggiunta con successo!");
			break;
			}
		case "Modifica quantità": {
			String idPiattaforma = request.getParameter("idPiattaforma");
			String quantitaPiattaforma = request.getParameter("quantitaPiattaforma");
			
			if(idPiattaforma == null || idPiattaforma.isEmpty())
				break;
			
			ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
			ProdottoPiattaformaBean prodottoPiattaforma = prodottoPiattaformaDAO.doRetrieveByKey(idProdotto, Integer.parseInt(idPiattaforma));

			prodottoPiattaforma.setQuantitaDisponibile(Integer.parseInt(quantitaPiattaforma));
			prodottoPiattaformaDAO.doUpdate(prodottoPiattaforma);
			
			session.setAttribute("messaggio", "Quantità modificata con successo!");
			break;
			}
		case "Elimina piattaforma": {
			String idPiattaforma = request.getParameter("idPiattaforma");
			
			if(idPiattaforma == null || idPiattaforma.isEmpty())
				break;
			
			ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
			
			//se la piattaforma è già usata non viene eliminata
			if(!prodottoPiattaformaDAO.doDelete(idProdotto, Integer.parseInt(idPiattaforma))) {
				session.setAttribute("messaggio", "La piattaforma non può essere eliminata perché è già utilizzata.");
				break;
			}
			
			session.setAttribute("messaggio", "Piattaforma eliminata con successo!");
			break;
			}
		case "Elimina genere": {
			String idGenere = request.getParameter("idGenere");
			
			if(idGenere == null || idGenere.isEmpty())
				break;
			
			ProdottoGenereDAO prodottoGenereDAO = new ProdottoGenereDAO();
			
			prodottoGenereDAO.doDelete(idProdotto, Integer.parseInt(idGenere));
			
			session.setAttribute("messaggio", "Genere eliminato con successo!");
			break;
			}
		case "Aggiungi genere": {
			String idGenere = request.getParameter("idGenere");
			
			if(idGenere == null || idGenere.isEmpty())
				break;
			
			ProdottoGenereDAO prodottoGenereDAO = new ProdottoGenereDAO();
			ProdottoGenereBean prodottoGenere = new ProdottoGenereBean();
			
			prodottoGenere.setIdProdotto(idProdotto);
			prodottoGenere.setIdGenere(Integer.parseInt(idGenere));
			
			//controlla se la relazione già esiste
			if(prodottoGenereDAO.esiste(prodottoGenere.getIdProdotto(), prodottoGenere.getIdGenere())) {
				session.setAttribute("messaggio", "Il genere è già associato a questo prodotto");
				break;
			}
			
			prodottoGenereDAO.doSave(prodottoGenere);
			
			session.setAttribute("messaggio", "Genere aggiunto con successo!");
			break;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/ModificaProdotto?idProdotto=" + idProdotto);
	}
}
