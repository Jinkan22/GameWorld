<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Modifica prodotto</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/GestioneProdottiServlet">Dashboard</a><br>

<h1>Modifica prodotto</h1>

<%
	ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
%>

<form action="<%= request.getContextPath()%>/ModificaProdottoServlet" method="post">

	<label>Nome prodotto</label><br>
	<input type="text" name="nome" value="<%= prodotto.getNome() %>" required><br><br>
	
	<label>Descrizione</label><br>
	<textarea name="descrizione" rows="5" cols="50" required><%= prodotto.getDescrizione() %></textarea><br><br>
	
	<label>Prezzo</label><br>
	<input type="number" name="prezzo" min="0" step="0.01" value="<%= prodotto.getPrezzo() %>" required><br><br>
	
	<label>Quantità disponibile</label><br>
	<input type="number" name="quantitaDisponibile" min="0" value="<%= prodotto.getQuantitaDisponibile() %>" required><br><br>
	
	<label>Immagine</label><br>
	<input type="text" name="immagine" value="<%= prodotto.getImmagine() %>" required><br><br>
	
	<label>Data di uscita</label><br>
	<input type="date" name="dataUscita" value="<%= prodotto.getDataUscita() %>" required><br><br>
	
	<%
	if(prodotto.getSviluppatore() != null) {
	%>
		<label>Sviluppatore</label><br>
		<input type="text" name="sviluppatore" value="<%= prodotto.getSviluppatore() %>" required><br><br>
	<% 
	}
	%>
	
	<input type="hidden" name="idProdotto" value="<%= prodotto.getIdProdotto() %>">
	<input type="submit" value="Modifica prodotto">

</form>

</body>
</html>