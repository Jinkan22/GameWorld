package model;

public class OrdineViewBean {
	private OrdineBean ordine;
    private UtenteBean utente;

    //empty constructor
    public OrdineViewBean() {}

    //getters
    public OrdineBean getOrdine() {
    	return ordine;
    }
    public UtenteBean getUtente() {
    	return utente;
    }

    //setters
    public void setOrdine(OrdineBean ordine) {
    	this.ordine = ordine;
    }
    public void setUtente(UtenteBean utente) {
    	this.utente = utente;
    }
}