package model;

public class UtenteBean {
	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String indirizzo;
	private String metodoPagamento;
	private String ruolo;
	
	//empty constructor
	public UtenteBean() {
		
	}
	
	//getters
	public int getIdUtente() {
		return idUtente;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public String getMetodoPagamento() {
		return metodoPagamento;
	}
	public String getRuolo() {
		return ruolo;
	}
	
	//setters
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}
