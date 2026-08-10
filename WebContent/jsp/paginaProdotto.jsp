<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.GenereBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
ProdottoViewBean prodotto = (ProdottoViewBean) request.getAttribute("prodotto");
UtenteBean utente = (UtenteBean) session.getAttribute("utente");
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

<p><strong>Prezzo:</strong> <%= prodotto.getProdotto().getPrezzo() %> €</p>
<%
	if(prodotto.getOfferta() != null) {
%>
<p style="color:red"><strong>IN OFFERTA A <%= prodotto.getPrezzoScontato() %> € FINO AL <%= sdf.format(prodotto.getOfferta().getDataFine()) %>!</strong></p>

<%
	}
%>

<p><strong>Descrizione:</strong> <%= prodotto.getProdotto().getDescrizione() %></p>

<%
	if(prodotto.getGeneri() != null) {
		%>
		<p><strong>Generi:</strong>
		<%
		for(int i = 0; i < prodotto.getGeneri().size(); i++) {
			GenereBean genere = prodotto.getGeneri().get(i);
			%>
			<%= genere.getNomeGenere() %><%= (i < prodotto.getGeneri().size() - 1) ? ", " : "" %>
			<%
		}
	}
	if(prodotto.getPiattaforme() != null) {
		%>
		<p><strong>Piattaforme:</strong>
		<%
		for(int i = 0; i < prodotto.getPiattaforme().size(); i++) {
			PiattaformaBean piattaforma = prodotto.getPiattaforme().get(i);
			%>
			<%= piattaforma.getNomePiattaforma() %><%= (i < prodotto.getPiattaforme().size() - 1) ? ", " : "" %>
			<%
		}
	}
%>

	<p><strong>Sviluppatore:</strong> <%= prodotto.getProdotto().getSviluppatore() %>
	<p><strong>Data di uscita:</strong> <%= sdf.format(prodotto.getProdotto().getDataUscita()) %><br>

	<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
		<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
		
		<select name="idPiattaforma" required>
			<option value="">Seleziona piattaforma</option>

			<%
			for(PiattaformaBean piattaforma : prodotto.getPiattaforme()) {
			%>
				<option value="<%= piattaforma.getIdPiattaforma() %>">
					<%= piattaforma.getNomePiattaforma() %>
				</option>
			<%
			}
			%>
		</select>
	
		<input type="submit" value="Aggiungi al carrello">
	</form>

</body>
</html>