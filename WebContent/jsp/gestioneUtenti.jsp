<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Gestione utenti</title>
</head>
<body>

<main class="pagina-gestione-utenti">

	<h2>GESTIONE UTENTI</h2>

	<%
	String errore = (String) session.getAttribute("errore");

	if(errore != null){
	%>
		<p class="errore-utenti"><%= errore %></p>
	<%
	}
	%>

	<section class="lista-utenti">

		<%
		ArrayList<UtenteBean> utenti = (ArrayList<UtenteBean>) request.getAttribute("utenti");

		if(utenti != null && !utenti.isEmpty()) {
			for(UtenteBean utenteRegistrato : utenti) {
		%>

			<div class="utente">

				<h3>UTENTE #<%= utenteRegistrato.getIdUtente() %></h3>

				<div class="dati-utente">
					<p>
						<strong>Nome e cognome:</strong>
						<span><%= utenteRegistrato.getNome() + " " + utenteRegistrato.getCognome() %></span>
					</p>

					<p>
						<strong>Email:</strong>
						<span><%= utenteRegistrato.getEmail() %></span>
					</p>

					<p>
						<strong>Data di nascita:</strong>
						<span><%= sdf.format(utenteRegistrato.getDataNascita()) %></span>
					</p>

					<p>
						<strong>Telefono:</strong>
						<span><%= utenteRegistrato.getNumeroTelefono() %></span>
					</p>

					<p>
						<strong>Ruolo:</strong>
						<span><%= utenteRegistrato.getRuolo() %></span>
					</p>
				</div>

				<form action="<%= request.getContextPath()%>/GestioneUtentiServlet" method="post">
					<input type="hidden" name="idUtente" value="<%= utenteRegistrato.getIdUtente() %>">

					<input type="submit" name="azione" value="<%= "ADMIN".equals(utenteRegistrato.getRuolo()) ? "Declassa a USER" : "Promuovi ad ADMIN" %>">
				</form>

			</div>

		<%
			}
		}
		else {
		%>
			<p>Non sono presenti utenti.</p>
		<%
		}
		%>

	</section>

</main>

<%@ include file="/jsp/components/footer.jsp" %>
</body>
</html>