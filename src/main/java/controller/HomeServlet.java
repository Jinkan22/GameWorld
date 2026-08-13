package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoViewBean;
import utils.OrdinamentoProdotti;

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
		ArrayList<ProdottoViewBean> nuoveUscite = prodottoDAO.doRetrieveViewByRicercaFiltriOrdinamento(
				null, null, null, OrdinamentoProdotti.NUOVE_USCITE, 1);
		ArrayList<ProdottoViewBean> miglioriOfferte = prodottoDAO.doRetrieveViewByRicercaFiltriOrdinamento(
				null, null, null, OrdinamentoProdotti.MIGLIORI_OFFERTE, 1);
		ArrayList<ProdottoViewBean> piuVenduti = prodottoDAO.doRetrieveViewByRicercaFiltriOrdinamento(
				null, null, null, OrdinamentoProdotti.PIU_VENDUTI, 1);
		
		//prodotti piattaforme
		ArrayList<Integer> steamId = new ArrayList<Integer>();
		ArrayList<Integer> playstationId = new ArrayList<Integer>();
		ArrayList<Integer> xboxId = new ArrayList<Integer>();
		ArrayList<Integer> nintendoId = new ArrayList<Integer>();

		steamId.add(7);
		playstationId.add(8);
		playstationId.add(9);
		xboxId.add(12);
		xboxId.add(13);
		nintendoId.add(10);
		nintendoId.add(11);
		
		ArrayList<ProdottoViewBean> giochiSteam = prodottoDAO.doRetrieveViewByRicercaFiltriOrdinamento(
				null, steamId, null, OrdinamentoProdotti.CASUALE, 3);
		ArrayList<ProdottoViewBean> giochiPlaystation = prodottoDAO.doRetrieveViewByRicercaFiltriOrdinamento(
				null, playstationId, null, OrdinamentoProdotti.CASUALE, 3);
		ArrayList<ProdottoViewBean> giochiXbox = prodottoDAO.doRetrieveViewByRicercaFiltriOrdinamento(
				null, xboxId, null, OrdinamentoProdotti.CASUALE, 3);
		ArrayList<ProdottoViewBean> giochiNintendo = prodottoDAO.doRetrieveViewByRicercaFiltriOrdinamento(
				null, nintendoId, null, OrdinamentoProdotti.CASUALE, 3);

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
