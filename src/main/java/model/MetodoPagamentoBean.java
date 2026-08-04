package model;

import java.sql.Date;

public class MetodoPagamentoBean {
	private int idMetodoPagamento;
	private String numeroCarta;
	private String intestatario;
	private Date dataScadenza;
	private String circuito;
	private int idUtente;
	
	//empty constructor
	public MetodoPagamentoBean() {
		
	}
	
	//getters
	public int getIdMetodoPagamento() {
		return idMetodoPagamento;
	}
	public String getNumeroCarta() {
		return numeroCarta;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public String getCircuito() {
		return circuito;
	}
	public int getIdUtente() {
		return idUtente;
	}
	
	//setters
	public void setIdMetodoPagamento(int idMetodoPagamento) {
		this.idMetodoPagamento = idMetodoPagamento;
	}
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
}
