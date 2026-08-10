<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.ElementoCarrelloViewBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Carrello</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo</a><br>

<h1>Carrello</h1>
<hr>

<%
String errore = (String) request.getAttribute("errore");

if(errore != null){
%>

<p><%= errore %><br><br>

<%
}	
	ArrayList<ElementoCarrelloViewBean> carrello = (ArrayList<ElementoCarrelloViewBean>) request.getAttribute("carrello");

	if(carrello != null && !carrello.isEmpty()) {
	
		for(ElementoCarrelloViewBean elemento : carrello) {
	
			ProdottoBean prodotto = elemento.getProdotto();
			PiattaformaBean piattaforma = elemento.getPiattaforma();
%>
	
			<p><strong><%= prodotto.getNome() %></strong></p>
	
			<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getImmagine() %>" width="200">
	
			<p><strong>Prezzo:</strong> <%= prodotto.getPrezzo() %> €</p>
			<p><strong>Piattaforma:</strong> <%= piattaforma.getNomePiattaforma() %></p>
			<p><strong>Quantità:</strong> <%= elemento.getQuantita() %></p>
	
			<form action="<%= request.getContextPath()%>/ModificaCarrelloServlet" method="post">
				<input type="hidden" name="idProdotto" value="<%= prodotto.getIdProdotto() %>">
				<input type="hidden" name="idPiattaforma" value="<%= piattaforma.getIdPiattaforma() %>">
	
				<input type="submit" name="azione" value="+">
				<input type="submit" name="azione" value="-">
				<input type="submit" name="azione" value="rimuovi">
			</form>
			<hr>
	
	<%
		}
		%>
			<form action="<%= request.getContextPath() %>/CheckoutServlet" method="get">
				<input type="submit" value="Procedi all'ordine">
			</form>
		<%
	}
	else {
	%>
		<p>Il carrello è vuoto</p>
	<%
	}
%>
	
</body>
</html>