package model;

import java.math.BigDecimal;

public class ElementoCarrelloViewBean {
	private ProdottoBean prodotto;
	private PiattaformaBean piattaforma;
	private OffertaBean offerta;
	private BigDecimal prezzoScontato;
	private int quantita;
	
	//costruttore vuoto
	public ElementoCarrelloViewBean() {
		
	}
		
	//getters
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public PiattaformaBean getPiattaforma() {
		return piattaforma;
	}
	public OffertaBean getOfferta() {
		return offerta;
	}
	public BigDecimal getPrezzoScontato() {
		return prezzoScontato;
	}
	public int getQuantita() {
		return quantita;
	}
	
	//setters
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	public void setPiattaforma(PiattaformaBean piattaforma) {
		this.piattaforma = piattaforma;
	}
	public void setOfferta(OffertaBean offerta) {
		this.offerta = offerta;
	}
	public void setPrezzoScontato(BigDecimal prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
