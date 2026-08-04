package model;

public class CarrelloViewBean {
	private ProdottoBean prodotto;
	private int quantita;
	
	//empty constructor
	public CarrelloViewBean() {
		
	}
		
	//getters
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public int getQuantita() {
		return quantita;
	}
	
	//setters
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
