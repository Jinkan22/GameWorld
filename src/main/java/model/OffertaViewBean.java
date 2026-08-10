package model;

import java.math.BigDecimal;

public class OffertaViewBean {
	private OffertaBean offerta;
	private ProdottoBean prodotto;
	private BigDecimal prezzoScontato;
	
	//empty constructor
	public OffertaViewBean() {
	}

	//getters
	public OffertaBean getOfferta() {
		return offerta;
	}
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public BigDecimal getPrezzoScontato() {
		return prezzoScontato;
	}

	//setters
	public void setOfferta(OffertaBean offerta) {
		this.offerta = offerta;
	}
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	public void setPrezzoScontato(BigDecimal prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}
}