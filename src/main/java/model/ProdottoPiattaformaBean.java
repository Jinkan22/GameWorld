package model;

public class ProdottoPiattaformaBean {
	private int idProdotto;
	private int idPiattaforma;
	private int quantitaDisponibile;
	
	//costruttore vuoto
	public ProdottoPiattaformaBean() {
		
	}
	
	//getters
	public int getIdProdotto() {
		return idProdotto;
	}
	public int getIdPiattaforma() {
		return idPiattaforma;
	}
	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}
	
	//setters
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setIdPiattaforma(int idPiattaforma) {
		this.idPiattaforma = idPiattaforma;
	}
	public void setQuantitaDisponibile(int quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}
}
