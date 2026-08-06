<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
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
    ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");

    if(prodotti != null) {
        for(ProdottoBean prodotto : prodotti) {
%>
			<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getIdProdotto() %>">
				<h2><%= prodotto.getNome() %></h2></a>

			<p>Prezzo: <%= prodotto.getPrezzo() %> €</p>
			<p>Quantità disponibile: <%= prodotto.getQuantitaDisponibile() %></p>
						
			<form action="<%= request.getContextPath()%>/GestioneProdottiServlet" method="post">
				<input type="hidden" name="idProdotto" value="<%= prodotto.getIdProdotto() %>">
			
				<input type="submit" name="azione" value="Modifica">
				<input type="submit" name="azione" value="Elimina">
			</form>
			<hr>
<%
        }
    }
%>

</body>
</html>