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
<title><%= prodotto.getNome() %></title>
</head>
<body>

<a href="<%= request.getContextPath() %>/CatalogoServlet">Catalogo</a><br><br>

<h1><%= prodotto.getNome() %></h1>

<p>Prezzo: <%= prodotto.getPrezzo() %> €
<p>Descrizione: <%= prodotto.getDescrizione() %>

<% if(prodotto.getSviluppatore() != null) %>
<p>Sviluppatore: <%= prodotto.getSviluppatore() %>


<p>Data uscita: <%= prodotto.getDataUscita() %><br><br>

<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getImmagine() %>" width="200">

</body>
</html>