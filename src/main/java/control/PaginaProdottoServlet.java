package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ProdottoViewBean;

import java.io.IOException;

import dao.ProdottoDAO;

@WebServlet("/PaginaProdotto")
public class PaginaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PaginaProdottoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

		ProdottoDAO prodottoDAO = new ProdottoDAO();
		ProdottoViewBean prodottoView = prodottoDAO.doRetrieveViewDisponibileByKey(idProdotto);

		// controlla se il prodotto non esiste
		if(prodottoView == null) {
			session.setAttribute("errore", "Il prodotto selezionato non esiste");
			response.sendRedirect(request.getContextPath() + "/Catalogo");
			return;
		}

		request.setAttribute("prodotto", prodottoView);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/paginaProdotto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
