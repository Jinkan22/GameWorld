package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoViewBean;

import java.io.IOException;

import dao.ProdottoDAO;

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
		ProdottoViewBean prodottoView = prodottoDAO.doRetrieveViewDisponibileByKey(idProdotto);

		// controlla se il prodotto non esiste
		if(prodottoView == null) {
			request.setAttribute("errore", "Il prodotto selezionato non esiste");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/CatalogoServlet");
			dispatcher.forward(request, response);
			return;
		}

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
