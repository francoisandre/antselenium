package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SeleniumUtils;

public class PageIdentificationAgentARecruter extends AbstractPage {
	
	public static final String MONSIEUR = "M. - 1";
	public static final String MADAME = "Mme - 2";
	
	@FindBy(xpath = "//span[text() = 'M. - 1']")
	private WebElement optionMonsieur;
	
	@FindBy(xpath = "//span[text() = 'Mme - 2']")
	private WebElement optionMadame;

	@FindBy(id = "attributsform3:nomUsage")
	private WebElement nomUsage;
	
	@FindBy(id = "attributsform3:prenom")
	private WebElement prenom;
	
	@FindBy(id = "attributsform3:nomPatronymique")
	private WebElement nomPatronymique;
	
	@FindBy(id = "attributsform3:richCalendar6InputDate")
	private WebElement dateNaissance;
	
	@FindBy(id = "attributsform3:civiliteInput")
	private WebElement civilite;
	
	@FindBy(css = "a[title='Valider']")
	private WebElement boutonValider;
	
	@FindBy(css ="a.bouttonCreation")
	private WebElement boutonCreationDossier;
	
	@FindBy(css =".rich-messages-label") 
	private List<WebElement> messagesErreur;
	
	@FindBy(css="#formchoixorg a.btValiderIden")
	private WebElement boutonConfirmerCreationDossier;
	
	@Page
	PageSelectionCorpsGrade pageSelectionCorpsGrade;
	
	public void setNomUsage(String nomUsage) {
		this.nomUsage.sendKeys(nomUsage);
	}
	
	public void setNomPatronymique(String nomPatronymique) {
		this.nomPatronymique.sendKeys(nomPatronymique);
	}
	
	public void setPrenom(String prenom) {
		this.prenom.sendKeys(prenom);
	}
	
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance.sendKeys(dateNaissance);
	}

	public void setCivilite(String civilite) {
		this.civilite.click();
		if (civilite.equalsIgnoreCase(MONSIEUR)) {
			optionMonsieur.click();
		}
		else if (civilite.equalsIgnoreCase(MADAME)) {
			optionMadame.click();
		}
	}
	
	public void valider() {
		Graphene.guardHttp(boutonValider).click();
	}

	public List<String> getMessagesErreur() {
		ArrayList<String> resultat = new ArrayList<>();
		if (messagesErreur != null) {
			for (WebElement element : messagesErreur) {
				resultat.add(element.getText());
			}
		}
		return resultat;
	}
	
	public PageSelectionCorpsGrade creerDossier() {
		Graphene.guardHttp(boutonCreationDossier).click();
		System.out.println("Visible: "+SeleniumUtils.isVisible(boutonConfirmerCreationDossier));
		System.out.println(boutonConfirmerCreationDossier.getText());
		Graphene.guardAjax(boutonConfirmerCreationDossier).click();
		System.out.println("icic");
		return pageSelectionCorpsGrade;
	}
	

}
