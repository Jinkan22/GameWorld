package model;

public class DettaglioOrdineBean {
	private int idDettaglio;
	private int quantita;
	private float prezzoAcquisto;
	private int idOrdine;
	private int idProdotto;
	
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
	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	
	//setters
	public void setIdDettaglio(int idDettaglio) {
		this.idDettaglio = idDettaglio;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public void setPrezzoAquisto(float prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
}
