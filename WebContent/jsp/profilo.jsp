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
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>

<%
UtenteBean utente = (UtenteBean) session.getAttribute("utente");
%>

<h1>Profilo utente</h1>

<p><strong>Nome:</strong> <%= utente.getNome() %></p>
<p><strong>Cognome:</strong> <%= utente.getCognome() %></p>
<p><strong>Email:</strong> <%= utente.getEmail() %></p>
<p><strong>Indirizzo:</strong> <%= utente.getIndirizzo() %></p>
<p><strong>Metodo di pagamento:</strong> <%= utente.getMetodoPagamento() %></p>

<a href="<%= request.getContextPath()%>/CarrelloServlet"><strong>Carrello</strong></a><br><br>

<a href="<%= request.getContextPath()%>/StoricoOrdiniServlet"><strong>Storico ordini</strong></a><br><br>

<a href="<%= request.getContextPath()%>/ModificaProfiloServlet"><strong>Modifica profilo</strong></a><br><br>

<a href="<%= request.getContextPath()%>/LogoutServlet"><strong>Logout</strong></a>

</body>
</html>