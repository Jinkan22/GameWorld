<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OrdineBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Storico Ordini</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo</a><br>

<h1>Storico Ordini</h1>

<%
    ArrayList<OrdineBean> ordini = (ArrayList<OrdineBean>) request.getAttribute("ordini");

    if(ordini != null && !ordini.isEmpty()) {
        for(OrdineBean ordine : ordini) {
%>
			<p>Data dell'ordine: <%= sdf.format(ordine.getDataOrdine()) %></p>
			<p>Totale: <%= ordine.getTotale() %> €</p>
			<p>Stato dell'ordine: <%=ordine.getStatoOrdine() %></p>
			<a href="<%= request.getContextPath() %>/DettagliOrdineServlet?idOrdine=<%= ordine.getIdOrdine() %>">Visualizza ordine</a>
			<hr>
<%
        }
    }
    else{
%>
		<p>Non hai ancora effettuato ordini</p>
<%
	}
%>

</body>
</html>