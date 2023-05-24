package entites;

import java.sql.Date;

public class Employee {
    public int    numEm;
    public String nom;
    public String prenom;
    public Date dateNaissance;
    public String adr;
    public String tel;
	public Employee(int numEm, String nom, String prenom, Date dateNaissance, String adr, String tel) {
		super();
		this.numEm = numEm;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adr = adr;
		this.tel = tel;
	}
	public int getNumEm() {
		return numEm;
	}
	public void setNumEm(int numEm) {
		this.numEm = numEm;
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
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
    
    
    
	
}
