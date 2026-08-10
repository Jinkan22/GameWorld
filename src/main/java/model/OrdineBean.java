package model;

import java.sql.Timestamp;

public class OrdineBean {
	private int idOrdine;
	private String acquirente;
	private Timestamp dataOrdine;
	private float totale;
	private String indirizzoFatturazione;
	private int idUtente;
	
	//empty constructor
	public OrdineBean() {
		
	}
	
	//getters
	public int getIdOrdine() {
		return idOrdine;
	}
	public String getAcquirente() {
		return acquirente;
	}
	public Timestamp getDataOrdine() {
		return dataOrdine;
	}
	public float getTotale() {
		return totale;
	}
	public String getIndirizzoFatturazione() {
		return indirizzoFatturazione;
	}
	public int getIdUtente() {
		return idUtente;
	}
	
	//setters
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public void setAcquirente(String acquirente) {
		this.acquirente = acquirente;
	}
	public void setDataOrdine(Timestamp dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public void setTotale(float totale) {
		this.totale = totale;
	}
	public void setIndirizzoFatturazione(String indirizzoFatturazione) {
		this.indirizzoFatturazione = indirizzoFatturazione;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
}
