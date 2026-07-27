package model;

public class TipoProdottoBean {
	private int idTipoProdotto;
	private String nomeTipo;
	
	//empty constructor
	public TipoProdottoBean() {
		
	}
	
	//getters
	public int getIdTipoProdotto() {
		return idTipoProdotto;
	}
	public String getNomeTipo() {
		return nomeTipo;
	}
	
	//setters
	public void setIdTipoProdotto(int idTipoProdotto) {
		this.idTipoProdotto = idTipoProdotto;
	}
	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
}
