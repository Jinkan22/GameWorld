package model;

import java.sql.Date;

public class OffertaBean {
	private int idOfferta;
	private int percentualeSconto;
	private Date dataInizio;
	private Date dataFine;
	private int idProdotto;
	
	//costruttore vuoto
	public OffertaBean() {
		
	}
	
	//getters
	public int getIdOfferta() {
		return idOfferta;
	}
	public int getPercentualeSconto() {
		return percentualeSconto;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	
	//setters
	public void setIdOfferta(int idOfferta) {
		this.idOfferta = idOfferta;
	}
	public void setPercentualeSconto(int percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
}
