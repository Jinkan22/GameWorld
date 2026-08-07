package model;

public class DettaglioOrdineViewBean {
	private ProdottoBean prodotto;
	private int quantita;
	private float prezzoAcquisto;
	
	//empty constructor
	public DettaglioOrdineViewBean() {
			
	}
			
	//getters
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public int getQuantita() {
		return quantita;
	}
	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	
	//setters
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public void setPrezzoAcquisto(float prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
}
