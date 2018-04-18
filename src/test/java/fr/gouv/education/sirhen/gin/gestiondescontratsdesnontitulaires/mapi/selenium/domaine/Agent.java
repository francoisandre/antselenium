package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.domaine;

public class Agent {
	
	private String nom;
	private String prenom;
	private String dateNaissance;
	
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
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
	public String getAnneeNaissance() {
		return dateNaissance.split("/")[2];
	}

}
