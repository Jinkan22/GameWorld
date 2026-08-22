package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OffertaBean;
import model.OffertaViewBean;
import model.ProdottoBean;
import model.UtenteBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import dao.OffertaDAO;
import dao.ProdottoDAO;

@WebServlet("/GestioneOfferte")
public class GestioneOfferteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GestioneOfferteServlet() {
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
		
		String errore = (String) session.getAttribute("errore");
		if(errore != null) {
			request.setAttribute("errore", errore);
			session.removeAttribute("errore");
		}
		
		OffertaDAO offertaDAO = new OffertaDAO();
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		
		ArrayList<OffertaBean> offerte = offertaDAO.doRetrieveAll();
		ArrayList<OffertaViewBean> offerteView = new ArrayList<OffertaViewBean>();

		for(OffertaBean offerta : offerte) {
		    OffertaViewBean offertaView = new OffertaViewBean();
		    ProdottoBean prodotto = prodottoDAO.doRetrieveByKey(offerta.getIdProdotto());
		    
		    offertaView.setOfferta(offerta);
		    offertaView.setProdotto(prodotto);
		    offertaView.setPrezzoScontato(prodotto.getPrezzo().multiply(
					BigDecimal.ONE.subtract(
					BigDecimal.valueOf(offerta.getPercentualeSconto()).
					divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP));

		    offerteView.add(offertaView);
		}

		request.setAttribute("offerte", offerteView);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/gestioneOfferte.jsp");
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
		
		int idOfferta = Integer.parseInt(request.getParameter("idOfferta"));
		OffertaDAO dao = new OffertaDAO();
		
		dao.doDelete(idOfferta);
		
		response.sendRedirect(request.getContextPath() + "/GestioneOfferte");
	}
}
