package model;

import java.math.BigDecimal;

public class DettaglioOrdineBean {
	private int idDettaglio;
	private int quantita;
	private BigDecimal prezzoAcquisto;
	private int idProdotto;
	private String nomeProdotto;
	private String nomePiattaforma;
	private int idOrdine;
	
	//costruttore vuoto
	public DettaglioOrdineBean() {
		
	}
	
	//getters
	public int getIdDettaglio() {
		return idDettaglio;
	}
	public int getQuantita() {
		return quantita;
	}
	public BigDecimal getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public String getNomePiattaforma() {
		return nomePiattaforma;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	
	//setters
	public void setIdDettaglio(int idDettaglio) {
		this.idDettaglio = idDettaglio;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public void setPrezzoAcquisto(BigDecimal prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	public void setNomePiattaforma(String nomePiattaforma) {
		this.nomePiattaforma = nomePiattaforma;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
}
