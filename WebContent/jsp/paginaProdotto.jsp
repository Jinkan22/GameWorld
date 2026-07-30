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

<h1><%= prodotto.getNome() %></h1>

<p>Prezzo: <%= prodotto.getPrezzo() %> €
<p>Descrizione: <%= prodotto.getDescrizione() %>
<p>Sviluppatore: <%= prodotto.getSviluppatore() %>
<p>Data uscita: <%= prodotto.getDataUscita() %>

<p>Valore immagine dal bean: <%= prodotto.getImmagine() %></p>

<p>Context path: <%= request.getContextPath() %></p>

<p>Percorso completo generato:
<%= request.getContextPath() + "/" + prodotto.getImmagine() %>
</p>

<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getImmagine() %>" width="200">

</body>
</html>