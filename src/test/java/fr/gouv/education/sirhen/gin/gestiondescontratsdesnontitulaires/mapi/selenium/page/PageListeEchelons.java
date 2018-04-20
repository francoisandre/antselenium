package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import java.util.List;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.GraphicalTestUtil;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SeleniumUtils;

public class PageListeEchelons extends AbstractPage {
	
	@FindBy(css = ".btAjouter")
	private WebElement boutonAjouterEchelon;
	
	@FindBy(id = "formSaisie:corpsSelected")
	private Select listeSelectionCorps;
	
	@FindBy(id = "formSaisie:gradeSelected")
	private Select listeSelectionGrade;
	
	@FindBy(id = "formSaisie:debutdouvertureInputDate")
	private WebElement dateEntreeEchelon;
	
	@FindBy(id = "formSaisie:EchelonSelected")
	private Select listeSelectionEchelon;
	
	@FindBy(id = "formSaisie:modeSelected")
	private Select listeSelectionMode;
	
	@FindBy(css = "#formSaisie .btValider")
	private WebElement boutonValider;
	
	@FindBy(id = "formSaisie:debutdouverture8InputDate")
	private WebElement dateSortieEchelon;
	
	
	public void ajouterEchelon() {
		Graphene.guardHttp(boutonAjouterEchelon).click();
	}
	
	public void selectionnePremierCorps() {
		//On sélectionne la deuxième option, la première étant non significative
		SeleniumUtils.selectOption(listeSelectionCorps, 1);
		GraphicalTestUtil.attente(2);
	}
	
	public void selectionnePremierGrade() {
		//On sélectionne la deuxième option, la première étant non significative		
		SeleniumUtils.selectOption(listeSelectionGrade, 1);
		GraphicalTestUtil.attente(2);
	}
	
	public void selectionnePremierEchelon() {
		//On sélectionne la deuxième option, la première étant non significative		
		SeleniumUtils.selectOption(listeSelectionEchelon, 1);
		GraphicalTestUtil.attente(2);
	}
	
	public void selectionnePremierModeAcces() {
		//On sélectionne la deuxième option, la première étant non significative
		SeleniumUtils.selectOption(listeSelectionMode, 1);
	}

	public void setDateEntreeEchelon(String date) {
		dateEntreeEchelon.sendKeys(date);
		dateSortieEchelon.click();
		GraphicalTestUtil.attente(2);
	}
	
	public void valider() {
		Graphene.guardHttp(boutonValider).click();
		retournePortailGestionnaire();
	}
	
	
	
	
}
