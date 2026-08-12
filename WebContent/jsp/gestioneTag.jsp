<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Gestione tag</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/AdminDashboardServlet">Dashboard</a><br>

<h1>Gestione tag</h1>
<hr>

<%
String errore = (String) request.getAttribute("errore");

if(errore != null){
%>

<p><%= errore %><br><br>

<% 
}
%>
	<form action="<%= request.getContextPath()%>/GestioneTagServlet" method="post">
		<p><strong>Aggiungi piattaforma</strong></p>
		<input type="text" name="nomePiattaforma">
		<input type="hidden" name="azione" value="aggiungiPiattaforma">
		<input type="submit" value="+">
	</form>
	
	<p><strong>Elimina piattaforme</strong></p>
<%
    ArrayList<PiattaformaBean> piattaforme = (ArrayList<PiattaformaBean>) request.getAttribute("piattaforme");

    if(piattaforme != null) {
        for(PiattaformaBean piattaforma : piattaforme) {
%>
			<p>- <%= piattaforma.getNomePiattaforma() %></p>
			
			<form action="<%= request.getContextPath()%>/GestioneTagServlet" method="post">
			
			<input type="hidden" name="idPiattaforma" value="<%= piattaforma.getIdPiattaforma() %>">
			<input type="hidden" name="azione" value="eliminaPiattaforma">
			<input type="submit" value="X">
			</form>
<%	
        }
    }
%>
	<hr>
	<form action="<%= request.getContextPath()%>/GestioneTagServlet" method="post">
		<p><strong>Aggiungi genere</strong></p>
		<input type="text" name="nomeGenere">
		<input type="hidden" name="azione" value="aggiungiGenere">
		<input type="submit" value="+">
	</form>
	
	<p><strong>Elimina generi</strong></p>
<%
    ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");

    if(generi != null) {
        for(GenereBean genere : generi) {
%>
			<p>- <%= genere.getNomeGenere() %></p>
						
			<form action="<%= request.getContextPath()%>/GestioneTagServlet" method="post">
			
			<input type="hidden" name="idGenere" value="<%= genere.getIdGenere() %>">
			<input type="hidden" name="azione" value="eliminaGenere">
			<input type="submit" value="X">
			</form>
<%	
        }
    }
%>

</body>
</html>