package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("/mapi-pac-portail-agent/ihmr/accueil")
public class PageAuthentification {

	@FindBy(id = "user")
	private WebElement champIdentifiant;

	@FindBy(id = "password")
	private WebElement champMotDePasse;

	@FindBy(css = "input#button")
	private WebElement boutonValider;

	@Page
	PageAccueilPortailAgent pageAccueilPortailAgent;

	public PageAuthentification() {
	}

	public final void setIdentifiant(final String identifiant) {
		champIdentifiant.sendKeys(identifiant);
	}

	public final void setChampMotDePasse(final String motDePasse) {
		champMotDePasse.sendKeys(motDePasse);
	}

	public final PageAccueilPortailAgent connexion() {
		Graphene.guardHttp(boutonValider).click();
		return pageAccueilPortailAgent;
	}
}
