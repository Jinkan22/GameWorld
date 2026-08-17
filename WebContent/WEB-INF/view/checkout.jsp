<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
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
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/interfaccia.js"></script>
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

        <form id="form-checkout" action="<%= request.getContextPath() %>/Checkout" method="post">

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

                <button type="button" id="aggiungi-indirizzo" onclick="mostraFormAggiungiIndirizzo()">
				    + Aggiungi indirizzo
				</button>
				
				<div id="nuovo-indirizzo">
				
			        <div class="campo-checkout">
			            <label for="via">Via</label>
			            <input type="text" id="via" name="via" form="form-aggiungi-indirizzo" required>
			        </div>
			
			        <div class="campo-checkout">
			            <label for="cap">CAP</label>
			            <input type="text" id="cap" name="cap" form="form-aggiungi-indirizzo" required>
			        </div>
			
			        <div class="campo-checkout">
			            <label for="citta">Città</label>
			            <input type="text" id="citta" name="citta" form="form-aggiungi-indirizzo" required>
			        </div>
			
			        <div class="campo-checkout">
			            <label for="provincia">Provincia</label>
			            <input type="text" id="provincia" name="provincia" form="form-aggiungi-indirizzo" required>
			        </div>
			
			        <div class="campo-checkout">
			            <label for="paese">Paese</label>
			            <input type="text" id="paese" name="paese" form="form-aggiungi-indirizzo" required>
			        </div>
			        
			        <div class="bottoni-inserimento">
				        <button type="button" id="annulla-inserimento" onclick="annullaFormAggiungiIndirizzo()">
				        	Annulla
				        </button>
				
				        <button type="submit" id="salva-indirizzo" form="form-aggiungi-indirizzo">
				            Salva indirizzo
				        </button>
			        </div>
			        
			    </div>
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

                <button type="button" id="aggiungi-metodo" onclick="mostraFormAggiungiMetodo()">
                    + Aggiungi metodo di pagamento
                </button>
                
                <div id="nuovo-metodo">
				
			        <div class="campo-checkout">
			            <label for="circuito">Circuito</label>
			            <input type="text" id="circuito" name="circuito" form="form-aggiungi-metodo" required>
			        </div>
			
			        <div class="campo-checkout">
			            <label for="numeroCarta">Numero carta</label>
			            <input type="text" id="numeroCarta" name="numeroCarta" form="form-aggiungi-metodo" required>
			        </div>
			
			        <div class="campo-checkout">
			            <label for="intestatario">Intestatario</label>
			            <input type="text" id="intestatario" name="intestatario" form="form-aggiungi-metodo" required>
			        </div>
			
			        <div class="campo-checkout">
			            <label for="dataScadenza">Data di scadenza</label>
			            <input type="date" id="dataScadenza" name="dataScadenza" form="form-aggiungi-metodo" required>
			        </div>
			        
			        <div class="bottoni-inserimento">
				        <button type="button" id="annulla-inserimento" onclick="annullaFormAggiungiMetodo()">
				        	Annulla
				        </button>
				
				        <button type="submit" id="salva-metodo" form="form-aggiungi-metodo">
				            Salva metodo di pagamento
				        </button>
			        </div>
			        
			    </div>
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
		
		<form action="<%= request.getContextPath() %>/Checkout" method="post">
			<input form="form-checkout" type="submit" value="Acquista">
		</form>
	</aside>
    
</main>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>