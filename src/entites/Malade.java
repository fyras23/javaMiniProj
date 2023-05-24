package entites;


public class Malade {
    public Malade(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	private int    NumeroMalade;
    private String nom;
    private String prenom;
    private String adresse;
    private String NumeroTelephone;
    private String ServiceDemande;
    
    
	
	
	public int getNumeroMalade() {
		return NumeroMalade;
	}
	public void setNumeroMalade(int numeroMalade) {
		NumeroMalade = numeroMalade;
	}
	public String getNumeroTelephone() {
		return NumeroTelephone;
	}
	public void setNumeroTelephone(String numeroTelephone) {
		NumeroTelephone = numeroTelephone;
	}
	public String getServiceDemande() {
		return ServiceDemande;
	}
	public void setServiceDemande(String serviceDemande) {
		ServiceDemande = serviceDemande;
	}
	public Malade(int numeroMalade, String nom, String prenom, String adresse, String numeroTelephone,
			String serviceDemande) {
		super();
		NumeroMalade = numeroMalade;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		NumeroTelephone = numeroTelephone;
		ServiceDemande = serviceDemande;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	

}
