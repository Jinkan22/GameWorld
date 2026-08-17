package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.UtenteDAO;

/**
 * Servlet implementation class GestioneUtentiServlet
 */
@WebServlet("/GestioneUtenti")
public class GestioneUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneUtentiServlet() {
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
		
		UtenteDAO dao = new UtenteDAO();
		ArrayList<UtenteBean> utenti = dao.doRetrieveAll();
		
		request.setAttribute("utenti", utenti);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/gestioneUtenti.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean admin = (UtenteBean) session.getAttribute("utente");
		
		if(admin == null || !"ADMIN".equals(admin.getRuolo())) {
			request.setAttribute("erroreLogin", "Effettuare il login come admin per accedere alla dashboard");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginRegistrazione.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		int idUtente = Integer.parseInt(request.getParameter("idUtente"));
		
		UtenteDAO dao = new UtenteDAO();
		UtenteBean utente = dao.doRetrieveByKey(idUtente);
		
		//controlla se l'utente non esiste
		if(utente == null) {
			session.setAttribute("errore", "L'utente selezionato non esiste");
			
			response.sendRedirect(request.getContextPath() + "/GestioneUtenti");
			return;
		}
		
		//controlla se l'admin sta cercando di modificare il suo ruolo
		if(utente.getIdUtente() == admin.getIdUtente()) {
			session.setAttribute("errore", "Non puoi modificare il tuo stesso ruolo");
			
			response.sendRedirect(request.getContextPath() + "/GestioneUtenti");
			return;
		}
		
		if("ADMIN".equals(utente.getRuolo()))
			utente.setRuolo("USER");
		else utente.setRuolo("ADMIN");
		
		dao.doUpdate(utente);
		
		response.sendRedirect(request.getContextPath() + "/GestioneUtenti");
	}

}
