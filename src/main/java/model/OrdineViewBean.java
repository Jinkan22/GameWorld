package model;

import java.util.ArrayList;

public class OrdineViewBean {
	private OrdineBean ordine;
	private ArrayList<DettaglioOrdineViewBean> dettagli;
	
	//empty constructor
	public OrdineViewBean() {
		
	}
	
	//getters
	public OrdineBean getOrdine() {
		return ordine;
	}
	public ArrayList<DettaglioOrdineViewBean> getDettagli() {
		return dettagli;
	}
	
	//setters
	public void setOrdine(OrdineBean ordine) {
		this.ordine = ordine;
	}
	public void setDettagli(ArrayList<DettaglioOrdineViewBean> dettagli) {
		this.dettagli = dettagli;
	}
}
