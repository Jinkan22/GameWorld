package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;

import model.UtenteBean;

import java.io.IOException;

import dao.UtenteDAO;

@WebServlet("/ModificaProfilo")
public class ModificaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificaProfiloServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("dataNascita");
		String numeroTelefono = request.getParameter("numeroTelefono");
		
		HttpSession session = request.getSession();
		
		UtenteBean utente = (UtenteBean)session.getAttribute("utente");
		
		if(utente == null) {
			session.setAttribute("erroreLogin", "Login necessario per la modifica del profilo");
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		
		UtenteDAO dao=new UtenteDAO();
		if(nome != null && !nome.isEmpty())
			utente.setNome(nome);
		
		if(cognome != null && !cognome.isEmpty())
			utente.setCognome(cognome);
		
		if(dataNascita != null && !dataNascita.isEmpty())
			utente.setDataNascita(Date.valueOf(dataNascita));
		
		if(numeroTelefono != null && !numeroTelefono.isEmpty())
			utente.setNumeroTelefono(numeroTelefono);
		
		
		dao.doUpdate(utente);
		session.setAttribute("utente", utente);

		response.sendRedirect(request.getContextPath() + "/Profilo");
	}

}
