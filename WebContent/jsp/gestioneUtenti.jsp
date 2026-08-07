<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Gestione utenti</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/AdminDashboardServlet">Dashboard</a><br>

<h1>Gestione utenti</h1>
<hr>

<%
String errore = (String) request.getAttribute("errore");

if(errore != null){
%>

<p><%= errore %><br><br>

<% 
}
%>

<%
    ArrayList<UtenteBean> utenti = (ArrayList<UtenteBean>) request.getAttribute("utenti");

    if(utenti != null) {
        for(UtenteBean utente : utenti) {
%>
			<p><strong>Utente #<%= utente.getIdUtente() %></strong></p>
			<p><strong>Nome e cognome:</strong> <%= utente.getNome() + " " + utente.getCognome() %></p>
			<p><strong>Email:</strong> <%= utente.getEmail() %></p>
			<p><strong>Data di nascita:</strong> <%= sdf.format(utente.getDataNascita()) %></p>
			<p><strong>Telefono:</strong> <%= utente.getNumeroTelefono() %></p>
			<p><strong>Ruolo:</strong> <%= utente.getRuolo() %></p>			
						
			<form action="<%= request.getContextPath()%>/GestioneUtentiServlet" method="post">
			
			<input type="hidden" name="idUtente" value="<%= utente.getIdUtente() %>">
			<input type="submit" name="azione" value="Modifica ruolo">
			</form>
			<br><hr>
<%	
        }
    }
%>

</body>
</html>