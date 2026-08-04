<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
<%@ page import="model.CarrelloViewBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Carrello</title>
</head>
<body>

<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>

<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo</a><br>

<h1>Carrello</h1>
<hr>

<%
    ArrayList<CarrelloViewBean> carrello = (ArrayList<CarrelloViewBean>) request.getAttribute("carrello");

    if(!carrello.isEmpty() && carrello != null) {
        for(CarrelloViewBean elemento : carrello) {
        	
        	ProdottoBean prodotto = elemento.getProdotto();
%>
			<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getIdProdotto() %>"><%= prodotto.getNome() %></a>
			<p>Prezzo: <%= prodotto.getPrezzo() %> €</p>
			<p>Quantita: <%= elemento.getQuantita() %></p><br>
			<img src="<%= request.getContextPath() + "/images/products/" + prodotto.getImmagine() %>" width="200">
			
			<hr>
<%
        }
    }
    else{
%>
		<p>Il carrello è vuoto</p>
<%
	}
%>

</body>
</html>