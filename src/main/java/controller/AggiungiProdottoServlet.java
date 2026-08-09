package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ProdottoBean;
import model.UtenteBean;

import java.io.IOException;
import java.sql.Date;

import dao.ProdottoDAO;

/**
 * Servlet implementation class AggiungiProdottoServlet
 */
@WebServlet("/AggiungiProdottoServlet")
public class AggiungiProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiProdottoServlet() {
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
		
		response.sendRedirect(request.getContextPath() + "/jsp/aggiungiProdotto.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO dao = new ProdottoDAO();
		ProdottoBean prodotto = new ProdottoBean();
		
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		String quantitaDisponibile = request.getParameter("quantitaDisponibile");
		String immagine = request.getParameter("immagine");
		String dataUscita = request.getParameter("dataUscita");
		String sviluppatore = request.getParameter("sviluppatore");
		
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);
		prodotto.setPrezzo(Float.parseFloat(prezzo));
		prodotto.setQuantitaDisponibile(Integer.parseInt(quantitaDisponibile));
		prodotto.setImmagine(immagine);
		prodotto.setDataUscita(Date.valueOf(dataUscita));
		
		if(sviluppatore != null && !sviluppatore.isEmpty())
			prodotto.setSviluppatore(sviluppatore);
		
		dao.doSave(prodotto);
		
		response.sendRedirect(request.getContextPath() + "/PaginaProdottoServlet?idProdotto=" + prodotto.getIdProdotto());
	}

}
