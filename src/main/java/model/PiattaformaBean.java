package model;

public class PiattaformaBean {
	private int idPiattaforma;
	private String nomePiattaforma;
	
	//empty constructor
	public PiattaformaBean() {
		
	}
	
	//getters
	public int getIdPiattaforma() {
		return idPiattaforma;
	}
	public String getNomePiattaforma() {
		return nomePiattaforma;
	}
	
	//setters
	public void setIdPiattaforma(int idPiattaforma) {
		this.idPiattaforma = idPiattaforma;
	}
	public void setNomePiattaforma(String nomePiattaforma) {
		this.nomePiattaforma = nomePiattaforma;
	}
}
