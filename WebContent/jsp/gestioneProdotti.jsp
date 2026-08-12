<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Gestione prodotti</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/AdminDashboardServlet">Dashboard</a><br>

<h1>Gestione prodotti</h1>
<hr>

<%
String errore = (String) request.getAttribute("errore");

if(errore != null){
%>

<p><%= errore %><br><br>

<% 
}
%>

	<a href="<%= request.getContextPath() %>/AggiungiProdottoServlet">Aggiungi un prodotto</a>
	<hr>

<%
    ArrayList<ProdottoViewBean> prodotti = (ArrayList<ProdottoViewBean>) request.getAttribute("prodotti");
	ArrayList<PiattaformaBean> piattaforme = (ArrayList<PiattaformaBean>) request.getAttribute("piattaforme");
	ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");

    if(prodotti != null) {
        for(ProdottoViewBean prodotto : prodotti) {
%>
			<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getProdotto().getIdProdotto() %>">
				<h2><%= prodotto.getProdotto().getNome() %></h2></a>

			<p>Prezzo: <%= prodotto.getProdotto().getPrezzo() %> €</p>
			<p>Piattaforme:</p>
			<ul>
			<%
				for(PiattaformaBean piattaforma : prodotto.getPiattaforme()) {
					%>
					<li><%= piattaforma.getNomePiattaforma() %>
					<%
				}
			%>
			</ul>
			<p>Generi:</p>
			<ul>
			<%
				for(GenereBean genere : prodotto.getGeneri()) {
					%>
					<li><%= genere.getNomeGenere() %>
					<%
				}
			%>
			</ul>
						
			<form action="<%= request.getContextPath()%>/ModificaProdottoServlet" method="get">
				<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
			
				<input type="submit" name="azione" value="Gestisci prodotto">
			</form>
			<hr>
<%
        }
    }
%>

</body>
</html>