package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MetodoPagamentoBean;
import model.UtenteBean;

import java.io.IOException;
import java.sql.Date;

import dao.MetodoPagamentoDAO;

/**
 * Servlet implementation class AggiungiRimuoviMetodoPagamentoServlet
 */
@WebServlet("/AggiungiRimuoviMetodoPagamento")
public class AggiungiRimuoviMetodoPagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiRimuoviMetodoPagamentoServlet() {
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
		
		MetodoPagamentoDAO metodoPagamentoDAO = new MetodoPagamentoDAO();
		
		switch(azione) {
		case "aggiungi": {
			String circuito = request.getParameter("circuito");
			String numeroCarta = request.getParameter("numeroCarta");
			String intestatario = request.getParameter("intestatario");
			String dataScadenza = request.getParameter("dataScadenza");
			
			MetodoPagamentoBean metodo = new MetodoPagamentoBean();
			
			metodo.setCircuito(circuito);
			metodo.setNumeroCarta(numeroCarta);
			metodo.setIntestatario(intestatario);
			metodo.setDataScadenza(Date.valueOf(dataScadenza + "-01"));
			metodo.setIdUtente(utente.getIdUtente());
			
			metodoPagamentoDAO.doSave(metodo);
			
			break;
			}
		case "rimuovi": {
			String idMetodo = request.getParameter("idMetodo");
			
			if(idMetodo == null || idMetodo.isEmpty())
				break;
			
			metodoPagamentoDAO.doDelete(Integer.valueOf(idMetodo));
			
			break;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/Checkout");
	}

}
