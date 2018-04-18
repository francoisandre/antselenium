package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SeleniumUtils;

public class PageGenerationArreteRecrutementAgentTitulaire extends AbstractPage {

	@FindBy(css = "#boutons a")
	private WebElement boutonGenererArrete;
	
	public boolean isBoutonGenererArreteVisible() {
		return SeleniumUtils.isVisible(boutonGenererArrete);
	}
	
}
