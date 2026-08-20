function mostraErrore(input, messaggio) {
	const contenitore = input.parentElement;

	let errore = contenitore.querySelector(".errore");

	if(!errore) {
		errore = document.createElement("p");
		errore.className = "errore";
		contenitore.appendChild(errore);
	}

	errore.textContent = messaggio;
}

function rimuoviErrore(input) {
	const contenitore = input.parentElement;
	const errore = contenitore.querySelector(".errore");

	if(errore) {
		errore.remove();
	}
}


function validaNome(input) {
	const valore = input.value.trim();
	const regex = /^[A-Za-zÀ-ÖØ-öø-ÿ' ]+$/;

	if(valore === "") {
		mostraErrore(input, "Il nome è obbligatorio.");
		return false;
	}

	if(!regex.test(valore)) {
		mostraErrore(input, "Il nome contiene caratteri non validi.");
		return false;
	}

	rimuoviErrore(input);
	return true;
}


function validaCognome(input) {
	const valore = input.value.trim();
	const regex = /^[A-Za-zÀ-ÖØ-öø-ÿ' ]+$/;

	if(valore === "") {
		mostraErrore(input, "Il cognome è obbligatorio.");
		return false;
	}

	if(!regex.test(valore)) {
		mostraErrore(input, "Il cognome contiene caratteri non validi.");
		return false;
	}

	rimuoviErrore(input);
	return true;
}


function validaEmail(input) {
	const valore = input.value.trim();

	const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

	if(valore === "") {
		mostraErrore(input, "L'email è obbligatoria.");
		return false;
	}

	if(!regex.test(valore)) {
		mostraErrore(input, "Inserisci un indirizzo email valido.");
		return false;
	}

	rimuoviErrore(input);
	return true;
}


function validaPassword(input) {
	const valore = input.value;

	if(valore === "") {
		mostraErrore(input, "La password è obbligatoria.");
		return false;
	}

	if(valore.length < 8) {
		mostraErrore(input, "La password deve contenere almeno 8 caratteri.");
		return false;
	}

	rimuoviErrore(input);
	return true;
}


function validaDataNascita(input) {
	const valore = input.value;

	if(valore === "") {
		mostraErrore(input, "La data di nascita è obbligatoria.");
		return false;
	}

	const data = new Date(valore);
	const oggi = new Date();

	if(data > oggi) {
		mostraErrore(input, "La data di nascita non può essere futura.");
		return false;
	}

	rimuoviErrore(input);
	return true;
}


function validaTelefono(input) {
	const valore = input.value.trim();

	const regex = /^[0-9]{9,15}$/;

	if(valore === "") {
		mostraErrore(input, "Il numero di telefono è obbligatorio.");
		return false;
	}

	if(!regex.test(valore)) {
		mostraErrore(input, "Inserisci un numero di telefono valido.");
		return false;
	}

	rimuoviErrore(input);
	return true;
}


function validaNomeProdotto(input) {
    const valore = input.value.trim();

    if(valore === "") {
        mostraErrore(input, "Inserisci il nome del prodotto.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaDescrizioneProdotto(input) {
    const valore = input.value.trim();

    if(valore === "") {
        mostraErrore(input, "Inserisci la descrizione del prodotto.");
        return false;
    }

    if(valore.length < 10) {
        mostraErrore(input, "La descrizione deve contenere almeno 10 caratteri.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaPrezzoProdotto(input) {
    const valore = input.value.trim();

    if(valore === "") {
        mostraErrore(input, "Inserisci il prezzo del prodotto.");
        return false;
    }

    const prezzo = parseFloat(valore);

    if(isNaN(prezzo)) {
        mostraErrore(input, "Inserisci un prezzo valido.");
        return false;
    }

    if(prezzo < 0) {
        mostraErrore(input, "Il prezzo non può essere negativo.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaImmagineProdotto(input) {
    const valore = input.value.trim();

    if(valore === "") {
        mostraErrore(input, "Inserisci il nome dell'immagine.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaDataUscitaProdotto(input) {
    const valore = input.value;

    if(valore === "") {
        mostraErrore(input, "Inserisci la data di uscita.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaSviluppatoreProdotto(input) {
    const valore = input.value.trim();

    if(valore === "") {
        mostraErrore(input, "Inserisci lo sviluppatore.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaPercentualeSconto(input) {
    const valore = input.value.trim();
    const percentuale = parseInt(valore);

    if(valore === "") {
        mostraErrore(input, "Inserisci la percentuale di sconto.");
        return false;
    }

    if(isNaN(percentuale) || percentuale < 0 || percentuale > 100) {
        mostraErrore(input, "La percentuale deve essere compresa tra 0 e 100.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaDataInizioOfferta(input) {

    if(input.value === "") {
        mostraErrore(input, "Inserisci la data di inizio.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaDataFineOfferta(input, dataInizio) {

    if(input.value === "") {
        mostraErrore(input, "Inserisci la data di fine.");
        return false;
    }

    if(dataInizio.value !== "" && input.value < dataInizio.value) {
        mostraErrore(input, "La data di fine non può essere precedente alla data di inizio.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}


function validaQuantitaPiattaforma(input) {

    const valore = input.value.trim();
    const quantita = parseInt(valore);

    if(valore === "") {
        mostraErrore(input, "Inserisci una quantità.");
        return false;
    }

    if(isNaN(quantita) || quantita < 0) {
        mostraErrore(input, "La quantità non può essere negativa.");
        return false;
    }

    rimuoviErrore(input);
    return true;
}

function validaSelezione(input, messaggio) {

    if(input.value === "") {
        mostraErrore(input, messaggio);
        return false;
    }

    rimuoviErrore(input);
    return true;
}


document.addEventListener("DOMContentLoaded", function() {

	/* LOGIN */

	const formLogin = document.getElementById("form-login");

	if(formLogin) {

		const email = formLogin.querySelector('input[name="email"]');
		const password = formLogin.querySelector('input[name="password"]');

		email.addEventListener("change", function() {
			validaEmail(email);
		});

		password.addEventListener("change", function() {
			validaPassword(password);
		});

		formLogin.addEventListener("submit", function(event) {

			const emailValida = validaEmail(email);
			const passwordValida = validaPassword(password);

			if(!emailValida || !passwordValida) {
				event.preventDefault();
			}
		});
	}


	/* REGISTRAZIONE */

	const formRegistrazione = document.getElementById("form-registrazione");

	if(formRegistrazione) {

		const nome = formRegistrazione.querySelector('input[name="nome"]');
		const cognome = formRegistrazione.querySelector('input[name="cognome"]');
		const email = formRegistrazione.querySelector('input[name="email"]');
		const password = formRegistrazione.querySelector('input[name="password"]');
		const dataNascita = formRegistrazione.querySelector('input[name="dataNascita"]');
		const telefono = formRegistrazione.querySelector('input[name="numeroTelefono"]');

		nome.addEventListener("change", function() {
			validaNome(nome);
		});

		cognome.addEventListener("change", function() {
			validaCognome(cognome);
		});

		email.addEventListener("change", function() {
			validaEmail(email);
		});

		password.addEventListener("change", function() {
			validaPassword(password);
		});

		dataNascita.addEventListener("change", function() {
			validaDataNascita(dataNascita);
		});

		telefono.addEventListener("change", function() {
			validaTelefono(telefono);
		});


		formRegistrazione.addEventListener("submit", function(event) {

			const nomeValido = validaNome(nome);
			const cognomeValido = validaCognome(cognome);
			const emailValida = validaEmail(email);
			const passwordValida = validaPassword(password);
			const dataValida = validaDataNascita(dataNascita);
			const telefonoValido = validaTelefono(telefono);

			if(!nomeValido ||
			   !cognomeValido ||
			   !emailValida ||
			   !passwordValida ||
			   !dataValida ||
			   !telefonoValido) {

				event.preventDefault();
			}
		});
	}


	/* MODIFICA PROFILO */

	const formModificaProfilo = document.getElementById("form-modifica-profilo");

	if(formModificaProfilo) {

		const nome = formModificaProfilo.querySelector('input[name="nome"]');
		const cognome = formModificaProfilo.querySelector('input[name="cognome"]');
		const dataNascita = formModificaProfilo.querySelector('input[name="dataNascita"]');
		const telefono = formModificaProfilo.querySelector('input[name="numeroTelefono"]');

		nome.addEventListener("change", function() {
			validaNome(nome);
		});

		cognome.addEventListener("change", function() {
			validaCognome(cognome);
		});

		dataNascita.addEventListener("change", function() {
			validaDataNascita(dataNascita);
		});

		telefono.addEventListener("change", function() {
			validaTelefono(telefono);
		});


		formModificaProfilo.addEventListener("submit", function(event) {

			const nomeValido = validaNome(nome);
			const cognomeValido = validaCognome(cognome);
			const dataValida = validaDataNascita(dataNascita);
			const telefonoValido = validaTelefono(telefono);

			if(!nomeValido ||
			   !cognomeValido ||
			   !dataValida ||
			   !telefonoValido) {

				event.preventDefault();
			}
		});
	}
	
	
	/* AGGIUNGI PRODOTTO */
	
	const formAggiungiProdotto = document.getElementById("form-aggiungi-prodotto");

	if(formAggiungiProdotto) {

		const nome = document.getElementById("nome");
		const descrizione = document.getElementById("descrizione");
		const prezzo = document.getElementById("prezzo");
		const immagine = document.getElementById("immagine");
		const dataUscita = document.getElementById("dataUscita");
		const sviluppatore = document.getElementById("sviluppatore");


		nome.addEventListener("change", function() {
			validaNomeProdotto(nome);
		});

		descrizione.addEventListener("change", function() {
			validaDescrizioneProdotto(descrizione);
		});

		prezzo.addEventListener("change", function() {
			validaPrezzoProdotto(prezzo);
		});

		immagine.addEventListener("change", function() {
			validaImmagineProdotto(immagine);
		});

		dataUscita.addEventListener("change", function() {
			validaDataUscitaProdotto(dataUscita);
		});

		sviluppatore.addEventListener("change", function() {
			validaSviluppatoreProdotto(sviluppatore);
		});


		formAggiungiProdotto.addEventListener("submit", function(event) {

			const nomeValido = validaNomeProdotto(nome);
			const descrizioneValida = validaDescrizioneProdotto(descrizione);
			const prezzoValido = validaPrezzoProdotto(prezzo);
			const immagineValida = validaImmagineProdotto(immagine);
			const dataUscitaValida = validaDataUscitaProdotto(dataUscita);
			const sviluppatoreValido = validaSviluppatoreProdotto(sviluppatore);


			if(!nomeValido ||
				!descrizioneValida ||
				!prezzoValido ||
				!immagineValida ||
				!dataUscitaValida ||
				!sviluppatoreValido) {

				event.preventDefault();
			}
		});
	}
	
	
	/* MODIFICA PRODOTTO */
	
	const formModificaProdotto = document.getElementById("form-modifica-prodotto");

	if(formModificaProdotto) {

	    const nome = document.getElementById("nome");
	    const descrizione = document.getElementById("descrizione");
	    const prezzo = document.getElementById("prezzo");
	    const immagine = document.getElementById("immagine");
	    const dataUscita = document.getElementById("dataUscita");
	    const sviluppatore = document.getElementById("sviluppatore");


	    nome.addEventListener("change", function() {
	        validaNomeProdotto(nome);
	    });

	    descrizione.addEventListener("change", function() {
	        validaDescrizioneProdotto(descrizione);
	    });

	    prezzo.addEventListener("change", function() {
	        validaPrezzoProdotto(prezzo);
	    });

	    immagine.addEventListener("change", function() {
	        validaImmagineProdotto(immagine);
	    });

	    dataUscita.addEventListener("change", function() {
	        validaDataUscitaProdotto(dataUscita);
	    });

	    sviluppatore.addEventListener("change", function() {
	        validaSviluppatoreProdotto(sviluppatore);
	    });


	    formModificaProdotto.addEventListener("submit", function(event) {
			
			if(event.submitter.value === "Elimina prodotto") {
				return;
			}

	        const nomeValido = validaNomeProdotto(nome);
	        const descrizioneValida = validaDescrizioneProdotto(descrizione);
	        const prezzoValido = validaPrezzoProdotto(prezzo);
	        const immagineValida = validaImmagineProdotto(immagine);
	        const dataValida = validaDataUscitaProdotto(dataUscita);
	        const sviluppatoreValido = validaSviluppatoreProdotto(sviluppatore);

	        if(!nomeValido ||
	           !descrizioneValida ||
	           !prezzoValido ||
	           !immagineValida ||
	           !dataValida ||
	           !sviluppatoreValido) {

	            event.preventDefault();
	        }
	    });
	}
	
	
	/* AGGIUNGI OFFERTA */

	const formOfferta = document.getElementById("form-offerta");

	if(formOfferta) {

	    const percentualeSconto = document.getElementById("percentualeSconto");
	    const dataInizio = document.getElementById("dataInizio");
	    const dataFine = document.getElementById("dataFine");


	    percentualeSconto.addEventListener("change", function() {
	        validaPercentualeSconto(percentualeSconto);
	    });

	    dataInizio.addEventListener("change", function() {
	        validaDataInizioOfferta(dataInizio);
	    });

	    dataFine.addEventListener("change", function() {
	        validaDataFineOfferta(dataFine, dataInizio);
	    });


	    formOfferta.addEventListener("submit", function(event) {

	        const percentualeValida = validaPercentualeSconto(percentualeSconto);
	        const dataInizioValida = validaDataInizioOfferta(dataInizio);
	        const dataFineValida = validaDataFineOfferta(dataFine, dataInizio);

	        if(!percentualeValida ||
	           !dataInizioValida ||
	           !dataFineValida) {

	            event.preventDefault();
	        }
	    });
	}
	
	
	/* AGGIUNGI PIATTAFORMA */

	const formPiattaforma = document.querySelector(".form-aggiungi-piattaforma");

	if(formPiattaforma) {

	    const select = formPiattaforma.querySelector("select");

	    select.addEventListener("change", function() {
	        validaSelezione(select, "Seleziona una piattaforma.");
	    });

	    formPiattaforma.addEventListener("submit", function(event) {

	        if(!validaSelezione(select, "Seleziona una piattaforma.")) {
	            event.preventDefault();
	        }
	    });
	}
	
	
	/* AGGIUNGI GENERE */

	const formGenere = document.querySelector(".form-aggiungi-genere");

	if(formGenere) {

	    const select = formGenere.querySelector("select");

	    select.addEventListener("change", function() {
	        validaSelezione(select, "Seleziona un genere.");
	    });

	    formGenere.addEventListener("submit", function(event) {

	        if(!validaSelezione(select, "Seleziona un genere.")) {
	            event.preventDefault();
	        }
	    });
	}
});