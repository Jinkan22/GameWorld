<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OffertaViewBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Gestione offerte</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/AdminDashboardServlet">Dashboard</a><br>

<h1>Gestione offerte</h1>
<hr>

<%
String errore = (String) request.getAttribute("errore");

if(errore != null){
%>

<p><%= errore %><br><br>

<% 
}
    ArrayList<OffertaViewBean> offerte = (ArrayList<OffertaViewBean>) request.getAttribute("offerte");

    if(offerte != null) {
        for(OffertaViewBean offerta : offerte) {
%>
			<p><strong>Offerta #<%= offerta.getOfferta().getIdOfferta() %></strong></p>
			<p><strong>Prodotto:</strong> <%= offerta.getProdotto().getNome() %></p>
			<p><strong>Data inizio:</strong> <%= sdf.format(offerta.getOfferta().getDataInizio()) %></p>
			<p><strong>Data fine:</strong> <%= sdf.format(offerta.getOfferta().getDataFine()) %></p>
			<p><strong>Prezzo originale:</strong> <%= offerta.getProdotto().getPrezzo() %> €</p>
			<p><strong>Percentuale di sconto:</strong> <%= offerta.getOfferta().getPercentualeSconto() %>%</p>
			<p><strong>Prezzo scontato:</strong> <%= offerta.getPrezzoScontato() %> €</p><br>			
						
			<form action="<%= request.getContextPath()%>/GestioneOfferteServlet" method="post">
			
			<input type="hidden" name="idOfferta" value="<%= offerta.getOfferta().getIdOfferta() %>">
			<input type="submit" value="Elimina offerta">
			</form>
			<br><hr>
<%	
        }
    }
%>

</body>
</html>