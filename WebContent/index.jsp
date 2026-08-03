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

<%
UtenteBean utente = (UtenteBean) session.getAttribute("utente");

if(utente != null){
%>

<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo utente</a><br><br>

<%
}
else{
%>

<a href="<%= request.getContextPath() %>/LoginServlet">Login</a><br><br>

<a href="<%= request.getContextPath() %>/RegistrazioneServlet">Registrazione</a><br><br>

<%
}
%>

<a href="<%= request.getContextPath() %>/CatalogoServlet">Catalogo prodotti</a>

</body>
</html>