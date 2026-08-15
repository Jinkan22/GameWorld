<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/components/header.jsp" %>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Profilo</title>
</head>
<body>

<main class="pagina-profilo">

	<h2>PROFILO<%= "ADMIN".equals(utente.getRuolo()) ? " ADMIN" : "" %></h2>

	<section class="informazioni-profilo" id="informazioni-profilo">

		<h3>INFORMAZIONI PERSONALI</h3>

		<div class="dato-profilo">
			<strong>Nome</strong>
			<span><%= utente.getNome() %></span>
		</div>

		<div class="dato-profilo">
			<strong>Cognome</strong>
			<span><%= utente.getCognome() %></span>
		</div>

		<div class="dato-profilo">
			<strong>Email</strong>
			<span><%= utente.getEmail() %></span>
		</div>

		<div class="dato-profilo">
			<strong>Data di nascita</strong>
			<span><%= sdf.format(utente.getDataNascita()) %></span>
		</div>

		<div class="dato-profilo">
			<strong>Numero di telefono</strong>
			<span><%= utente.getNumeroTelefono() %></span>
		</div>
		
	</section>

	<section class="modifica-profilo" id="modifica-profilo">

		<h3>MODIFICA PROFILO</h3>

		<form action="<%= request.getContextPath() %>/ModificaProfiloServlet" method="post">

			<div class="campo-profilo">
				<label for="nome">Nome</label>
				<input type="text" id="nome" name="nome" value="<%= utente.getNome() %>" required>
			</div>

			<div class="campo-profilo">
				<label for="cognome">Cognome</label>
				<input type="text" id="cognome" name="cognome" value="<%= utente.getCognome() %>" required>
			</div>

			<div class="campo-profilo">
				<label for="email">Email</label>
				<input type="email" id="email" name="email" value="<%= utente.getEmail() %>" required>
			</div>

			<div class="campo-profilo">
				<label for="dataNascita">Data di nascita</label>
				<input type="date" id="dataNascita" name="dataNascita" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(utente.getDataNascita()) %>" required>
			</div>

			<div class="campo-profilo">
				<label for="numeroTelefono">Numero di telefono</label>
				<input type="tel" id="numeroTelefono" name="numeroTelefono" value="<%= utente.getNumeroTelefono() %>" required>
			</div>

			<div class="bottoni-profilo">
				<button type="button" id="annulla-button">Annulla</button>
				<button type="submit">Salva</button>
			</div>
		</form>
	</section>
	
	<%
		if(utente != null && "ADMIN".equals(utente.getRuolo())) {
	%>
	<section class="dashboard-admin" id="dashboard-admin">
	
		<h3>ADMIN DASHBOARD</h3>
	
		<div class="azioni-dashboard">
			<a href="<%= request.getContextPath() %>/GestioneProdottiServlet">
				<button>Gestione prodotti</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneOrdiniServlet">
				<button>Gestione ordini</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneUtentiServlet">
				<button>Gestione utenti</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneOfferteServlet">
				<button>Gestione offerte</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneTagServlet">
				<button>Gestione tag</button>
			</a>
			
			<button class="torna-profilo" type="button" id="chiudi-dashboard-button">Torna al profilo</button>
		</div>
	</section>
	<%
		}
	%>

	<section class="azioni-profilo">

		<%
			if(utente != null && "ADMIN".equals(utente.getRuolo())) {
		%>
			<button type="button" id="dashboard-button">Admin dashboard</button>
		<%
			}
		%>
		
		<button type="button" id="modifica-button">Modifica profilo</button>

		<a href="<%= request.getContextPath() %>/StoricoOrdiniServlet">
			<button>Storico ordini</button>
		</a>

		<a href="<%= request.getContextPath() %>/LogoutServlet">
			<button>Logout</button>
		</a>
		
	</section>
</main>

<script>
	const informazioniProfilo = document.getElementById("informazioni-profilo");
	const modificaProfilo = document.getElementById("modifica-profilo");
	const dashboardAdmin = document.getElementById("dashboard-admin");

	const modificaButton = document.getElementById("modifica-button");
	const annullaButton = document.getElementById("annulla-button");
	const dashboardButton = document.getElementById("dashboard-button");
	const chiudiDashboardButton = document.getElementById("chiudi-dashboard-button");

	modificaButton.addEventListener("click", function() {
		informazioniProfilo.style.display = "none";
		modificaProfilo.style.display = "block";

		if(dashboardAdmin)
			dashboardAdmin.style.display = "none";
	});

	annullaButton.addEventListener("click", function() {
		modificaProfilo.style.display = "none";
		informazioniProfilo.style.display = "block";

		if(dashboardAdmin)
			dashboardAdmin.style.display = "none";
	});

	if(dashboardButton) {
		dashboardButton.addEventListener("click", function() {
			informazioniProfilo.style.display = "none";
			modificaProfilo.style.display = "none";
			dashboardAdmin.style.display = "block";
		});
	}

	if(chiudiDashboardButton) {
		chiudiDashboardButton.addEventListener("click", function() {
			dashboardAdmin.style.display = "none";
			informazioniProfilo.style.display = "block";
		});
	}

</script>

<%@ include file="/jsp/components/footer.jsp" %>
</body>
</html>