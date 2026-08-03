<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Profilo</title>
</head>
<body>

<%
UtenteBean utente = (UtenteBean) session.getAttribute("utente");
%>

<h1>Profilo utente</h1>

<p><strong>Nome:</strong> <%= utente.getNome() %></p>
<p><strong>Cognome:</strong> <%= utente.getCognome() %></p>
<p><strong>Email:</strong> <%= utente.getEmail() %></p>
<p><strong>Ruolo:</strong> <%= utente.getRuolo() %></p>

</body>
</html>