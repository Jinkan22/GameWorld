<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.GenereBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
ProdottoViewBean prodotto = (ProdottoViewBean) request.getAttribute("prodotto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - <%= prodotto.getProdotto().getNome() %></title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/CatalogoServlet">Catalogo</a><br><br>

<h1><%= prodotto.getProdotto().getNome() %></h1>

<%
	String errore = (String) request.getAttribute("errore");

	if(errore != null) {
%>
	<p><%= errore %></p>
<%
	}
%>

<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getProdotto().getImmagine() %>" width="200"><br>

<p>Prezzo: <%= prodotto.getProdotto().getPrezzo() %> €</p>
<%
	if(prodotto.getOfferta() != null)
%>
<p>IN OFFERTA A: <%= prodotto.getPrezzoScontato() %> € fino al <%= sdf.format(prodotto.getOfferta().getDataFine()) %></p>

<p>Descrizione: <%= prodotto.getProdotto().getDescrizione() %>

<%
	if(prodotto.getGeneri() != null) {
		%>
		<p>Generi:
		<%
		for(GenereBean genere : prodotto.getGeneri()) {
			%>
			<%= genere.getNomeGenere() %>
			<%
		}
	}
	if(prodotto.getPiattaforme() != null) {
		%>
		<p>Piattaforme:
		<%
		for(PiattaformaBean piattaforma : prodotto.getPiattaforme()) {
			%>
			<%= piattaforma.getNomePiattaforma() %>
			<%
		}
	}
%>

<% if(prodotto.getProdotto().getSviluppatore() != null) %>
<p>Sviluppatore: <%= prodotto.getProdotto().getSviluppatore() %>

<p>Data di uscita: <%= sdf.format(prodotto.getProdotto().getDataUscita()) %><br>

<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
	<input type="hidden" name="idProdotto" value=<%= prodotto.getProdotto().getIdProdotto() %>>
			
	<input type="submit" value="Aggiungi al carrello">
</form>

</body>
</html>