<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<%@ page import="utils.OrdinamentoProdotti" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

OrdinamentoProdotti ordinamento = (OrdinamentoProdotti) request.getAttribute("ordinamento");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Catalogo</title>
</head>
<body>

<main class="catalogo">
	<aside class="sidebar-filtri">
		<h3>FILTRI</h3>
		<form action="<%= request.getContextPath() %>/Catalogo" method="get">
		
			<strong>Ordina per:</strong><br>
		    
		    <label>
				<input type="radio" name="ordinamento" value="CASUALE" 
				<%= ordinamento == OrdinamentoProdotti.CASUALE ? "checked" : "" %>>
				Predefinito
		    </label>
		    <label>
				<input type="radio" name="ordinamento" value="NOME" 
				<%= ordinamento == OrdinamentoProdotti.NOME ? "checked" : "" %>>
				Dalla A alla Z
		    </label>
		    <label>
				<input type="radio" name="ordinamento" value="NUOVE_USCITE" 
				<%= ordinamento == OrdinamentoProdotti.NUOVE_USCITE ? "checked" : "" %>>
				Nuove uscite
		    </label>
		    <label>
				<input type="radio" name="ordinamento" value="MIGLIORI_OFFERTE" 
				<%= ordinamento == OrdinamentoProdotti.MIGLIORI_OFFERTE ? "checked" : "" %>>
				Migliori offerte
		    </label>
		    <label>
				<input type="radio" name="ordinamento" value="PIU_VENDUTI" 
				<%= ordinamento == OrdinamentoProdotti.PIU_VENDUTI ? "checked" : "" %>>
				Piu venduti
		    </label>
		
		    <br><strong>Piattaforme:</strong><br>
		
		    <%
		    ArrayList<PiattaformaBean> piattaforme = (ArrayList<PiattaformaBean>) request.getAttribute("piattaforme");
		    ArrayList<Integer> idPiattaformeSelezionate = (ArrayList<Integer>) request.getAttribute("idPiattaforme");
		
		    if(piattaforme != null) {
		        for(PiattaformaBean piattaforma : piattaforme) {
		            boolean selezionata = idPiattaformeSelezionate != null && idPiattaformeSelezionate.contains(piattaforma.getIdPiattaforma());
		    %>
		        		<label>
		            		<input type="checkbox" name="idPiattaforme" value="<%= piattaforma.getIdPiattaforma() %>" <%= selezionata ? "checked" : "" %>>
		
		          			<%= piattaforma.getNomePiattaforma() %>
		       			</label>
		    <%
		        }
		    }
		    %>
		    
			<br><strong>Generi:</strong><br>
		
		    <%
		    ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");
			ArrayList<Integer> idGeneriSelezionati = (ArrayList<Integer>) request.getAttribute("idGeneri");
		
		    if(generi != null) {
		        for(GenereBean genere : generi) {
		            boolean selezionato = idGeneriSelezionati != null && idGeneriSelezionati.contains(genere.getIdGenere());
		    %>
		
		       		<label>
		            	<input type="checkbox" name="idGeneri" value="<%= genere.getIdGenere() %>" <%= selezionato ? "checked" : "" %>>
		
		            	<%= genere.getNomeGenere() %>
		        	</label>
		    <%
		        }
		    }
		    %>
		
		    <br><input type="submit" value="Filtra">
	
		</form>
	</aside>
	
	<section class="risultati-prodotti">
		<h2>CATALOGO</h2>
		<div class="linea-prodotti">
		<%
		    ArrayList<ProdottoViewBean> prodotti = (ArrayList<ProdottoViewBean>) request.getAttribute("prodotti");
		
		    if(prodotti != null && !prodotti.isEmpty()) {
		        for(ProdottoViewBean prodotto : prodotti) {
		%>
					<div class ="card-prodotto">
						<a href="<%= request.getContextPath() %>/PaginaProdotto?idProdotto=<%= prodotto.getProdotto().getIdProdotto() %>">
							<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getProdotto().getImmagine() %>">
						</a>
						
						<h3><%= prodotto.getProdotto().getNome() %></h3>
						<%
							if(prodotto.getOfferta() != null) {
						%>
								<div class="prezzi">
									<div class="prezzo-originale">
										<%= prodotto.getProdotto().getPrezzo() %> €
									</div>
									<div class="percentuale-sconto">
										-<%= prodotto.getOfferta().getPercentualeSconto() %>%
									</div>
									<div class="prezzo-scontato">
										<%= prodotto.getPrezzoScontato() %> €
									</div>
								</div>
						<%
							}
							else {
						%>
								<div class="prezzo">
									<%= prodotto.getProdotto().getPrezzo() %> €
								</div>
						<%
							}
						%>
					</div>
		
		<%
		        }
		    }
		    else {
		%>
				<p>Non ci sono prodotti corrispondenti alla tua ricerca</p>
		<%
		    }
		%>
		</div>	
	</section>
</main>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>