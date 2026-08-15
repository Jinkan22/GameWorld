<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OrdineViewBean" %>
<%@ page import="model.OrdineBean" %>
<%@ page import="model.DettaglioOrdineViewBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

ArrayList<OrdineViewBean> ordini = (ArrayList<OrdineViewBean>) request.getAttribute("ordini");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Storico Ordini</title>
</head>
<body>

<main class="pagina-storico-ordini">

    <h2>STORICO ORDINI</h2>

    <%
    if(ordini != null && !ordini.isEmpty()) {

        for(OrdineViewBean ordineView : ordini) {

            OrdineBean ordine = ordineView.getOrdine();
            ArrayList<DettaglioOrdineViewBean> dettagli = ordineView.getDettagli();
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

                    for(DettaglioOrdineViewBean dettaglio : dettagli) {
                %>

                    <div class="prodotto-ordine">

                        <div class="dati-prodotto-ordine">

                            <strong>
                                <%= dettaglio.getProdotto().getNome() %>
                            </strong>

							<div>
                                <strong>Piattaforma:</strong>
                                <span>
                                    <%= dettaglio.getPiattaforma().getNomePiattaforma() %>
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
        <p>Non hai ancora effettuato ordini</p>
    <%
    }
    %>
</main>

<%@ include file="/jsp/components/footer.jsp" %>
</body>
</html>