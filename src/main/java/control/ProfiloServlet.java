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

@WebServlet("/Profilo")
public class ProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProfiloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null) {
			session.setAttribute("erroreLogin", "Effettuare il login per accedere al profilo");
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/profilo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
