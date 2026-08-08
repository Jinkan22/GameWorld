<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<p>Quantità disponibile: <%= prodotto.getProdotto().getQuantitaDisponibile() %></p>
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
			<hr>
<%
        }
    }
%>

</body>
</html>