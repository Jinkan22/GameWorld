<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OrdineBean" %>
<%@ page import="model.OrdineViewBean" %>
<%@ page import="model.DettaglioOrdineBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

ArrayList<OrdineViewBean> ordini = (ArrayList<OrdineViewBean>) request.getAttribute("ordini");

String dataInizio = (String) request.getAttribute("dataInizio");
String dataFine = (String) request.getAttribute("dataFine");
String idUtente = (String) request.getAttribute("idUtente");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Gestione ordini</title>
</head>
<body>

<main class="pagina-storico-ordini">

    <h2>GESTIONE ORDINI</h2>
    
    <div class="filtri-ordini">
		<form action="<%= request.getContextPath() %>/GestioneOrdini" method="get">
			<div class="dataInizio-ordini">
				<label for="dataInizio">Data inizio</label>
				<input type="date" id="dataInizio" name="dataInizio" value="<%= dataInizio != null ? dataInizio : "" %>">
			</div>
			
			<div class="dataFine-ordini">
				<label for="dataFine">Data fine</label>
				<input type="date" id="dataFine" name="dataFine" value="<%= dataFine != null ? dataFine : "" %>">
			</div>
			
			<div class="idUtente ordini">
				<label for="prezzo">ID Utente</label>
				<input type="number" id="idUtente" name="idUtente" min="0" value="<%= idUtente != null ? idUtente : "" %>">
			</div>
			
			<input type="submit" name="azione" value="Filtra ordini">
			<input type="submit" name="azione" value="Mostra tutti">
    	</form>
    </div>

    <%
    if(ordini != null && !ordini.isEmpty()) {

        for(OrdineViewBean ordineView : ordini) {

            OrdineBean ordine = ordineView.getOrdine();
            ArrayList<DettaglioOrdineBean> dettagli = ordineView.getDettagli();
    %>

        <section class="ordine">

            <div class="intestazione-ordine">
                <div>
                    <strong>Ordine #<%= ordine.getIdOrdine() %></strong>
                    <span><%= sdf.format(ordine.getDataOrdine()) %></span>
                </div>
                <div>
                    <strong>Totale:</strong>
                    <span><%= ordine.getTotale() %> €</span>
                </div>
            </div>

            <div class="dati-ordine">
                <div>
                    <span><strong>Acquirente:</strong> <%= ordine.getAcquirente() %></span>
                    
                </div>
                <div>
                    <span><strong>Indirizzo di fatturazione:</strong> <%= ordine.getIndirizzoFatturazione() %></span>
                </div>
            </div>

            <div class="prodotti-ordine">

                <h3>PRODOTTI</h3>

                <%
                if(dettagli != null && !dettagli.isEmpty()) {

                    for(DettaglioOrdineBean dettaglio : dettagli) {
                %>

                    <div class="prodotto-ordine">

                        <div class="dati-prodotto-ordine">

                            <strong>
                                <%= dettaglio.getNomeProdotto() %>
                            </strong>

							<div>
                                <strong>Piattaforma:</strong>
                                <span>
                                    <%= dettaglio.getNomePiattaforma() %>
                                </span>
                            </div>

                            <div>
                                <strong>Quantità:</strong>
                                <span>
                                    <%= dettaglio.getQuantita() %>
                                </span>
                            </div>

                        </div>

                        <div class="prezzo-prodotto-ordine">
                            <strong>Prezzo:</strong>
                            <span>
                                <%= dettaglio.getPrezzoAcquisto() %> €
                            </span>
                        </div>
                    </div>
                <%
                    }
                }
                %>
            </div>
        </section>
    <%
        }

    } else {
    %>
        <p>Non ci sono ordini che corrispondono ai filtri impostati</p>
    <%
    }
    %>
</main>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>