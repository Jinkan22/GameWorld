package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

import java.io.IOException;
import java.sql.Date;

import dao.ProdottoDAO;

/**
 * Servlet implementation class ModificaProdottoServlet
 */
@WebServlet("/ModificaProdottoServlet")
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		
		ProdottoDAO dao = new ProdottoDAO();
		ProdottoBean prodotto = dao.doRetrieveByKey(idProdotto);
		
		if(prodotto == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/GestioneProdottiServlet");
			dispatcher.forward(request, response);
			return;
		}
		
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		String quantitaDisponibile = request.getParameter("quantitaDisponibile");
		String immagine = request.getParameter("immagine");
		String dataUscita = request.getParameter("dataUscita");
		String sviluppatore = request.getParameter("sviluppatore");
		
		if(nome != null && !nome.isEmpty())
			prodotto.setNome(nome);
		
		if(descrizione != null && !descrizione.isEmpty())
			prodotto.setDescrizione(descrizione);
		
		if(prezzo != null && !prezzo.isEmpty())
			prodotto.setPrezzo(Float.parseFloat(prezzo));
		
		if(quantitaDisponibile != null && !quantitaDisponibile.isEmpty())
			prodotto.setQuantitaDisponibile(Integer.parseInt(quantitaDisponibile));
		
		if(immagine != null && !immagine.isEmpty())
			prodotto.setImmagine(immagine);
		
		if(dataUscita != null && !dataUscita.isEmpty())
			prodotto.setDataUscita(Date.valueOf(dataUscita));
		
		if(sviluppatore != null && !sviluppatore.isEmpty())
			prodotto.setSviluppatore(sviluppatore);
		
		dao.doUpdate(prodotto);
		
		response.sendRedirect(request.getContextPath() + "/PaginaProdottoServlet?idProdotto=" + idProdotto);
	}

}
