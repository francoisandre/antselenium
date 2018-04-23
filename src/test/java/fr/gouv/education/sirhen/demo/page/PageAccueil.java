package fr.gouv.education.sirhen.demo.page;

import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SeleniumUtils;

@Location("https://www.eyrolles.com/")
public class PageAccueil  {

	@Page
	PageResultatRecherche pageResultatRecherche;

	@FindBy(id = "champRecherche")
	private WebElement champRecherche;
	
	@FindBy(id = "submit-search")
	private WebElement boutonRecherche;
	
	@FindBy(id = "pieddepage")
	private WebElement pieddepage;

	public void setRecherche(String motClef) {
		champRecherche.clear();
		champRecherche.sendKeys(motClef);
	}
	
	public PageResultatRecherche lancerRecherche() {
		boutonRecherche.click();
		return pageResultatRecherche;
	}

	public boolean estChargee() {
		return SeleniumUtils.isVisible(pieddepage);
	}
	
	
}
