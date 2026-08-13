<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.GenereBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
ProdottoViewBean prodotto = (ProdottoViewBean) request.getAttribute("prodotto");
String errore = (String) request.getAttribute("errore");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - <%= prodotto.getProdotto().getNome() %></title>
</head>
<body>

<div class="errore">
	<%
		if(errore != null) {
	%>
		<p><%= errore %></p>
	<%
		}
	%>
</div>

<main class="pagina-prodotto">

	<div class="prodotto-immagine">
		<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getProdotto().getImmagine() %>">
	</div>
	
	<div class="card-dati">
		<h1><%= prodotto.getProdotto().getNome() %></h1>
	
		<div class="prodotto-prezzo">
			
			<%
				if(prodotto.getOfferta() != null) {
			%>
					<span class="info-offerta">OFFERTA VALIDA FINO AL <%= sdf.format(prodotto.getOfferta().getDataFine()) %></span><br>
					<div class="prezzi">
						<span class="prezzo-originale"><%= prodotto.getProdotto().getPrezzo() %> €</span>
						<span class="percentuale-sconto">-<%= prodotto.getOfferta().getPercentualeSconto() %>%</span>
						<span class="prezzo-scontato"><%= prodotto.getPrezzoScontato() %> €</span>
					</div>
			<%
				}
				else {
			%>
					<span class="prezzo"><%= prodotto.getProdotto().getPrezzo() %> €</span>
			<%
				}
			%>
		</div>
		
		<div class="prodotto-info">
			
			<%
			if(prodotto.getGeneri() != null && !prodotto.getGeneri().isEmpty()) {
			%>
				<div class="prodotto-generi">
				<strong>Generi:</strong>
				<%
				for(int i = 0; i < prodotto.getGeneri().size(); i++) {
					GenereBean genere = prodotto.getGeneri().get(i);
				%>
					<span><%= genere.getNomeGenere() %><%= (i < prodotto.getGeneri().size() - 1) ? ", " : "" %></span>
				<%
				}
				%>
				</div>
			<%
			}
			%>
			
			<div>
				<p><strong>Sviluppatore:</strong> <%= prodotto.getProdotto().getSviluppatore() %>
			</div>
			
			<div>
				<p><strong>Data di uscita:</strong> <%= sdf.format(prodotto.getProdotto().getDataUscita()) %><br>
			</div>
			
			<div>
				<p><strong>Descrizione:</strong> <%= prodotto.getProdotto().getDescrizione() %></p>
			</div>
			
		</div>
		
		<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
			<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
			
			<select name="idPiattaforma" required>
				<option value="">Seleziona piattaforma</option>
	
				<%
				for(PiattaformaBean piattaforma : prodotto.getPiattaforme()) {
				%>
					<option value="<%= piattaforma.getIdPiattaforma() %>">
						<%= piattaforma.getNomePiattaforma() %>
					</option>
				<%
				}
				%>
			</select>
		
			<input type="submit" value="Aggiungi al carrello">
		</form>
	</div>
</main>

<%@ include file="/jsp/components/footer.jsp" %>
</body>
</html>