<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OffertaViewBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

ArrayList<OffertaViewBean> offerte = (ArrayList<OffertaViewBean>) request.getAttribute("offerte");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Gestione offerte</title>
</head>
<body>

<main class="pagina-gestione-offerte">

    <h2>GESTIONE OFFERTE</h2>

    <%
    	String errore = (String) request.getAttribute("errore");

    	if(errore != null) {
    %>
        	<p class="errore"><%= errore %></p>
    <%
    	}
    %>

    <section class="lista-offerte">
	    <%
	    if(offerte != null && !offerte.isEmpty()) {
	
	        for(OffertaViewBean offerta : offerte) {
	    %>
		        <div class="card-offerta">
		            <h3>Offerta #<%= offerta.getOfferta().getIdOfferta() %></h3>
		
		            <div class="dato-offerta">
		                <strong>Prodotto</strong>
		                <span><%= offerta.getProdotto().getNome() %></span>
		            </div>
		
		            <div class="dato-offerta">
		                <strong>Data inizio</strong>
		                <span><%= sdf.format(offerta.getOfferta().getDataInizio()) %></span>
		            </div>
		
		            <div class="dato-offerta">
		                <strong>Data fine</strong>
		                <span><%= sdf.format(offerta.getOfferta().getDataFine()) %></span>
		            </div>
		
		            <div class="dato-offerta">
		                <strong>Prezzo originale</strong>
		                <span><%= offerta.getProdotto().getPrezzo() %> €</span>
		            </div>
		
		            <div class="dato-offerta">
		                <strong>Sconto</strong>
		                <span><%= offerta.getOfferta().getPercentualeSconto() %>%</span>
		            </div>
		
		            <div class="dato-offerta">
		                <strong>Prezzo scontato</strong>
		                <span><%= offerta.getPrezzoScontato() %> €</span>
		            </div>
		
		            <form action="<%= request.getContextPath() %>/GestioneOfferteServlet" method="post">
		                <input type="hidden" name="idOfferta" value="<%= offerta.getOfferta().getIdOfferta() %>">
		
		                <input type="submit" value="Elimina offerta">
		            </form>
		        </div>
	    <%
	        }
	    }
	    else {
	    %>
	        <p class="nessuna-offerta">Non sono presenti offerte.</p>
	    <%
	    }
	    %>
    </section>
</main>

<%@ include file="/jsp/components/footer.jsp" %>

</body>
</html>