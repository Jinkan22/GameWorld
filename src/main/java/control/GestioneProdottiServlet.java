package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GenereBean;
import model.PiattaformaBean;
import model.ProdottoViewBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.GenereDAO;
import dao.PiattaformaDAO;
import dao.ProdottoDAO;

@WebServlet("/GestioneProdotti")
public class GestioneProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GestioneProdottiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");

		if(utente == null || !"ADMIN".equals(utente.getRuolo())) {
			session.setAttribute("erroreLogin", "Effettuare il login come admin per accedere alla dashboard");
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		
		String errore = (String) session.getAttribute("errore");
		if(errore != null && !errore.isEmpty()) {
			request.setAttribute("errore", errore);
			session.removeAttribute("errore");
		}

		ProdottoDAO prodottoDAO = new ProdottoDAO();
		GenereDAO genereDAO = new GenereDAO();
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();

		ArrayList<ProdottoViewBean> prodottiView = prodottoDAO.doRetrieveAllView();
		ArrayList<GenereBean> generi = genereDAO.doRetrieveAll();
		ArrayList<PiattaformaBean> piattaforme = piattaformaDAO.doRetrieveAll();

		request.setAttribute("prodotti", prodottiView);
		request.setAttribute("generi", generi);
		request.setAttribute("piattaforme", piattaforme);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/gestioneProdotti.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}
}
