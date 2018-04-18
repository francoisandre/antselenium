package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageRechercherContrats extends AbstractPage {
	
	@Page
	private PageAjouterContrat pageAjouterContrat;
	
	@FindBy(css="#smenuAjouter a")
	private WebElement lienMenuAjouter;
	
	public PageAjouterContrat ajouterContrat() {
		Graphene.guardAjax(lienMenuAjouter).click();
		return pageAjouterContrat;
	}
	
}
