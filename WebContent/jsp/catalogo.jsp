<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.PiattaformaBean" %>
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
<hr>

<%
    ArrayList<ProdottoViewBean> prodotti = (ArrayList<ProdottoViewBean>) request.getAttribute("prodotti");

    if(prodotti != null) {
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
%>

</body>
</html>