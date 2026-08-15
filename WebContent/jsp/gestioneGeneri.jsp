<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Gestione generi</title>
</head>
<body>


<main class="pagina-gestione-generi">

	<h2>GESTIONE GENERI</h2>

	<section class="card-generi">

		<%
		String errore = (String) request.getAttribute("errore");

		if(errore != null){
		%>
			<p class="errore-generi"><%= errore %></p>
		<%
		}
		%>

		<form class="aggiungi-genere" action="<%= request.getContextPath()%>/GestioneGeneriServlet" method="post">
			<input type="text" name="nomeGenere" placeholder="Nuovo genere..." required>
			<input type="hidden" name="azione" value="aggiungiGenere">
			<input type="submit" value="+">
		</form>

		<div class="lista-generi">
			<%
			ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");

			if(generi != null && !generi.isEmpty()) {
				for(GenereBean genere : generi) {
			%>
				<div class="genere">
					<span><%= genere.getNomeGenere() %></span>

					<form action="<%= request.getContextPath()%>/GestioneGeneriServlet" method="post">
						<input type="hidden" name="idGenere" value="<%= genere.getIdGenere() %>">
						<input type="hidden" name="azione" value="eliminaGenere">
						<input type="submit" value="×">
					</form>
				</div>
			<%
				}
			}
			else {
			%>
				<p>Nessun genere presente.</p>
			<%
			}
			%>
		</div>

	</section>

</main>

<%@ include file="/jsp/components/footer.jsp" %>
</body>
</html>