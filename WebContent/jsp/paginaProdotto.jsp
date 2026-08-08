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
	
	ArrayList<PiattaformaBean> piattaforme = (ArrayList<PiattaformaBean>) request.getAttribute("piattaforme");
	ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");
%>

<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getProdotto().getImmagine() %>" width="200"><br>

<p><strong>Prezzo:</strong> <%= prodotto.getProdotto().getPrezzo() %> €</p>
<%
	if(prodotto.getOfferta() != null) {
%>
<p><strong>IN OFFERTA A: <%= prodotto.getPrezzoScontato() %> € fino al <%= sdf.format(prodotto.getOfferta().getDataFine()) %></strong></p>

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

<% if(prodotto.getProdotto().getSviluppatore() != null) %>
<p><strong>Sviluppatore:</strong> <%= prodotto.getProdotto().getSviluppatore() %>

<p><strong>Data di uscita:</strong> <%= sdf.format(prodotto.getProdotto().getDataUscita()) %><br>

<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
	<input type="hidden" name="idProdotto" value=<%= prodotto.getProdotto().getIdProdotto() %>>
			
	<input type="submit" value="Aggiungi al carrello">
</form>

<%	
	if(utente != null && "ADMIN".equals(utente.getRuolo())) {
%>
		<hr>
		<h3>Operazioni admin</h3>
		<form action="<%= request.getContextPath()%>/GestioneProdottiServlet" method="post">
			<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
			
			<select name="idPiattaforma">
			<option value="">Seleziona piattaforma</option>
		
			<%
			for(PiattaformaBean piattaforma : piattaforme) {
			%>
        		<option value="<%= piattaforma.getIdPiattaforma() %>">
            	<%= piattaforma.getNomePiattaforma() %>
        		</option>
    		<%
    		}
    		%>
			</select>
			
			<input type="submit" name="azione" value="Aggiungi piattaforma"><br>
			
			<select name="idGenere">
			<option value="">Seleziona genere</option>

			<%
			for(GenereBean genere : generi) {
			%>
       			<option value="<%= genere.getIdGenere() %>">
           		<%= genere.getNomeGenere() %>
       			</option>
    		<%
    		}
    		%>
			</select>
			<input type="submit" name="azione" value="Aggiungi genere"><br>
			
			<input type="submit" name="azione" value="Modifica prodotto">
			<input type="submit" name="azione" value="Elimina prodotto">
			<input type="submit" name="azione" value="Crea offerta">
		</form>

<%
	}
%>

</body>
</html>