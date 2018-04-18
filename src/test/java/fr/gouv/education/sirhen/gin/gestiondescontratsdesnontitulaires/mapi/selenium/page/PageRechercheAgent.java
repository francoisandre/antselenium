package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageRechercheAgent extends AbstractPage {
	
	@Drone
	WebDriver driver;
	
	@FindBy(id = "AjouterPersonne:nomDUsage")
	private WebElement champNomUsage;
	
	@FindBy(id = "AjouterPersonne:prenom")
	private WebElement champPrenom;
	
	@FindBy(id = "AjouterPersonne:anneeNaissance")
	private WebElement champAnneeNaissance;
	
	@FindBy(css = "a[title='Lancer la recherche']")
	private WebElement boutonValider;
	
	@FindBy(css = "tr.rich-table-row")
	private List<WebElement> resultatsRecherche;
	
	public void setNomUsage(String nom) {
		champNomUsage.clear();
		champNomUsage.sendKeys(nom);
	}
	
	public void setPrenom(String prenom) {
		champPrenom.clear();
		champPrenom.sendKeys(prenom);
	}
	
	public void setAnneeNaissance(String anneeNaissance) {
		champAnneeNaissance.clear();
		champAnneeNaissance.sendKeys(anneeNaissance);
	}
	
	public void valider() {
		Graphene.guardHttp(boutonValider).click();
	}

	public int getNombreResultats() {
		if (resultatsRecherche == null) {
			return 0;
		}
		else {
			return resultatsRecherche.size();
		}
		
	}

	public void selectionneUniqueAgent() {
		
		//Le bouton de sélection est assez dur à selectionner. On considère que c'est le dernier de la liste suivante
		List<WebElement> elements = driver.findElements(By.cssSelector("tr.rich-table-row a"));
		WebElement webElement = elements.get(elements.size()-1);
		Graphene.guardHttp(webElement).click();
		
	}
	
	

}
