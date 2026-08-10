<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OrdineBean" %>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Gestione ordini</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/AdminDashboardServlet">Dashboard</a><br>

<h1>Gestione ordini</h1>
<hr>

<%
String errore = (String) request.getAttribute("errore");

if(errore != null){
%>

<p><%= errore %><br><br>

<% 
}
    ArrayList<OrdineBean> ordini = (ArrayList<OrdineBean>) request.getAttribute("ordini");

    if(ordini != null) {
        for(OrdineBean ordine : ordini) {
%>
			<p><strong>Ordine #<%= ordine.getIdOrdine() %></strong></p>
			<p><strong>Acquirente:</strong> <%= ordine.getAcquirente() %></p>
			<p><strong>Indirizzo di fatturazione:</strong> <%= ordine.getIndirizzoFatturazione() %></p>
			<p><strong>Data:</strong> <%= sdf.format(ordine.getDataOrdine()) %></p>
			<p><strong>Totale:</strong> <%= ordine.getTotale() %> €</p>
			<a href="<%= request.getContextPath() %>/DettagliOrdineServlet?idOrdine=<%= ordine.getIdOrdine() %>">Visualizza ordine</a><br><br>
			
			<br><hr>
<%	
        }
    }
%>
</body>
</html>