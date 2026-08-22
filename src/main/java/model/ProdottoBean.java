package model;

import java.math.BigDecimal;
import java.sql.Date;

public class ProdottoBean {
	private int idProdotto;
	private String nome;
	private String descrizione;
	private BigDecimal prezzo;
	private String immagine;
	private Date dataUscita;
	private String sviluppatore;
	
	//costruttore vuoto
	public ProdottoBean() {
		
	}
	
	//getters
	public int getIdProdotto() {
		return idProdotto;
	}
	public String getNome() {
		return nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public String getImmagine() {
		return immagine;
	}
	public Date getDataUscita() {
		return dataUscita;
	}
	public String getSviluppatore() {
		return sviluppatore;
	}
	
	//setters
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}
	public void setSviluppatore(String sviluppatore) {
		this.sviluppatore = sviluppatore;
	}
}
