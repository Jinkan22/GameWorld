package model;

import java.util.ArrayList;

public class OrdineViewBean {
	private OrdineBean ordine;
	private ArrayList<DettaglioOrdineBean> dettagli;
	
	//costruttore vuoto
	public OrdineViewBean() {
		
	}
	
	//getters
	public OrdineBean getOrdine() {
		return ordine;
	}
	public ArrayList<DettaglioOrdineBean> getDettagli() {
		return dettagli;
	}
	
	//setters
	public void setOrdine(OrdineBean ordine) {
		this.ordine = ordine;
	}
	public void setDettagli(ArrayList<DettaglioOrdineBean> dettagli) {
		this.dettagli = dettagli;
	}
}
