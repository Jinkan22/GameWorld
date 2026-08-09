<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.ProdottoPiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Modifica prodotto</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>
<a href="<%= request.getContextPath() %>/GestioneProdottiServlet">Gestione prodotti</a><br>

<h1>Modifica prodotto</h1>

<%
String messaggio = (String) request.getAttribute("messaggio");

if(messaggio != null){
%>

<p><%= messaggio %><br><br>

<% 
}
	ProdottoViewBean prodotto = (ProdottoViewBean) request.getAttribute("prodotto");
	ArrayList<PiattaformaBean> piattaforme = (ArrayList<PiattaformaBean>) request.getAttribute("piattaforme");
	ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");
%>

<form action="<%= request.getContextPath()%>/ModificaProdottoServlet" method="post">

	<label>Nome prodotto</label><br>
	<input type="text" name="nome" value="<%= prodotto.getProdotto().getNome() %>" required><br><br>
	
	<label>Descrizione</label><br>
	<textarea name="descrizione" rows="5" cols="50" required><%= prodotto.getProdotto().getDescrizione() %></textarea><br><br>
	
	<label>Prezzo</label><br>
	<input type="number" name="prezzo" min="0" step="0.01" value="<%= prodotto.getProdotto().getPrezzo() %>" required><br><br>
		
	<label>Immagine</label><br>
	<input type="text" name="immagine" value="<%= prodotto.getProdotto().getImmagine() %>" required><br><br>
	
	<label>Data di uscita</label><br>
	<input type="date" name="dataUscita" value="<%= prodotto.getProdotto().getDataUscita() %>" required><br><br>
	
	<label>Sviluppatore</label><br>
	<input type="text" name="sviluppatore" value="<%= prodotto.getProdotto().getSviluppatore() %>" required><br><br>
	
	<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
	<input type="submit" name="azione" value="Modifica prodotto">
	<input type="submit" name="azione" value="Elimina prodotto">
	
</form>
	<hr>
	
	<p>Piattaforme:</p>
	
	<ul>
	<%
		for(int i=0; i < prodotto.getPiattaforme().size(); i++) {
			ProdottoPiattaformaBean prodottoPiattaforma = prodotto.getProdottoPiattaforme().get(i);
		    PiattaformaBean piattaforma = prodotto.getPiattaforme().get(i);
			%>
				<li>
				<form action="<%= request.getContextPath()%>/ModificaProdottoServlet" method="post">
					<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
					<%= piattaforma.getNomePiattaforma() %> - Disponibili: 
					<input type="number" name="quantitaPiattaforma" value="<%= prodottoPiattaforma.getQuantitaDisponibile() %>" min="0">
			
					<input type="hidden" name="idPiattaforma" value="<%= piattaforma.getIdPiattaforma() %>">
					<input type="submit" name="azione" value="Modifica quantità">
					<input type="submit" name="azione" value="Elimina piattaforma">
				</form>
				</li>
			<%
		}
	%>
	</ul>
	
	<p>Generi:</p>
	<ul>
	<%
		for(GenereBean genere : prodotto.getGeneri()) {
			%>
			<li>
			<form action="<%= request.getContextPath()%>/ModificaProdottoServlet" method="post">
				<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
				<%= genere.getNomeGenere() %>
			
				<input type="hidden" name="idGenere" value="<%= genere.getIdGenere() %>">
				<input type="submit" name="azione" value="Elimina genere">
			</form>
			</li>
			<%
		}
	%>
	</ul>
	
<form action="<%= request.getContextPath()%>/ModificaProdottoServlet" method="post">
	<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">

	<select name="idPiattaforma">
		<option value="">Seleziona piattaforma</option>

		<%
		for(PiattaformaBean piattaforma : piattaforme) {
		%>
        	<option value="<%= piattaforma.getIdPiattaforma() %>">
            <%= piattaforma.getNomePiattaforma() %>
        	</option>
    	<%
    	}
    	%>
	</select>
		
	<input type="submit" name="azione" value="Aggiungi piattaforma"><br>
		
	<select name="idGenere">
		<option value="">Seleziona genere</option>

		<%
		for(GenereBean genere : generi) {
		%>
        	<option value="<%= genere.getIdGenere() %>">
        	<%= genere.getNomeGenere() %>
        	</option>
    	<%
    	}
    	%>
	</select>
	<input type="submit" name="azione" value="Aggiungi genere"><br>
	
	<input type="submit" name="azione" value="Crea offerta">

</form>

</body>
</html>