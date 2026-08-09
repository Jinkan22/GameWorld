package model;

import java.sql.Timestamp;

public class OrdineBean {
	private int idOrdine;
	private Timestamp dataOrdine;
	private float totale;
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
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
}
