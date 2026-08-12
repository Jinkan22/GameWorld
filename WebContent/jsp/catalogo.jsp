<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Catalogo</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>

<h1>Catalogo</h1>

<form action="<%= request.getContextPath() %>/CatalogoServlet" method="get">

    <input type="text" name="ricerca" placeholder="Cerca un videogioco..."
           value="<%= (request.getAttribute("ricerca") != null) ? request.getAttribute("ricerca") : "" %>">
           
	<input type="submit" value="Cerca">

    <br><br>

    <strong>Piattaforme:</strong>

    <%
    ArrayList<PiattaformaBean> piattaforme = (ArrayList<PiattaformaBean>) request.getAttribute("piattaforme");
    ArrayList<Integer> idPiattaformeSelezionate = (ArrayList<Integer>) request.getAttribute("idPiattaforme");

    if(piattaforme != null) {
        for(PiattaformaBean piattaforma : piattaforme) {
            boolean selezionata = idPiattaformeSelezionate != null && idPiattaformeSelezionate.contains(piattaforma.getIdPiattaforma());
    %>
        		<label>
            		<input type="checkbox" name="idPiattaforme" value="<%= piattaforma.getIdPiattaforma() %>" <%= selezionata ? "checked" : "" %>>

          			<%= piattaforma.getNomePiattaforma() %>
       			</label>
    <%
        }
    }
    %>

    <br><br>

    <strong>Generi:</strong>

    <%
    ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");
	ArrayList<Integer> idGeneriSelezionati = (ArrayList<Integer>) request.getAttribute("idGeneri");

    if(generi != null) {
        for(GenereBean genere : generi) {
            boolean selezionato = idGeneriSelezionati != null && idGeneriSelezionati.contains(genere.getIdGenere());
    %>

       		<label>
            	<input type="checkbox" name="idGeneri" value="<%= genere.getIdGenere() %>" <%= selezionato ? "checked" : "" %>>

            	<%= genere.getNomeGenere() %>
        	</label>
    <%
        }
    }
    %>

    <br><br>

    <input type="submit" value="Filtra">

</form>

<hr>
<%
    ArrayList<ProdottoViewBean> prodotti = (ArrayList<ProdottoViewBean>) request.getAttribute("prodotti");

    if(prodotti != null && !prodotti.isEmpty()) {
        for(ProdottoViewBean prodotto : prodotti) {
%>
			<a href="<%= request.getContextPath() %>/PaginaProdottoServlet?idProdotto=<%= prodotto.getProdotto().getIdProdotto() %>">
				<h2><%= prodotto.getProdotto().getNome() %></h2></a>

			<p>Prezzo: <%= prodotto.getProdotto().getPrezzo() %> €</p>
			<%
				if(prodotto.getOfferta() != null) {
			%>
					<p style="color:red"><strong>IN OFFERTA A <%= prodotto.getPrezzoScontato() %> € FINO AL <%= sdf.format(prodotto.getOfferta().getDataFine()) %>!</strong></p>

			<%
				}
			%>
			<p>Descrizione: <%= prodotto.getProdotto().getDescrizione() %></p>
						
			<form action="<%= request.getContextPath()%>/AggiungiAlCarrelloServlet" method="post">
				<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
				
				<select name="idPiattaforma" required>
					<option value="">Seleziona piattaforma</option>

					<%
					for(PiattaformaBean piattaforma : prodotto.getPiattaforme()) {
					%>
						<option value="<%= piattaforma.getIdPiattaforma() %>">
							<%= piattaforma.getNomePiattaforma() %>
						</option>
					<%
					}
					%>

				</select>
			
				<input type="submit" value="Aggiungi al carrello">
			</form>
			<br><hr>
<%
        }
    }
    else {
%>
		<p>Non ci sono prodotti corrispondenti alla tua ricerca</p>
<%
    }
%>

</body>
</html>