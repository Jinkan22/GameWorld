<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
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
String errore = (String) session.getAttribute("errore");

if(errore != null){
%>

<p><%= errore %><br><br>

<% 
}
    ArrayList<UtenteBean> utenti = (ArrayList<UtenteBean>) request.getAttribute("utenti");

    if(utenti != null) {
        for(UtenteBean utenteRegistrato : utenti) {
%>
			<p><strong>Utente #<%= utenteRegistrato.getIdUtente() %></strong></p>
			<p><strong>Nome e cognome:</strong> <%= utenteRegistrato.getNome() + " " + utenteRegistrato.getCognome() %></p>
			<p><strong>Email:</strong> <%= utenteRegistrato.getEmail() %></p>
			<p><strong>Data di nascita:</strong> <%= sdf.format(utenteRegistrato.getDataNascita()) %></p>
			<p><strong>Telefono:</strong> <%= utenteRegistrato.getNumeroTelefono() %></p>
			<p><strong>Ruolo:</strong> <%= utenteRegistrato.getRuolo() %></p>			
						
			<form action="<%= request.getContextPath()%>/GestioneUtentiServlet" method="post">
			
			<input type="hidden" name="idUtente" value="<%= utenteRegistrato.getIdUtente() %>">
			<input type="submit" name="azione" value="<%= "ADMIN".equals(utenteRegistrato.getRuolo()) ? "Declassa a USER" : "Promuovi ad ADMIN" %>">
			</form>
			<br><hr>
<%	
        }
    }
%>

</body>
</html>