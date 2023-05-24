package entites;

public class MaladeRec extends Malade{

    private Infirmier infirmier;
    private Salle   salle;
    private String numLit;
    private String diagnostic;
    private Docteur docteur;
    private Ordonnance ordonnance;
	public MaladeRec(int idMalade, String nom, String prenom, String adresse, String telephone, int service,
			Infirmier infirmier, Salle salle, String numLit, String diagnostic, Docteur docteur, 
			Ordonnance ordonnance) {
		super(idMalade, nom, prenom, adresse, telephone, service);
		this.infirmier = infirmier;
	
		this.salle = salle;
		this.numLit = numLit;
		this.diagnostic = diagnostic;
		this.docteur = docteur;
		this.ordonnance = ordonnance;
	}
	public Infirmier getInfirmier() {
		return infirmier;
	}
	public void setInfirmier(Infirmier infirmier) {
		this.infirmier = infirmier;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public String getNumLit() {
		return numLit;
	}
	public void setNumLit(String numLit) {
		this.numLit = numLit;
	}
	public String getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}
	public Docteur getDocteur() {
		return docteur;
	}
	public void setDocteur(Docteur docteur) {
		this.docteur = docteur;
	}
	public Ordonnance getOrdonnance() {
		return ordonnance;
	}
	public void setOrdonnance(Ordonnance ordonnance) {
		this.ordonnance = ordonnance;
	}
	@Override
	public String toString() {
		return "Malade [infirmier=" + infirmier + ", salle=" + salle + ", numLit=" + numLit + ", diagnostic="
				+ diagnostic + ", docteur=" + docteur + ", ordonnance=" + ordonnance + "]";
	}

    
    

}
