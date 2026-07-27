package model;

public class ElementoCarrelloBean {
	private int idElementoCarrello;
	private int quantita;
	private int idUtente;
	private int idProdotto;
	
	//empty constructor
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
}
