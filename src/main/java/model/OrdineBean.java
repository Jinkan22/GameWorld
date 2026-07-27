package model;

import java.sql.Date;

public class OrdineBean {
	private int idOrdine;
	private Date dataOrdine;
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
	public Date getDataOrdine() {
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
	public void setDataOrdine(Date dataOrdine) {
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
