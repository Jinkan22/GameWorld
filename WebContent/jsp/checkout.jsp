<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.ElementoCarrelloViewBean" %>
<%@ page import="model.IndirizzoBean" %>
<%@ page import="model.MetodoPagamentoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Checkout</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/CarrelloServlet">Carrello</a><br>

<h1>Checkout</h1>
<hr>

	<%
    ArrayList<ElementoCarrelloViewBean> carrello = (ArrayList<ElementoCarrelloViewBean>) request.getAttribute("carrello");

	float totale = 0;
	for(ElementoCarrelloViewBean elemento : carrello) {
		
		ProdottoBean prodotto = elemento.getProdotto();
		PiattaformaBean piattaforma = elemento.getPiattaforma();
	%>
		<p><strong>Nome:</strong> <%= prodotto.getNome() %></p>
		<p><strong>Piattaforma:</strong> <%= piattaforma.getNomePiattaforma() %>
		<p><strong>Prezzo:</strong> <%= prodotto.getPrezzo() %> €</p>
		<p><strong>Quantita:</strong> <%= elemento.getQuantita() %></p>
		
		<hr>
		
	<%
		totale += prodotto.getPrezzo() * elemento.getQuantita();
	}
	%>
	
	<p><strong>Totale:</strong> <%= totale %> €</p>
	<hr>
	
	<h3>Indirizzo di spedizione</h3>
	
	<form action="<%= request.getContextPath()%>/CheckoutServlet" method="post">
	<%
	ArrayList<IndirizzoBean> indirizzi = (ArrayList<IndirizzoBean>) request.getAttribute("indirizzi");
	if(indirizzi != null && !indirizzi.isEmpty()){
		for(IndirizzoBean indirizzo : indirizzi) {
		%>
			<input type="radio" id="indirizzo<%= indirizzo.getIdIndirizzo() %>" name="indirizzo" value="<%= indirizzo.getIdIndirizzo() %>" required>
			<label for="indirizzo<%= indirizzo.getIdIndirizzo() %>">
			<%= indirizzo.getVia() %>, 
			<%= indirizzo.getCap() %>, 
			<%= indirizzo.getCitta() %>,
			<%= indirizzo.getProvincia() %>,
			<%= indirizzo.getPaese() %>
			</label>
		<%
		}
	}
	%>
	
	<h3>Metodo di pagamento</h3>
	
	<%
	ArrayList<MetodoPagamentoBean> metodiPagamento = (ArrayList<MetodoPagamentoBean>) request.getAttribute("metodiPagamento");
	if(metodiPagamento != null && !metodiPagamento.isEmpty()){
		for(MetodoPagamentoBean metodo : metodiPagamento) {
		%>
			<input type="radio" id="metodoPagamento<%= metodo.getIdMetodoPagamento() %>" name="metodoPagamento" value="<%= metodo.getIdMetodoPagamento() %>" required>
			<label for="metodoPagamento<%= metodo.getIdMetodoPagamento() %>">
			<%= metodo.getCircuito() %>, 
			<%= metodo.getNumeroCarta() %>, 
			<%= metodo.getIntestatario() %>, 
			<%= metodo.getDataScadenza() %>
			</label>	
		<%
		}
	}
	%>
	
	<hr>
	
	<input type="submit" value="Acquista">
	
	</form>
	
</body>
</html>