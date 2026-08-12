<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Creazione offerta</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/GestioneProdottiServlet">Gestione prodotti</a><br>

<h1>Creazione offerta</h1>

<%
	ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
%>

<p><strong>Prodotto:</strong> <%= prodotto.getNome() %></p>
<p><strong>Prezzo:</strong> <%= prodotto.getPrezzo() %> €</p>

<form action="<%= request.getContextPath()%>/CreazioneOffertaServlet" method="post">
	
	<label>Percentuale di sconto</label><br>
	<input type="number" name="percentualeSconto" min="0" max="100" required><br><br>
	
	<label>Data di inizio</label><br>
	<input type="date" name="dataInizio" required><br><br>
	
	<label>Data di fine</label><br>
	<input type="date" name="dataFine" required><br><br>
	
	<input type="hidden" name="idProdotto" value="<%= prodotto.getIdProdotto() %>">
	<input type="submit" value="Crea offerta">

</form>

</body>
</html>