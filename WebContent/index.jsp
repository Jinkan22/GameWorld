<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Home</title>
</head>
<body>

<%
	ProdottoViewBean nuovaUscita = (ProdottoViewBean) request.getAttribute("nuovaUscita");
	ProdottoViewBean miglioreOfferta = (ProdottoViewBean) request.getAttribute("miglioreOfferta");
	ProdottoViewBean piuVenduto = (ProdottoViewBean) request.getAttribute("piuVenduto");
	
	ArrayList<ProdottoViewBean> giochiSteam = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiSteam");
	ArrayList<ProdottoViewBean> giochiPlaystation = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiPlaystation");
	ArrayList<ProdottoViewBean> giochiXbox = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiXbox");
	ArrayList<ProdottoViewBean> giochiNintendo = (ArrayList<ProdottoViewBean>) request.getAttribute("giochiNintendo");
%>

<main>
	<section class="banners">
		<div class="banner">
			<%
				if(nuovaUscita != null) {
			%>
				<a href="...">
					<img src="<%= request.getContextPath() + "/images/products/" + nuovaUscita.getProdotto().getImmagine() %>">
				</a>
				<h2>NUOVE USCITE</h2>
			<%
				}
			%>
		</div>
		<div class="banner">
			<%
				if(miglioreOfferta != null) {
			%>
				<a href="...">
					<img src="<%= request.getContextPath() + "/images/products/" + miglioreOfferta.getProdotto().getImmagine() %>">
				</a>
				<h2>IN OFFERTA OGGI</h2>
			<%
				}
			%>
		</div>
		<div class="banner">
			<%
				if(piuVenduto != null) {
			%>
				<a href="...">
					<img src="<%= request.getContextPath() + "/images/products/" + piuVenduto.getProdotto().getImmagine() %>">
				</a>
				<h2>I PIU VENDUTI</h2>
			<%
				}
			%>
		</div>
	</section>


</main>

</body>
</html>