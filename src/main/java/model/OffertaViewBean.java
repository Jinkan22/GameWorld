package model;

public class OffertaViewBean {
	private OffertaBean offerta;
	private ProdottoBean prodotto;
	private float prezzoScontato;
	
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
	public float getPrezzoScontato() {
		return prezzoScontato;
	}

	//setters
	public void setOfferta(OffertaBean offerta) {
		this.offerta = offerta;
	}
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	public void setPrezzoScontato(float prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}
}