package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrdineViewBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.OrdineDAO;

@WebServlet("/StoricoOrdini")
public class StoricoOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StoricoOrdiniServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean)session.getAttribute("utente");
		
		if(utente == null) {
			session.setAttribute("erroreLogin", "Effettuare il login per visualizzare lo storico degli ordini");
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		
		OrdineDAO dao = new OrdineDAO();
		
		ArrayList<OrdineViewBean> ordiniView = dao.doRetrieveViewByIdUtente(utente.getIdUtente());
		
		request.setAttribute("ordini", ordiniView);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/storicoOrdini.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
