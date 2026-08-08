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
		OffertaDAO offertaDAO = new OffertaDAO();
		ProdottoGenereDAO prodottoGenereDAO = new ProdottoGenereDAO();
		ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
		GenereDAO genereDAO = new GenereDAO();
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
		
		ArrayList<ProdottoBean> prodotti = prodottoDAO.doRetrieveAll();
		ArrayList<ProdottoViewBean> prodottiView = new ArrayList<ProdottoViewBean>();
		
		for(ProdottoBean prodotto : prodotti) {
			OffertaBean offerta = offertaDAO.doRetrieveAttivaByIdProdotto(prodotto.getIdProdotto());
			ArrayList<GenereBean> generiProdotto = prodottoGenereDAO.doRetrieveByIdProdotto(prodotto.getIdProdotto());
			ArrayList<PiattaformaBean> piattaformeProdotto = prodottoPiattaformaDAO.doRetrieveByIdProdotto(prodotto.getIdProdotto());

			ProdottoViewBean prodottoView = new ProdottoViewBean();
			
			prodottoView.setProdotto(prodotto);
			prodottoView.setOfferta(offerta);
			if(prodottoView.getOfferta() != null)
				prodottoView.setPrezzoScontato(prodotto.getPrezzo() / 100 * (100 - offerta.getPercentualeSconto()));
			else prodottoView.setPrezzoScontato(0);
			prodottoView.setGeneri(generiProdotto);
			prodottoView.setPiattaforme(piattaformeProdotto);
			
			prodottiView.add(prodottoView);
		}
		
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
		
		String azione = request.getParameter("azione");
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		
		ProdottoDAO dao = new ProdottoDAO();
		ProdottoBean prodotto = dao.doRetrieveByKey(idProdotto);
		
		//controlla se il prodotto non esiste
		if(prodotto == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gestioneProdotti.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		switch(azione) {
		case "Modifica prodotto": {
			request.setAttribute("prodotto", prodotto);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/modificaProdotto.jsp");
			dispatcher.forward(request, response);
			break;
			}
		case "Elimina prodotto": {
			prodotto.setQuantitaDisponibile(0);
			dao.doUpdate(prodotto);
			response.sendRedirect(request.getContextPath() + "/GestioneProdottiServlet");
			break;
			}
		case "Crea offerta": {
			request.setAttribute("prodotto", prodotto);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/creazioneOfferta.jsp");
			dispatcher.forward(request, response);
			break;
			}
		case "Aggiungi piattaforma": {
			String idPiattaforma = request.getParameter("idPiattaforma");
			
			if(idPiattaforma == null || idPiattaforma.isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/GestioneProdottiServlet");
				break;
			}
			
			ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
			ProdottoPiattaformaBean prodottoPiattaforma = new ProdottoPiattaformaBean();
			
			prodottoPiattaforma.setIdProdotto(idProdotto);
			prodottoPiattaforma.setIdPiattaforma(Integer.parseInt(idPiattaforma));
			
			//controlla se la relazione già esiste
			if(prodottoPiattaformaDAO.esiste(prodottoPiattaforma.getIdProdotto(), prodottoPiattaforma.getIdPiattaforma())) {
				response.sendRedirect(request.getContextPath() + "/GestioneProdottiServlet");
				break;
			}
			
			prodottoPiattaformaDAO.doSave(prodottoPiattaforma);
			
			response.sendRedirect(request.getContextPath() + "/PaginaProdottoServlet?idProdotto=" + idProdotto);
			break;
			}
		case "Aggiungi genere": {
			String idGenere = request.getParameter("idGenere");
			
			if(idGenere == null || idGenere.isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/GestioneProdottiServlet");
				break;
			}
			
			ProdottoGenereDAO prodottoGenereDAO = new ProdottoGenereDAO();
			ProdottoGenereBean prodottoGenere = new ProdottoGenereBean();
			
			prodottoGenere.setIdProdotto(idProdotto);
			prodottoGenere.setIdGenere(Integer.parseInt(idGenere));
			
			//controlla se la relazione già esiste
			if(prodottoGenereDAO.esiste(prodottoGenere.getIdProdotto(), prodottoGenere.getIdGenere())) {
				response.sendRedirect(request.getContextPath() + "/GestioneProdottiServlet");
				break;
			}
			
			prodottoGenereDAO.doSave(prodottoGenere);
			
			response.sendRedirect(request.getContextPath() + "/PaginaProdottoServlet?idProdotto=" + idProdotto);
			break;
			}
		}
	}

}
