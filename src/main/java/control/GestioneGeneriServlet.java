package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GenereBean;
import model.UtenteBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.GenereDAO;

@WebServlet("/GestioneGeneri")
public class GestioneGeneriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GestioneGeneriServlet() {
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
		
		GenereDAO genereDAO = new GenereDAO();
		ArrayList<GenereBean> generi = genereDAO.doRetrieveAll();
		
		request.setAttribute("generi", generi);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/gestioneGeneri.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null || !"ADMIN".equals(utente.getRuolo())) {
			session.setAttribute("erroreLogin", "Effettuare il login come admin per accedere alla dashboard");
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		
		String azione = request.getParameter("azione");
		GenereDAO genereDAO = new GenereDAO();
		
		switch(azione) {
		case "aggiungiGenere": {
			GenereBean genere = new GenereBean();
			String nomeGenere = request.getParameter("nomeGenere");
			
			genere.setNomeGenere(nomeGenere);
			genereDAO.doSave(genere);
			break;
			}
		case "eliminaGenere": {
			int idGenere = Integer.parseInt(request.getParameter("idGenere"));

			if(genereDAO.isUtilizzato(idGenere)) {
		    	request.setAttribute("errore", "Il genere selezionato è ancora associato a uno o più prodotti.");
		        doGet(request, response);
		        return;
		    }
			
			genereDAO.doDelete(idGenere);
			break;
			}
		}
		
		doGet(request, response);
	}

}
