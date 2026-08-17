//loginRegistrazione.jsp

function mostraLogin() {
	document.getElementById("registrazione").style.display = "none";
	document.getElementById("login").style.display = "block";
}

function mostraRegistrazione() {
	document.getElementById("login").style.display = "none";
	document.getElementById("registrazione").style.display = "block";
}


//profilo.jsp

function mostraModificaProfilo() {
	if(document.getElementById("dashboard-admin"))
		document.getElementById("dashboard-admin").style.display = "none";
	document.getElementById("informazioni-profilo").style.display = "none";
	document.getElementById("modifica-profilo").style.display = "block";
}

function mostraInformazioniProfilo() {
	if(document.getElementById("dashboard-admin"))
		document.getElementById("dashboard-admin").style.display = "none";
	document.getElementById("modifica-profilo").style.display = "none";
	document.getElementById("informazioni-profilo").style.display = "block";
}

function mostraDashboard() {
	document.getElementById("informazioni-profilo").style.display = "none";
	document.getElementById("modifica-profilo").style.display = "none";
	document.getElementById("dashboard-admin").style.display = "block";
}

function chiudiDashboard() {
	document.getElementById("dashboard-admin").style.display = "none";
	document.getElementById("modifica-profilo").style.display = "none";
	document.getElementById("informazioni-profilo").style.display = "block";
}


//gestioneProdotti.jsp

function mostraFormAggiungiProdotto() {
	document.getElementById("lista-prodotti").style.display = "none";
	document.getElementById("aggiungi-prodotto").style.display = "block";
}

function mostraListaProdotti() {
	document.getElementById("aggiungi-prodotto").style.display = "none";
	document.getElementById("lista-prodotti").style.display = "grid";
}


//modificaProdotto.jsp

function mostraFormNuovaOfferta() {
	document.getElementById("lista-offerte").style.display = "none";
	document.getElementById("crea-offerta-button").style.display = "none";
	document.getElementById("form-nuova-offerta").style.display = "block";
}

function mostraListaOfferte() {
	document.getElementById("form-nuova-offerta").style.display = "none";
	document.getElementById("lista-offerte").style.display = "block";
	document.getElementById("crea-offerta-button").style.display = "block";
}