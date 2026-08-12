package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoViewBean;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProdottoDAO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		
		//banner
		ArrayList<ProdottoViewBean> nuoveUscite = prodottoDAO.doRetrieveViewNuoveUscite(1);
		ArrayList<ProdottoViewBean> miglioriOfferte = prodottoDAO.doRetrieveViewMiglioriOfferte(1);
		ArrayList<ProdottoViewBean> piuVenduti = prodottoDAO.doRetrieveViewPiuVenduti(1);
		
		//prodotti piattaforme
		ArrayList<ProdottoViewBean> giochiSteam = prodottoDAO.doRetrieveViewDisponibiliByPiattaforma(7, 3);
		ArrayList<ProdottoViewBean> giochiPlaystation = prodottoDAO.doRetrieveViewDisponibiliByPiattaforma(9, 3);
		ArrayList<ProdottoViewBean> giochiXbox = prodottoDAO.doRetrieveViewDisponibiliByPiattaforma(12, 3);
		ArrayList<ProdottoViewBean> giochiNintendo = prodottoDAO.doRetrieveViewDisponibiliByPiattaforma(11, 3);
		
		System.out.println("Nuove uscite: " + nuoveUscite.size());
		System.out.println("Migliori offerte: " + miglioriOfferte.size());
		System.out.println("Più venduti: " + piuVenduti.size());

		if(!nuoveUscite.isEmpty())
			request.setAttribute("nuovaUscita", nuoveUscite.get(0));
		if(!miglioriOfferte.isEmpty())
			request.setAttribute("miglioreOfferta", miglioriOfferte.get(0));
		if(!piuVenduti.isEmpty())
			request.setAttribute("piuVenduto", piuVenduti.get(0));
		
		request.setAttribute("giochiSteam", giochiSteam);
		request.setAttribute("giochiPlaystation", giochiPlaystation);
		request.setAttribute("giochiXbox", giochiXbox);
		request.setAttribute("giochiNintendo", giochiNintendo);

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
