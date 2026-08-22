package control;

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

@WebServlet("/AggiungiRimuoviIndirizzo")
public class AggiungiRimuoviIndirizzoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AggiungiRimuoviIndirizzoServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
		if(utente == null) {
			session.setAttribute("erroreLogin", "Effettuare il login per completare l'acquisto");
			response.sendRedirect(request.getContextPath() + "/Login");
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
