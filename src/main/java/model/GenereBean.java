package model;

public class GenereBean {
	private int idGenere;
	private String nomeGenere;
	
	//empty constructor
	public GenereBean() {
		
	}
	
	//getters
	public int getIdGenere() {
		return idGenere;
	}
	public String getNomeGenere() {
		return nomeGenere;
	}
	
	//setters
	public void setIdGenere(int idGenere) {
		this.idGenere = idGenere;
	}
	public void setNomeGenere(String nomeGenere) {
		this.nomeGenere = nomeGenere;
	}
}
