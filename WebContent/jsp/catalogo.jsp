<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");

    if(prodotti != null) {
        for(ProdottoBean prodotto : prodotti) {
%>
			<h2><%= prodotto.getNome() %></h2>
			<p>Prezzo: <%= prodotto.getPrezzo() %> €</p>
			<p>Descrizione: <%= prodotto.getDescrizione() %></p>
			<hr>
<%
        }
    }
%>

</body>
</html>