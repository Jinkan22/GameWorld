package model;

public class ElementoCarrelloBean {
	private int idElementoCarrello;
	private int quantita;
	private int idUtente;
	private int idProdotto;
	private int idPiattaforma;
	
	//costruttore vuoto
	public ElementoCarrelloBean() {
		
	}
	
	//getters
	public int getIdElementoCarrello() {
		return idElementoCarrello;
	}
	public int getQuantita() {
		return quantita;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public int getIdPiattaforma() {
		return idPiattaforma;
	}
	
	//setters
	public void setIdElementoCarrello(int idElementoCarrello) {
		this.idElementoCarrello = idElementoCarrello;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setIdPiattaforma(int idPiattaforma) {
		this.idPiattaforma = idPiattaforma;
	}
}
