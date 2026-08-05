package controller;

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

/**
 * Servlet implementation class ModificaCarrelloServlet
 */
@WebServlet("/ModificaCarrelloServlet")
public class ModificaCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaCarrelloServlet() {
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
		String azione=request.getParameter("azione");
		
		HttpSession session=request.getSession();
		
		UtenteBean utente=(UtenteBean)session.getAttribute("utente");
		
		int idProdotto=Integer.parseInt(request.getParameter("idProdotto"));
		
		if(utente==null) {
			
			ArrayList<ElementoCarrelloBean>carrello=(ArrayList<ElementoCarrelloBean>)session.getAttribute("carrello");
			
			ElementoCarrelloBean trovato=new ElementoCarrelloBean();
			
			for(ElementoCarrelloBean elemento:carrello) {
				if(elemento.getIdProdotto()==idProdotto) {
					trovato=elemento;
				}
			}
			
			switch(azione) {
			case"+":
				trovato.setQuantita(trovato.getQuantita()+1);
			case"-":
				trovato.setQuantita(trovato.getQuantita()-1);
			case"rimuovi":
				carrello.remove(trovato);
			}
		}else {
			
			ElementoCarrelloDAO dao=new ElementoCarrelloDAO();
			
			ElementoCarrelloBean elemento=dao.doRetrieveByIdUtenteAndIdProdotto(utente.getIdUtente(),idProdotto);
			
			switch(azione) {
			case"+":
				elemento.setQuantita(elemento.getQuantita()+1);
				dao.doUpdate(elemento);
			case"-":
				elemento.setQuantita(elemento.getQuantita()-1);
				dao.doUpdate(elemento);
			case"rimuovi":
				dao.doDelete(elemento.getIdProdotto());
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/CarrelloServlet");
		
	}

}
