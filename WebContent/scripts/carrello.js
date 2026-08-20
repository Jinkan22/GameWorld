function createXMLHttpRequest() {
	var request;

	try {
		request = new XMLHttpRequest();
	} catch(e) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e) {
				return null;
			}
		}
	}

	return request;
}


function loadAjaxDoc(url, method, params, cFunction) {

	var request = createXMLHttpRequest();

	if(request) {

		request.onreadystatechange = function() {
			if(this.readyState == 4) {
				if(this.status == 200) {
					cFunction(this);
				}
			}
		};

		if(method.toLowerCase() == "get") {
			if(params) {
				request.open("GET", url + "?" + params, true);
			}
			else {
				request.open("GET", url, true);
			}
			request.send(null);
		}
		else {
			request.open("POST", url, true);
			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			request.send(params);
		}
	}
}

/* MODIFICA QUANTITÀ */

function modificaQuantita(form, azione) {

	var idProdotto = form.querySelector('input[name="idProdotto"]').value;
	var idPiattaforma = form.querySelector('input[name="idPiattaforma"]').value;

	var params =
		"azione=" + encodeURIComponent(azione)
		+ "&idProdotto=" + encodeURIComponent(idProdotto)
		+ "&idPiattaforma=" + encodeURIComponent(idPiattaforma);

	loadAjaxDoc("ModificaCarrello", "POST", params, function(request) {
		gestisciRispostaCarrello(request, form);
	});
}

/* GESTIONE RISPOSTA */

function gestisciRispostaCarrello(request, form) {

	var risposta = JSON.parse(request.responseText);
	var errore = document.querySelector(".errore");

	if(risposta.successo) {
		errore.textContent = "";
		
		document.getElementById("totale").textContent = risposta.totale.toFixed(2) + " €";
		document.getElementById("sconto").textContent = "- " + risposta.sconto.toFixed(2) + " €";
		document.getElementById("totaleScontato").textContent = risposta.totaleScontato.toFixed(2) + " €";

		if(risposta.quantita === 0) {
			var prodotto = form.closest(".prodotto-carrello");
			prodotto.remove();
		}
		else {
			var quantita = form.querySelector(".quantita-prodotto");
			quantita.textContent = risposta.quantita;
		}
	}
	else {
		errore.textContent = risposta.errore;
	}
}

document.addEventListener("DOMContentLoaded", function() {

	var formQuantita = document.querySelectorAll(".form-quantita");

	for(var i = 0; i < formQuantita.length; i++) {
		
		var form = formQuantita[i];

		var decrementa = form.querySelector(".decrementa");
		var incrementa = form.querySelector(".incrementa");
		var rimuovi = form.querySelector(".rimuovi");


		decrementa.addEventListener("click", function(event) {
			event.preventDefault();
			var form = this.closest(".form-quantita");
			modificaQuantita(form, "-");
		});


		incrementa.addEventListener("click", function(event) {
			event.preventDefault();
			var form = this.closest(".form-quantita");
			modificaQuantita(form, "+");
		});


		rimuovi.addEventListener("click", function(event) {
			event.preventDefault();
			var form = this.closest(".form-quantita");
			modificaQuantita(form, "Rimuovi");
		});
	}
});