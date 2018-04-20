package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SeleniumUtils;

public class PageCreationUCM extends AbstractPage {
	
	public final static String A02_1D_PUB_ENS_ELEMENTAIRE_YC_EDUC_PRIORIT = "A02";
	

	@FindBy(id="attributsform:LigneBudgetaire")
	private Select listeSelectionLigneBudgetaire;
	
	@FindBy(id="attributsform:ActionLOLF")
	private Select listeSelectionActionLOLF;
	
	@FindBy(id="attributsform:dateDebutInputDate")
	private WebElement champDateDebut;
	
	@FindBy(css ="a.btValider")
	private WebElement boutonValider;
	
	public void selectionnePremiereLigneBudgetaire() {
		SeleniumUtils.selectOption(listeSelectionLigneBudgetaire, 1);
	}
	
	public void selectionneActionLolf(String value) {
		listeSelectionActionLOLF.selectByValue(value);
	}
	
	public void setDateDebut(String date) {
		champDateDebut.clear();
		champDateDebut.sendKeys(date);
	}
	
	public void valider() {
		Graphene.guardHttp(boutonValider).click();
	}
	
	
}
