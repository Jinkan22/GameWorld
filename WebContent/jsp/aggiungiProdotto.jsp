<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Aggiungi prodotto</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/GestioneProdottiServlet">Gestione prodotti</a><br>

<h1>Aggiungi prodotto</h1>

<form action="<%= request.getContextPath()%>/AggiungiProdottoServlet" method="post">

	<label>Nome prodotto</label><br>
	<input type="text" name="nome" required><br><br>
	
	<label>Descrizione</label><br>
	<textarea name="descrizione" rows="5" cols="50" required></textarea><br><br>
	
	<label>Prezzo</label><br>
	<input type="number" name="prezzo" min="0" step="0.01" required><br><br>
	
	<label>Immagine</label><br>
	<input type="text" name="immagine" required><br><br>
	
	<label>Data di uscita</label><br>
	<input type="date" name="dataUscita" required><br><br>
	
	<label>Sviluppatore</label><br>
	<input type="text" name="sviluppatore" required><br><br>
	
	<input type="submit" value="Aggiungi prodotto">

</form>

</body>
</html>