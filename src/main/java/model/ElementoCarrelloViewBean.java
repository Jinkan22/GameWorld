package model;

public class ElementoCarrelloViewBean {
	private ProdottoBean prodotto;
	private PiattaformaBean piattaforma;
	private int quantita;
	
	//empty constructor
	public ElementoCarrelloViewBean() {
		
	}
		
	//getters
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public PiattaformaBean getPiattaforma() {
		return piattaforma;
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
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
