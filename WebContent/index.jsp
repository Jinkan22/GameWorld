<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Home</title>
</head>
<body>

<%
	ProdottoViewBean nuovaUscita = (ProdottoViewBean) request.getAttribute("nuovaUscita");
	ProdottoViewBean miglioreOfferta = (ProdottoViewBean) request.getAttribute("miglioreOfferta");
	ProdottoViewBean piuVenduto = (ProdottoViewBean) request.getAttribute("piuVenduto");
	
	ArrayList<ProdottoViewBean> giochiSteam = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiSteam");
	ArrayList<ProdottoViewBean> giochiPlaystation = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiPlaystation");
	ArrayList<ProdottoViewBean> giochiXbox = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiXbox");
	ArrayList<ProdottoViewBean> giochiNintendo = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiNintendo");
%>

<main>
	<section class="banners">
		<div class="top-banners">
			<div class="banner">
				<%
					if(nuovaUscita != null) {
				%>
					<a href="<%= request.getContextPath() %>/CatalogoServlet?ordinamento=NUOVE_USCITE">
						<img src="<%= request.getContextPath() + "/images/products/" + nuovaUscita.getProdotto().getImmagine() %>">
						<h2>NUOVE USCITE</h2>
					</a>
				<%
					}
				%>
			</div>
			<div class="descrizione">
				<h1>Benvenuto su gameWorld!</h1>
            	<h3>
            	    Il tuo store digitale dedicato ai videogiochi.<br>
            	    Scopri nuovi titoli, grandi offerte e i giochi<br>
            	    più amati dai videogiocatori.
            	</h3>
        	</div>
		</div>
		<div class="bottom-banners">
			<div class="banner">
				<%
					if(miglioreOfferta != null) {
				%>
					<a href="<%= request.getContextPath() %>/CatalogoServlet?ordinamento=MIGLIORI_OFFERTE">
						<img src="<%= request.getContextPath() + "/images/products/" + miglioreOfferta.getProdotto().getImmagine() %>">
						<h2>IN OFFERTA OGGI</h2>
					</a>
				<%
					}
				%>
			</div>
			<div class="banner">
				<%
					if(piuVenduto != null) {
				%>
					<a href="<%= request.getContextPath() %>/CatalogoServlet?ordinamento=PIU_VENDUTI">
						<img src="<%= request.getContextPath() + "/images/products/" + piuVenduto.getProdotto().getImmagine() %>">
						<h2>I PIÙ VENDUTI</h2>
					</a>
				<%
					}
				%>
			</div>
		</div>
	</section>

	<section class="piattaforme">
		<h2>STEAM</h2>
		<hr>
		<div class="linea-prodotti">
			<%
				for(ProdottoViewBean prodotto : giochiSteam) {
			%>
				<div class ="card-prodotto">
					<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getProdotto().getIdProdotto() %>">
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
			%>
		</div>
	</section>
	
	<section class="piattaforme">
		<h2>PLAYSTATION</h2>
		<hr>
		<div class="linea-prodotti">
			<%
				for(ProdottoViewBean prodotto : giochiPlaystation) {
			%>
				<div class ="card-prodotto">
					<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getProdotto().getIdProdotto() %>">
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
			%>
		</div>
	</section>
	
	<section class="piattaforme">
		<h2>XBOX</h2>
		<hr>
		<div class="linea-prodotti">
			<%
				for(ProdottoViewBean prodotto : giochiXbox) {
			%>
				<div class ="card-prodotto">
					<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getProdotto().getIdProdotto() %>">
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
			%>
		</div>
	</section>
	
	<section class="piattaforme">
		<h2>NINTENDO</h2>
		<hr>
		<div class="linea-prodotti">
			<%
				for(ProdottoViewBean prodotto : giochiNintendo) {
			%>
				<div class ="card-prodotto">
					<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getProdotto().getIdProdotto() %>">
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
			%>
		</div>
	</section>

</main>

<%@ include file="/jsp/components/footer.jsp" %>

</body>
</html>