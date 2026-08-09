package model;

public class DettaglioOrdineViewBean {
	private ProdottoBean prodotto;
	private PiattaformaBean piattaforma;
	private int quantita;
	private float prezzoAcquisto;
	
	//empty constructor
	public DettaglioOrdineViewBean() {
			
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
	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
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
	public void setPrezzoAcquisto(float prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
}
