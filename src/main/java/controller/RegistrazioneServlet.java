package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UtenteBean;

import java.io.IOException;

import dao.UtenteDAO;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
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
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String indirizzo=request.getParameter("indirizzo");
		String MetodoDiPagamento=request.getParameter("pagamento");
		
		UtenteBean utente=new UtenteBean();
		
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		utente.setPassword(password);
		utente.setIndirizzo(indirizzo);
		utente.setMetodoPagamento(MetodoDiPagamento);
		
		UtenteDAO dao=new UtenteDAO();
		if(!dao.doSave(utente)) {
			request.setAttribute("errore", "Errore nella registrazione");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/registrazione.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		utente=dao.doRetrieveByEmailAndPassword(email, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("utente",utente);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
