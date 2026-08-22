package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProdottoViewBean {
	private ProdottoBean prodotto;
	private OffertaBean offerta;
	private BigDecimal prezzoScontato;
	private ArrayList<GenereBean> generi;
	private ArrayList<ProdottoPiattaformaBean> prodottoPiattaforme;
	private ArrayList<PiattaformaBean> piattaforme;
	
	//costruttore vuoto
	public ProdottoViewBean(){
		
	}
	
	//getters
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public OffertaBean getOfferta() {
		return offerta;
	}
	public BigDecimal getPrezzoScontato() {
		return prezzoScontato;
	}
	public ArrayList<GenereBean> getGeneri() {
		return generi;
	}
	public ArrayList<ProdottoPiattaformaBean> getProdottoPiattaforme() {
		return prodottoPiattaforme;
	}
	public ArrayList<PiattaformaBean> getPiattaforme() {
		return piattaforme;
	}
	
	//setters
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	public void setOfferta(OffertaBean offerta) {
		this.offerta = offerta;
	}
	public void setPrezzoScontato(BigDecimal prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}
	public void setGeneri(ArrayList<GenereBean> generi) {
		this.generi = generi;
	}
	public void setProdottoPiattaforme(ArrayList<ProdottoPiattaformaBean> prodottoPiattaforme) {
		this.prodottoPiattaforme = prodottoPiattaforme;
	}
	public void setPiattaforme(ArrayList<PiattaformaBean> piattaforme) {
		this.piattaforme = piattaforme;
	}
}
