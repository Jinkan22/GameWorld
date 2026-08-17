<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
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
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/interfaccia.js"></script>
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

		<form action="<%= request.getContextPath() %>/ModificaProfilo" method="post">

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
				<button type="button" id="annulla-button" onclick="mostraInformazioniProfilo()">Annulla</button>
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
			<a href="<%= request.getContextPath() %>/GestioneProdotti">
				<button>Gestione prodotti</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneOrdini">
				<button>Gestione ordini</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneUtenti">
				<button>Gestione utenti</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneOfferte">
				<button>Gestione offerte</button>
			</a>
			<a href="<%= request.getContextPath() %>/GestioneGeneri">
				<button>Gestione generi</button>
			</a>
			
			<button class="torna-profilo" type="button" id="chiudi-dashboard-button" onclick="chiudiDashboard()">Torna al profilo</button>
		</div>
	</section>
	<%
		}
	%>

	<section class="azioni-profilo">

		<%
			if(utente != null && "ADMIN".equals(utente.getRuolo())) {
		%>
			<button type="button" id="dashboard-button" onclick="mostraDashboard()">Admin dashboard</button>
		<%
			}
		%>
		
		<button type="button" id="modifica-button" onclick="mostraModificaProfilo()">Modifica profilo</button>

		<a href="<%= request.getContextPath() %>/StoricoOrdini">
			<button>Storico ordini</button>
		</a>

		<a href="<%= request.getContextPath() %>/Logout">
			<button>Logout</button>
		</a>
		
	</section>
</main>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>