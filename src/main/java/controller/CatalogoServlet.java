package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GenereBean;
import model.PiattaformaBean;
import model.ProdottoViewBean;
import utils.OrdinamentoProdotti;

import java.io.IOException;
import java.util.ArrayList;

import dao.GenereDAO;
import dao.PiattaformaDAO;
import dao.ProdottoDAO;

/**
 * Servlet implementation class CatalogoServlet
 */
@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ricerca = request.getParameter("ricerca");
		String[] idPiattaformeString = request.getParameterValues("idPiattaforme");
		String[] idGeneriString = request.getParameterValues("idGeneri");
		String opzioni = request.getParameter("ordinamento");
		
		ArrayList<Integer> idPiattaforme = new ArrayList<Integer>();
		ArrayList<Integer> idGeneri = new ArrayList<Integer>();
		
		if(idPiattaformeString != null) {
			for(String id : idPiattaformeString) {
				idPiattaforme.add(Integer.parseInt(id));
			}
		}
		
		if(idGeneriString != null) {
			for(String id : idGeneriString) {
				idGeneri.add(Integer.parseInt(id));
			}
		}
		
		OrdinamentoProdotti ordinamento;
		
		if(opzioni == null || opzioni.isEmpty()) {
			ordinamento = OrdinamentoProdotti.CASUALE;
		}
		else {
			ordinamento = OrdinamentoProdotti.valueOf(opzioni);
		}
		
		ProdottoDAO dao = new ProdottoDAO();
		PiattaformaDAO piattaformaDAO = new PiattaformaDAO();
		GenereDAO genereDAO = new GenereDAO();

		ArrayList<ProdottoViewBean> prodotti = dao.doRetrieveViewByRicercaFiltriOrdinamento(
				ricerca, idPiattaforme, idGeneri, ordinamento, 0);
		ArrayList<PiattaformaBean> piattaforme = piattaformaDAO.doRetrieveAll();
		ArrayList<GenereBean> generi = genereDAO.doRetrieveAll();

		request.setAttribute("piattaforme", piattaforme);
		request.setAttribute("generi", generi);
		request.setAttribute("prodotti", prodotti);
		
		request.setAttribute("idPiattaforme", idPiattaforme);
		request.setAttribute("idGeneri", idGeneri);
		request.setAttribute("ricerca", ricerca);
		request.setAttribute("ordinamento", ordinamento);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/catalogo.jsp");
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
