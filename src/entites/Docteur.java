package entites;

import java.sql.Date;

public class Docteur extends Employee{

private String specialite;



public Docteur(int numEm, String nom, String prenom, Date dateNaissance, String adr, String tel, String specialite ) {
	super(numEm, nom, prenom, dateNaissance,adr, tel);
	
	
	this.specialite = specialite ; 
}
public int getNumEm() {
	return numEm;
}
public void setNumEm(int numEm) {
	this.numEm = numEm;
}
public String getSpecialite() {
	return specialite;
}
public void setSpecialite(String specialite) {
	this.specialite = specialite;
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
public Date getDateNaissance() {
	return dateNaissance;
}
public void setDateNaissance(Date dateNaissance) {
	this.dateNaissance = dateNaissance;
}
@Override
public String toString() {
	return "Docteur [specialite=" + specialite + ", getNumEm()=" + getNumEm() + ", getSpecialite()=" + getSpecialite()
			+ ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getAdr()=" + getAdr() + ", getTel()="
			+ getTel() + ", getDateNaissance()=" + getDateNaissance() + "]";
}






}
