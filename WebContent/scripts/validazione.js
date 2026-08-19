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
		errore.textContent = "";
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
});