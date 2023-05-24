package entites;

import java.util.Date;

public class Ordonnance extends Malade {
	
	
	
   
	
	private int   NumeroOrdonnance;
    private int NumeroMalade ;
    private int idDoc;
    private Date DateVisite;
    private String  descri;
    
     public Ordonnance( String nomP, String prenomP,  int numeroOrdonnance, int numeroMalade2, int idDoc, Date dateVisite, String descri) {
		super( nomP, prenomP);
		NumeroOrdonnance = numeroOrdonnance;
		NumeroMalade = numeroMalade2;
		this.idDoc = idDoc;
		DateVisite = dateVisite;
		this.descri = descri;
	}

	public int getNumeroOrdonnance() {
		return NumeroOrdonnance;
	}

	public void setNumeroOrdonnance(int numeroOrdonnance) {
		NumeroOrdonnance = numeroOrdonnance;
	}

	public int getNumeroMalade() {
		return NumeroMalade;
	}

	public void setNumeroMalade(int numeroMalade) {
		NumeroMalade = numeroMalade;
	}

	public int getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}

	public Date getDateVisite() {
		return DateVisite;
	}

	public void setDateVisite(Date dateVisite) {
		DateVisite = dateVisite;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	@Override
	public String toString() {
		return "Ordonnance [NumeroOrdonnance=" + NumeroOrdonnance + ", NumeroMalade=" + NumeroMalade + ", idDoc="
				+ idDoc + ", DateVisite=" + DateVisite + ", descri=" + descri + ", getNumeroOrdonnance()="
				+ getNumeroOrdonnance() + ", getNumeroMalade()=" + getNumeroMalade() + ", getIdDoc()=" + getIdDoc()
				+ ", getDateVisite()=" + getDateVisite() + ", getDescri()=" + getDescri() + "]";
	}
     
	
    
	
    
}
