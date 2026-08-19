package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.IndirizzoBean;
import model.UtenteBean;

import java.io.IOException;

import dao.IndirizzoDAO;

/**
 * Servlet implementation class IndirizzoServlet
 */
@WebServlet("/AggiungiRimuoviIndirizzo")
public class AggiungiRimuoviIndirizzoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiRimuoviIndirizzoServlet() {
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
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null) {
			request.setAttribute("erroreLogin", "Effettuare il login per completare l'acquisto");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginRegistrazione.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		String azione = request.getParameter("azione");
		
		IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
		
		switch(azione) {
		case "aggiungi": {
			String via = request.getParameter("via");
			String cap = request.getParameter("cap");
			String citta = request.getParameter("citta");
			String provincia = request.getParameter("provincia");
			String paese = request.getParameter("paese");
			
			IndirizzoBean indirizzo = new IndirizzoBean();
			
			indirizzo.setVia(via);
			indirizzo.setCap(cap);
			indirizzo.setCitta(citta);
			indirizzo.setProvincia(provincia);
			indirizzo.setPaese(paese);
			indirizzo.setIdUtente(utente.getIdUtente());
			
			indirizzoDAO.doSave(indirizzo);
			
			break;
			}
		case "rimuovi": {
			String idIndirizzo = request.getParameter("idIndirizzo");
			
			if(idIndirizzo == null || idIndirizzo.isEmpty())
				break;
			
			indirizzoDAO.doDelete(Integer.valueOf(idIndirizzo));
			
			break;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/Checkout");
	}

}
