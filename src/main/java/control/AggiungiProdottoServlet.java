package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

import dao.ProdottoDAO;

@WebServlet("/AggiungiProdotto")
public class AggiungiProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AggiungiProdottoServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO dao = new ProdottoDAO();
		ProdottoBean prodotto = new ProdottoBean();
		
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		String immagine = request.getParameter("immagine");
		String dataUscita = request.getParameter("dataUscita");
		String sviluppatore = request.getParameter("sviluppatore");
		
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);
		prodotto.setPrezzo(new BigDecimal(prezzo).setScale(2, RoundingMode.HALF_UP));
		prodotto.setImmagine(immagine);
		prodotto.setDataUscita(Date.valueOf(dataUscita));
		
		if(sviluppatore != null && !sviluppatore.isEmpty())
			prodotto.setSviluppatore(sviluppatore);
		
		dao.doSave(prodotto);
		
		response.sendRedirect(request.getContextPath() + "/ModificaProdotto?idProdotto=" + prodotto.getIdProdotto());
	}
}
