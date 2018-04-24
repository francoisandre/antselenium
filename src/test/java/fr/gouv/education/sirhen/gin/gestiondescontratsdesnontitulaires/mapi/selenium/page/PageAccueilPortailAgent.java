package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAccueilPortailAgent {

	@FindBy(id = "menuSituationPersonnelle")
	WebElement lienMenuSituationPersonnelle;

	@Drone
	WebDriver driver;

	@Page
	PageSituationPersonnelle pageSituationPersonnelle;

	public PageSituationPersonnelle naviguePageSituationPersonnelle() {
		Graphene.guardHttp(lienMenuSituationPersonnelle).click();
		return pageSituationPersonnelle;
	}

}
