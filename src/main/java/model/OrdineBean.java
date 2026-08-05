package model;

import java.sql.Timestamp;

public class OrdineBean {
	private int idOrdine;
	private Timestamp dataOrdine;
	private float totale;
	private String statoOrdine;
	private int idUtente;
	
	//empty constructor
	public OrdineBean() {
		
	}
	
	//getters
	public int getIdOrdine() {
		return idOrdine;
	}
	public Timestamp getDataOrdine() {
		return dataOrdine;
	}
	public float getTotale() {
		return totale;
	}
	public String getStatoOrdine() {
		return statoOrdine;
	}
	public int getIdUtente() {
		return idUtente;
	}
	
	//setters
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public void setDataOrdine(Timestamp dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public void setTotale(float totale) {
		this.totale = totale;
	}
	public void setStatoOrdine(String statoOrdine) {
		this.statoOrdine = statoOrdine;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
}
