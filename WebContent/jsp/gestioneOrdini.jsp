<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OrdineViewBean" %>
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
<a href="<%= request.getContextPath() %>/AdminDashboardServlet">Gestione prodotti</a><br>

<h1>Gestione ordini</h1>
<hr>

<%
    ArrayList<OrdineViewBean> ordini = (ArrayList<OrdineViewBean>) request.getAttribute("ordini");

    if(ordini != null) {
        for(OrdineViewBean ordine : ordini) {
%>
			<p>Ordine #<%= ordine.getOrdine().getIdOrdine() %></p><br>
			<p>Utente: <%= ordine.getUtente().getNome() + " " + ordine.getUtente().getCognome() %></p>
			<p>Data: <%= sdf.format(ordine.getOrdine().getDataOrdine()) %></p>
			<p>Totale: <%= ordine.getOrdine().getTotale() %> €</p>
						
			<form action="<%= request.getContextPath()%>/GestioneOrdiniServlet" method="post">
			
			<label>Stato:</label><br>
			<input type="radio" id="inAttesa<%= ordine.getOrdine().getIdOrdine() %>" name="statoOrdine" value="IN ATTESA" required
			<%= ordine.getOrdine().getStatoOrdine().equals("IN ATTESA") ? "checked" : "" %>>
			<label for="inAttesa<%= ordine.getOrdine().getIdOrdine() %>">In attesa</label><br>
		
			<input type="radio" id="spedito<%= ordine.getOrdine().getIdOrdine() %>" name="statoOrdine" value="SPEDITO"
			<%= ordine.getOrdine().getStatoOrdine().equals("SPEDITO") ? "checked" : "" %>>
			<label for="spedito<%= ordine.getOrdine().getIdOrdine() %>">Spedito</label><br>
			
			<input type="radio" id="consegnato<%= ordine.getOrdine().getIdOrdine() %>" name="statoOrdine" value="CONSEGNATO"
			<%= ordine.getOrdine().getStatoOrdine().equals("CONSEGNATO") ? "checked" : "" %>>
			<label for="consegnato<%= ordine.getOrdine().getIdOrdine() %>">Consegnato</label><br>
			
			<input type="radio" id="annullato<%= ordine.getOrdine().getIdOrdine() %>" name="statoOrdine" value="ANNULLATO"
			<%= ordine.getOrdine().getStatoOrdine().equals("ANNULLATO") ? "checked" : "" %>>
			<label for="annullato<%= ordine.getOrdine().getIdOrdine() %>">Annullato</label><br><br>
			
			<input type="hidden" name="idOrdine" value="<%= ordine.getOrdine().getIdOrdine() %>">
			<input type="submit" value="Aggiorna stato">
			</form>
			<hr>
<%	
        }
    }
%>

</body>
</html>