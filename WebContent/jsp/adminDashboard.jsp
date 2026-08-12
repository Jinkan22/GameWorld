<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Dashboard</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo</a><br>

<h1>Dashboard</h1>
<hr>

<a href="<%= request.getContextPath()%>/GestioneProdottiServlet"><strong>Gestione prodotti</strong></a><br><br>

<a href="<%= request.getContextPath()%>/GestioneOrdiniServlet"><strong>Gestione ordini</strong></a><br><br>

<a href="<%= request.getContextPath()%>/GestioneUtentiServlet"><strong>Gestione utenti</strong></a><br><br>

<a href="<%= request.getContextPath()%>/GestioneOfferteServlet"><strong>Gestione offerte</strong></a><br><br>

<a href="<%= request.getContextPath()%>/GestioneTagServlet"><strong>Gestione tag</strong></a>

</body>
</html>