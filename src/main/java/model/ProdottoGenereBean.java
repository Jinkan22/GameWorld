package model;

public class ProdottoGenereBean {
	private int idProdotto;
	private int idGenere;
	
	//costruttore vuoto
	public ProdottoGenereBean() {
		
	}
	
	//getters
	public int getIdProdotto() {
		return idProdotto;
	}
	public int getIdGenere() {
		return idGenere;
	}
	
	//setters
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setIdGenere(int idGenere) {
		this.idGenere = idGenere;
	}
}
