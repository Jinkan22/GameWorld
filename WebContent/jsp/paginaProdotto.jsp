<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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

<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getImmagine() %>" width="200"><br>

<p>Prezzo: <%= prodotto.getPrezzo() %> €
<p>Descrizione: <%= prodotto.getDescrizione() %>

<% if(prodotto.getSviluppatore() != null) %>
<p>Sviluppatore: <%= prodotto.getSviluppatore() %>

<p>Data uscita: <%= sdf.format(prodotto.getDataUscita()) %><br>

<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
	<input type="hidden" name="idProdotto" value=<%= prodotto.getIdProdotto() %>>
			
	<input type="submit" value="Aggiungi al carrello">
</form>

</body>
</html>