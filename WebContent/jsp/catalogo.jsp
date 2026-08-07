<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Catalogo</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>

<h1>Catalogo</h1>
<hr>

<%
    ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");

    if(prodotti != null) {
        for(ProdottoBean prodotto : prodotti) {
%>
			<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getIdProdotto() %>">
				<h2><%= prodotto.getNome() %></h2></a>

			<p>Prezzo: <%= prodotto.getPrezzo() %> €</p>
			<p>Descrizione: <%= prodotto.getDescrizione() %></p>
						
			<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
				<input type="hidden" name="idProdotto" value="<%= prodotto.getIdProdotto() %>">
			
				<input type="submit" value="Aggiungi al carrello">
			</form>
			<br><hr>
<%
        }
    }
%>

</body>
</html>