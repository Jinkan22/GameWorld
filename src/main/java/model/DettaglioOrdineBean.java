package model;

import java.math.BigDecimal;

public class DettaglioOrdineBean {
	private int idDettaglio;
	private int quantita;
	private BigDecimal prezzoAcquisto;
	private int idOrdine;
	private int idProdotto;
	private int idPiattaforma;
	
	//empty constructor
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
	public int getIdOrdine() {
		return idOrdine;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public int getIdPiattaforma() {
		return idPiattaforma;
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
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setIdPiattaforma(int idPiattaforma) {
		this.idPiattaforma = idPiattaforma;
	}
}
