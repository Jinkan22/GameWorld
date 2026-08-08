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
 * Servlet implementation class PaginaProdottoServlet
 */
@WebServlet("/PaginaProdottoServlet")
public class PaginaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

		ProdottoDAO prodottoDAO = new ProdottoDAO();
		OffertaDAO offertaDAO = new OffertaDAO();
		ProdottoGenereDAO prodottoGenereDAO = new ProdottoGenereDAO();
		ProdottoPiattaformaDAO prodottoPiattaformaDAO = new ProdottoPiattaformaDAO();
		GenereDAO genereDAO = new GenereDAO();
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
		
		ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(idProdotto);
		
		//controlla se l'ordine non esiste
		if(prodotto == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CatalogoServlet");
			dispatcher.forward(request, response);
			return;
		}
		
		//se l'utente è admin imposta generi e piattaforme come attributi della request
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		if(utente != null && "ADMIN".equals(utente.getRuolo())) {
			ArrayList<GenereBean> generi = genereDAO.doRetrieveAll();
			ArrayList<PiattaformaBean> piattaforme = piattaformaDAO.doRetrieveAll();
			
			request.setAttribute("generi", generi);
			request.setAttribute("piattaforme", piattaforme);
		}
		
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
		
		request.setAttribute("prodotto", prodottoView);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/paginaProdotto.jsp");
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
