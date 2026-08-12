<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OrdineBean" %>
<%@ page import="model.DettaglioOrdineViewBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Dettagli ordine</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/StoricoOrdiniServlet">Storico ordini</a><br>

<h1>Dettagli ordine</h1>

<%
	OrdineBean ordine = (OrdineBean) request.getAttribute("ordine");
	ArrayList<DettaglioOrdineViewBean> dettagliOrdine = (ArrayList<DettaglioOrdineViewBean>) request.getAttribute("dettagliOrdine");
%>
	<p><strong>Acquirente:</strong> <%= ordine.getAcquirente() %></p>
	<p><strong>Data dell'ordine:</strong> <%= sdf.format(ordine.getDataOrdine()) %></p>
	<p><strong>Indirizzo di fatturazione:</strong> <%= ordine.getIndirizzoFatturazione() %></p>
	<p><strong>Totale:</strong> <%= ordine.getTotale() %> €</p>
	<hr>
<%

    if(dettagliOrdine != null && !dettagliOrdine.isEmpty()) {
        for(DettaglioOrdineViewBean dettaglio : dettagliOrdine) {
%>
			<p>Prodotto: <%= dettaglio.getProdotto().getNome() %></p>
			<p>Piattaforma: <%=dettaglio.getPiattaforma().getNomePiattaforma() %></p>
			<p>Quantità:<%= dettaglio.getQuantita() %></p>
			<p>Prezzo: <%= dettaglio.getPrezzoAcquisto() %> €</p>
			<hr>
<%
        }
    }
%>

</body>
</html>