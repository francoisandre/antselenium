package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SeleniumUtils;

public class AbstractPage {

	@FindBy(css = ".footer")
	private WebElement piedDePage;
	
	public boolean estChargee() {
		return SeleniumUtils.isVisible(piedDePage);
	}
	
	@FindBy(css = "#cheminNav a")
	private WebElement boutonRetourPortailGestionnaire;
	
	public void retournePortailGestionnaire() {
		Graphene.guardHttp(boutonRetourPortailGestionnaire).click();
	}

}
