<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoBean" %>
<%
    ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - <%= prodotto.getNome() %></title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/CatalogoServlet">Catalogo</a><br><br>

<h1><%= prodotto.getNome() %></h1>

<%
	String errore = (String) request.getAttribute("errore");

	if(errore != null) {
%>
	<p><%= errore %></p>
<%
	}
%>

<p>Prezzo: <%= prodotto.getPrezzo() %> €
<p>Descrizione: <%= prodotto.getDescrizione() %>

<% if(prodotto.getSviluppatore() != null) %>
<p>Sviluppatore: <%= prodotto.getSviluppatore() %>


<p>Data uscita: <%= prodotto.getDataUscita() %><br><br>

<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getImmagine() %>" width="200"><br><br>

<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
	<input type="hidden" name="idProdotto" value=<%= prodotto.getIdProdotto() %>>
			
	<input type="submit" value="Aggiungi al carrello">
</form>

</body>
</html>