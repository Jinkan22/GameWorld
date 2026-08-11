<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Home</title>
</head>
<body>

<h1>GameWorld</h1>

<form action="<%= request.getContextPath() %>/CatalogoServlet" method="get">
    <input type="text" name="ricerca" placeholder="Cerca un videogioco...">
    <input type="submit" value="Cerca">
</form>
<br>

<%
UtenteBean utente = (UtenteBean) session.getAttribute("utente");

if(utente != null) {
	if("ADMIN".equals(utente.getRuolo())) {
%>
		<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo admin</a><br><br>
<%
	}
	else {
%>
		<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo utente</a><br><br>
<% 
	}
}
else{
%>

<a href="<%= request.getContextPath() %>/LoginServlet">Login</a><br><br>

<a href="<%= request.getContextPath() %>/RegistrazioneServlet">Registrazione</a><br><br>

<%
}
%>

<a href="<%= request.getContextPath() %>/CarrelloServlet">Carrello</a><br><br>

<a href="<%= request.getContextPath() %>/CatalogoServlet">Catalogo</a>

</body>
</html>