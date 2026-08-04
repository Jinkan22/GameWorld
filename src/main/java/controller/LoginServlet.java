package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ElementoCarrelloBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.ElementoCarrelloDAO;
import dao.UtenteDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UtenteDAO dao = new UtenteDAO();
		
		UtenteBean utente = dao.doRetrieveByEmailAndPassword(email, password);
			
		if(utente == null) {
			request.setAttribute("errore", "Email o password errati");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		
		session.setAttribute("utente", utente);
		
		versaCarrelloSessione(session, utente);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void versaCarrelloSessione(HttpSession session, UtenteBean utente) {
		
		ArrayList<ElementoCarrelloBean> carrello = (ArrayList<ElementoCarrelloBean>)session.getAttribute("carrello");
		
		if(carrello == null || carrello.isEmpty())
			return;
		
		ElementoCarrelloDAO dao = new ElementoCarrelloDAO();
		
		for(ElementoCarrelloBean elemento : carrello) {
			ElementoCarrelloBean elementoDB = dao.doRetrieveByIdUtenteAndIdProdotto(utente.getIdUtente(),elemento.getIdProdotto());
			
			if(elementoDB != null) {
				elementoDB.setQuantita(elementoDB.getQuantita()+elemento.getQuantita());
				dao.doUpdate(elementoDB);
			}
			else {
				elemento.setIdUtente(utente.getIdUtente());
				dao.doSave(elemento);
			}
		}
		
		session.removeAttribute("carrello");
	}

}
