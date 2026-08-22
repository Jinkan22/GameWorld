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
import java.sql.Timestamp;
import java.util.ArrayList;

import dao.OrdineDAO;

@WebServlet("/GestioneOrdini")
public class GestioneOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GestioneOrdiniServlet() {
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
        
        OrdineDAO ordineDAO = new OrdineDAO();
        ArrayList<OrdineViewBean> ordini = new ArrayList<OrdineViewBean>();
        
        String azione = request.getParameter("azione");
        
        if("Mostra tutti".equals(azione)) {
        	ordini = ordineDAO.doRetrieveAllView();
        }
        else {
        	String dataInizioParam = request.getParameter("dataInizio");
            String dataFineParam = request.getParameter("dataFine");
            String idUtenteParam = request.getParameter("idUtente");

            Timestamp dataInizio = null;
            Timestamp dataFine = null;
            Integer idUtente = null;

            if (dataInizioParam != null && !dataInizioParam.isEmpty()) {
                dataInizio = Timestamp.valueOf(dataInizioParam + " 00:00:00");
            }
            if (dataFineParam != null && !dataFineParam.isEmpty()) {
                dataFine = Timestamp.valueOf(dataFineParam + " 23:59:59");
            }
            if (idUtenteParam != null && !idUtenteParam.isEmpty()) {
                idUtente = Integer.parseInt(idUtenteParam);
            }

            ordini = ordineDAO.doRetrieveViewByFiltri(dataInizio, dataFine, idUtente);
            
            request.setAttribute("dataInizio", dataInizioParam);
            request.setAttribute("dataFine", dataFineParam);
            request.setAttribute("idUtente", idUtenteParam);
        }

        request.setAttribute("ordini", ordini);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/gestioneOrdini.jsp");
        dispatcher.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
