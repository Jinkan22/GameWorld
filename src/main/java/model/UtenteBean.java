package model;

import java.sql.Date;

public class UtenteBean {
	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Date dataNascita;
	private String numeroTelefono;
	private String ruolo;
	
	//costruttore vuoto
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
	public Date getDataNascita() {
		return dataNascita;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
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
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}
