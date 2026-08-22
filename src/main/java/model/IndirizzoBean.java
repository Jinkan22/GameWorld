package model;

public class IndirizzoBean {
	private int idIndirizzo;
	private String via;
	private String citta;
	private String cap;
	private String provincia;
	private String paese;
	private int idUtente;
	
	//costruttore vuoto
	public IndirizzoBean() {
		
	}
	
	//getters
	public int getIdIndirizzo() {
		return idIndirizzo;
	}
	public String getVia() {
		return via;
	}
	public String getCitta() {
		return citta;
	}
	public String getCap() {
		return cap;
	}
	public String getProvincia() {
		return provincia;
	}
	public String getPaese() {
		return paese;
	}
	public int getIdUtente() {
		return idUtente;
	}
	
	//setters
	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
}
