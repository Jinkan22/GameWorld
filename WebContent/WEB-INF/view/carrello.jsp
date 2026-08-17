<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.OffertaBean" %>
<%@ page import="model.ElementoCarrelloViewBean" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Carrello</title>
</head>
<body>

<main class="pagina-carrello">
	<section class="lista-carrello">
		<h2>CARRELLO</h2>
		
		<%
			String errore = (String) request.getAttribute("errore");
			if(errore != null){
		%>
				<p><%= errore %><br><br>
		<%
			}
			
			BigDecimal totale = BigDecimal.ZERO;
			BigDecimal sconto = BigDecimal.ZERO;
			BigDecimal totaleScontato = BigDecimal.ZERO;
			
			ArrayList<ElementoCarrelloViewBean> carrello = (ArrayList<ElementoCarrelloViewBean>) request.getAttribute("carrello");

			if(carrello != null && !carrello.isEmpty()) {
			
				for(ElementoCarrelloViewBean elemento : carrello) {
	
					ProdottoBean prodotto = elemento.getProdotto();
					PiattaformaBean piattaforma = elemento.getPiattaforma();
					OffertaBean offerta = elemento.getOfferta();
				
					totale = totale.add(prodotto.getPrezzo().multiply(BigDecimal.valueOf(elemento.getQuantita())));
		%>
					<div class="prodotto-carrello">
						<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getImmagine() %>" width="200">
						
						<div class="dati-prodotto-carrello">
							<div class="nome-prodotto">
								<strong><%= prodotto.getNome() %></strong>
							</div>
							
							<%
								if(offerta != null) {
									totaleScontato = totaleScontato.add(elemento.getPrezzoScontato().multiply(BigDecimal.valueOf(elemento.getQuantita())));
							%>
									<div class="prezzi">
										<div class="prezzo-originale">
											<%= prodotto.getPrezzo() %> €
										</div>
										<div class="percentuale-sconto">
											-<%= offerta.getPercentualeSconto() %>%
										</div>
										<div class="prezzo-scontato">
											<%= elemento.getPrezzoScontato() %> €
										</div>
									</div>
							<%
								}
								else {
									totaleScontato = totaleScontato.add(prodotto.getPrezzo().multiply(BigDecimal.valueOf(elemento.getQuantita())));
							%>
									<div class="prezzo">
										<%= prodotto.getPrezzo() %> €
									</div>
							<%
								}
							%>
							
							<div class="piattaforma-prodotto">
								<p><%= piattaforma.getNomePiattaforma() %></p>
							</div>
							
							<form class="form-quantita" action="<%= request.getContextPath()%>/ModificaCarrello" method="post">
								<input type="hidden" name="idProdotto" value="<%= prodotto.getIdProdotto() %>">
								<input type="hidden" name="idPiattaforma" value="<%= piattaforma.getIdPiattaforma() %>">
							
								<div class="controlli-quantita">
									<input class="decrementa" type="submit" name="azione" value="-">
									<span class="quantita-prodotto"><%= elemento.getQuantita() %></span>
									<input class="incrementa" type="submit" name="azione" value="+">
								</div>
							
								<input class="rimuovi" type="submit" name="azione" value="Rimuovi">
							</form>
						</div>
					</div>
		<%
				}
				sconto = totale.subtract(totaleScontato);
			}
			else {
		%>
				<p>Il carrello è vuoto</p>
		<%
			}
		%>
	</section>
	
	<aside class="riepilogo-totale">
		<div class="totale">
			<strong>Totale: </strong>
			<span><%= totale %> €</span>
		</div>
		<div class="sconto">
			<strong>Sconto: </strong>
			<span>- <%= sconto %> €</span>
		</div>
		<hr>
		<div class="totaleScontato">
			<strong>Totale scontato: </strong>
			<span><%= totaleScontato %> €</span>
		</div>
		
		<form action="<%= request.getContextPath() %>/Checkout" method="get">
			<input type="submit" value="Procedi all'ordine">
		</form>
	</aside>
</main>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>