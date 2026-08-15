<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.ElementoCarrelloViewBean" %>
<%@ page import="model.IndirizzoBean" %>
<%@ page import="model.MetodoPagamentoBean" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
	ArrayList<ElementoCarrelloViewBean> carrello = (ArrayList<ElementoCarrelloViewBean>) request.getAttribute("carrello");
	ArrayList<IndirizzoBean> indirizzi = (ArrayList<IndirizzoBean>) request.getAttribute("indirizzi");
	ArrayList<MetodoPagamentoBean> metodiPagamento = (ArrayList<MetodoPagamentoBean>) request.getAttribute("metodiPagamento");
	BigDecimal totale = BigDecimal.ZERO;
	BigDecimal sconto = BigDecimal.ZERO;
	BigDecimal totaleScontato = BigDecimal.ZERO;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Checkout</title>
</head>
<body>

<main class="pagina-checkout">
    <section class="contenuto-checkout">

        <h2>CHECKOUT</h2>
        
        <%
        	String errore = (String) request.getAttribute("errore");
        	if(errore != null) {
        %>
        		<p><%= errore %></p>
       	<%
        	}
       	%>

        <section class="riepilogo-prodotti">
            <%
            if(carrello != null && !carrello.isEmpty()) {

                for(int i = 0; i < carrello.size(); i++) {

                    ElementoCarrelloViewBean elemento = carrello.get(i);

                    ProdottoBean prodotto = elemento.getProdotto();
                    PiattaformaBean piattaforma = elemento.getPiattaforma();

                    BigDecimal prezzoFinale;
					totale = totale.add(prodotto.getPrezzo().multiply(BigDecimal.valueOf(elemento.getQuantita())));

                    if(elemento.getOfferta() != null) {
                        prezzoFinale = elemento.getPrezzoScontato();
                    }
                    else {
                    	prezzoFinale = prodotto.getPrezzo();
                    }

                    totaleScontato = totaleScontato.add(prezzoFinale.multiply(BigDecimal.valueOf(elemento.getQuantita())));
            %>

	                <div class="prodotto-checkout">
	
	                    <strong class="nome-prodotto">
	                        <%= prodotto.getNome() %>
	                    </strong>
	
	                    <div>
	                        <span><%= prezzoFinale %> €</span>
	                    </div>
	
	                    <div>
	                        <span><%= piattaforma.getNomePiattaforma() %></span>
	                    </div>
	
	                    <div>
	                        <span>Quantità: <%= elemento.getQuantita() %></span>
	                    </div>
	
	                </div>

	                <%
	                if(i < carrello.size() - 1) {
	                %>
	                    <hr>
	                <%
	                }
				}
                sconto = totale.subtract(totaleScontato);
            }
            else {
            %>
                <p>Il carrello è vuoto.</p>
            <%
            }
            %>

        </section>

        <form id="form-checkout" action="<%= request.getContextPath() %>/CheckoutServlet" method="post">

            <!-- INDIRIZZO -->
            <section class="indirizzo">

                <h3>Indirizzo di fatturazione</h3>

                <%
                if(indirizzi != null && !indirizzi.isEmpty()) {

                    for(IndirizzoBean indirizzo : indirizzi) {
                %>
                    <div class="opzione-indirizzo">
                        <input type="radio" id="indirizzo<%= indirizzo.getIdIndirizzo() %>" name="indirizzo"
                        	value="<%= indirizzo.getIdIndirizzo() %>" required>
                        <label for="indirizzo<%= indirizzo.getIdIndirizzo() %>">
                            <%= indirizzo.getVia() %>,
                            <%= indirizzo.getCap() %>,
                            <%= indirizzo.getCitta() %>,
                            <%= indirizzo.getProvincia() %>,
                            <%= indirizzo.getPaese() %>
                        </label>
                    </div>
                <%
                    }
                }
                else {
                %>
                    <p>Nessun indirizzo salvato.</p>
                <%
                }
                %>

                <button type="button" id="aggiungi-indirizzo">
                    + Aggiungi indirizzo
                </button>

            </section>

            <section class="metodo-pagamento">

                <h3>Metodo di pagamento</h3>
                <%
                if(metodiPagamento != null && !metodiPagamento.isEmpty()) {

                    for(MetodoPagamentoBean metodo : metodiPagamento) {
                %>
                    <div class="opzione-pagamento">
                        <input type="radio" id="metodoPagamento<%= metodo.getIdMetodoPagamento() %>" name="metodoPagamento"
                        	value="<%= metodo.getIdMetodoPagamento() %>" required >
                        <label for="metodoPagamento<%= metodo.getIdMetodoPagamento() %>">
                            <%= metodo.getCircuito() %>,
                            <%= metodo.getNumeroCarta() %>,
                            <%= metodo.getIntestatario() %>,
                            <%= sdf.format(metodo.getDataScadenza()) %>
                        </label>
                    </div>
                <%
                    }
                }
                else {
                %>
                    <p>Nessun metodo di pagamento salvato.</p>
                <%
                }
                %>

                <button type="button" id="aggiungi-metodo">
                    + Aggiungi metodo di pagamento
                </button>
            </section>
        </form>
    </section>

    <aside class="riepilogo-totale">
		<div class="totale">
			<strong>Totale: </strong>
			<span><%= totale %> €</span>
		</div>
		<div class="sconto">
			<strong>Sconto: </strong>
			<span>- <%= sconto %> €</span>
		</div>
		<hr>
		<div class="totaleScontato">
			<strong>Totale scontato: </strong>
			<span><%= totaleScontato %> €</span>
		</div>
		
		<form action="<%= request.getContextPath() %>/CheckoutServlet" method="post">
			<input form="form-checkout" type="submit" value="Acquista">
		</form>
	</aside>
    
</main>

<%@ include file="/jsp/components/footer.jsp" %>

</body>
</html>