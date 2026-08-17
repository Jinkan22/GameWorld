<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.ProdottoPiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<%@ page import="model.OffertaBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

String messaggio = (String) request.getAttribute("messaggio");

ProdottoViewBean prodotto = (ProdottoViewBean) request.getAttribute("prodotto");
ArrayList<PiattaformaBean> piattaforme = (ArrayList<PiattaformaBean>) request.getAttribute("piattaforme");
ArrayList<GenereBean> generi = (ArrayList<GenereBean>) request.getAttribute("generi");
ArrayList<OffertaBean> offerte = (ArrayList<OffertaBean>) request.getAttribute("offerte");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Gestione prodotto</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/interfaccia.js"></script>
</head>
<body>

<main class="pagina-gestione-prodotto">

	<h2>GESTIONE PRODOTTO</h2>

	<%
	if(messaggio != null) {
	%>
		<p><%= messaggio %></p>
	<%
	}
	%>

	<section class="card-gestione-dati-prodotto">

		<h3>DATI PRODOTTO</h3>

		<form action="<%= request.getContextPath() %>/ModificaProdotto" method="post">

			<div class="campo-gestione-prodotto">
				<label for="nome">Nome prodotto</label>
				<input type="text" id="nome" name="nome" value="<%= prodotto.getProdotto().getNome() %>" required>
			</div>

			<div class="campo-gestione-prodotto">
				<label for="descrizione">Descrizione</label>
				<textarea id="descrizione" name="descrizione" rows="5" required><%= prodotto.getProdotto().getDescrizione() %></textarea>
			</div>

			<div class="campo-gestione-prodotto">
				<label for="prezzo">Prezzo</label>
				<input type="number" id="prezzo" name="prezzo" min="0" step="0.01" value="<%= prodotto.getProdotto().getPrezzo() %>" required>
			</div>

			<div class="campo-gestione-prodotto">
				<label for="immagine">Immagine</label>
				<input type="text" id="immagine" name="immagine" value="<%= prodotto.getProdotto().getImmagine() %>" required>
			</div>

			<div class="campo-gestione-prodotto">
				<label for="dataUscita">Data di uscita</label>
				<input type="date" id="dataUscita" name="dataUscita" value="<%= prodotto.getProdotto().getDataUscita() %>" required>
			</div>

			<div class="campo-gestione-prodotto">
				<label for="sviluppatore">Sviluppatore</label>
				<input type="text" id="sviluppatore" name="sviluppatore" value="<%= prodotto.getProdotto().getSviluppatore() %>" required>
			</div>

			<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">

			<div class="bottoni-gestione-prodotto">
				<button type="submit" name="azione" value="Modifica prodotto">Modifica prodotto</button>
				<button type="submit" name="azione" value="Elimina prodotto">Elimina prodotto</button>
			</div>
		</form>
	</section>

	<section class="card-gestione-piattaforme">

		<h3>PIATTAFORME</h3>

		<div class="lista-gestione-piattaforme">

			<%
			for(int i = 0; i < prodotto.getPiattaforme().size(); i++) {
				ProdottoPiattaformaBean prodottoPiattaforma = prodotto.getProdottoPiattaforme().get(i);
				PiattaformaBean piattaforma = prodotto.getPiattaforme().get(i);
			%>
				<div class="riga-gestione-piattaforma">
				
					<form action="<%= request.getContextPath() %>/ModificaProdotto" method="post">
						<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
						<input type="hidden" name="idPiattaforma" value="<%= piattaforma.getIdPiattaforma() %>">

						<strong><%= piattaforma.getNomePiattaforma() %></strong>

						<label>Quantità</label>
						<input type="number" name="quantitaPiattaforma" value="<%= prodottoPiattaforma.getQuantitaDisponibile() %>" min="0">

						<button type="submit" name="azione" value="Modifica quantità">Modifica</button>
						<button type="submit" name="azione" value="Elimina piattaforma">Elimina</button>
					</form>
				</div>
			<%
			}
			%>

		</div>

		<form class="form-aggiungi-piattaforma" action="<%= request.getContextPath() %>/ModificaProdotto" method="post">
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

			<button type="submit" name="azione" value="Aggiungi piattaforma">Aggiungi piattaforma</button>
		</form>
	</section>

	<section class="card-gestione-generi">

		<h3>GENERI</h3>

		<div class="lista-gestione-generi">
		
			<%
			for(GenereBean genere : prodotto.getGeneri()) {
			%>
				<div class="riga-gestione-genere">
					<form action="<%= request.getContextPath() %>/ModificaProdotto" method="post">
						<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
						<input type="hidden" name="idGenere" value="<%= genere.getIdGenere() %>">

						<strong><%= genere.getNomeGenere() %></strong>

						<button type="submit" name="azione" value="Elimina genere">Elimina</button>
					</form>
				</div>
			<%
			}
			%>
		</div>

		<form class="form-aggiungi-genere" action="<%= request.getContextPath() %>/ModificaProdotto" method="post">
			<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">

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

			<button type="submit" name="azione" value="Aggiungi genere">Aggiungi genere</button>
		</form>
	</section>

	<section class="card-gestione-offerte">

		<h3>OFFERTE</h3>

		<div id="lista-offerte">

			<%
			if(offerte != null && !offerte.isEmpty()) {

				for(OffertaBean offerta : offerte) {
			%>

				<div class="riga-gestione-offerta">

					<div class="dati-gestione-offerta">
						<strong><%= offerta.getPercentualeSconto() %>% di sconto</strong>

						<span>
							<%= sdf.format(offerta.getDataInizio()) %>
							→
							<%= sdf.format(offerta.getDataFine()) %>
						</span>
					</div>

					<form action="<%= request.getContextPath() %>/ModificaProdotto" method="post">
						<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
						<input type="hidden" name="idOfferta" value="<%= offerta.getIdOfferta() %>">

						<button type="submit" name="azione" value="Elimina offerta">Elimina</button>
					</form>
				</div>
			<%
				}
			}
			else {
			%>
				<p>Nessuna offerta presente.</p>
			<%
			}
			%>
		</div>

		<div id="form-nuova-offerta">

			<form action="<%= request.getContextPath() %>/ModificaProdotto" method="post">
				<input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">

				<div class="campo-gestione-offerta">
					<label for="percentualeSconto">Percentuale di sconto</label>
					<input type="number" id="percentualeSconto" name="percentualeSconto" min="0" max="100" required>
				</div>

				<div class="campo-gestione-offerta">
					<label for="dataInizio">Data di inizio</label>
					<input type="date" id="dataInizio" name="dataInizio" required>
				</div>

				<div class="campo-gestione-offerta">
					<label for="dataFine">Data di fine</label>
					<input type="date" id="dataFine" name="dataFine" required>
				</div>

				<div class="bottoni-gestione-offerta">
					<button type="button" id="annulla-offerta-button" onclick="mostraListaOfferte()">Annulla</button>
					<button type="submit" name="azione" value="Crea offerta">Crea offerta</button>
				</div>
			</form>
		</div>

		<button type="button" id="crea-offerta-button" onclick="mostraFormNuovaOfferta()">Crea nuova offerta</button>
	</section>
</main>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>