package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import java.util.List;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PageSelectionUnite extends AbstractPage {

	@FindBy(id = "SelectionnerUnite:academieSelectedParDefaut")
	private Select listeAcademies;

	@FindBy(id = "SelectionnerUnite:departementSelected")
	private Select listeDepartements;

	@FindBy(id = "SelectionnerUnite:communeSelected")
	private Select listeCommunes;

	@FindBy(id = "SelectionnerUnite:typeUSSelected")
	private Select listeTypesEtablissement;

	@FindBy(id = "SelectionnerUnite:secteurSelected")
	private Select listeSecteur;

	@FindBy(css = ".btRecherche2")
	private WebElement boutonRecherche;

	public void selectionneAcademieParNom(final String nomAcademie) {
		List < WebElement > options = listeAcademies.getOptions();
		for (WebElement webElement : options) {
			String aux = webElement.getText().toLowerCase();
			if (aux.contains(nomAcademie.toLowerCase())) {
				Graphene.guardAjax(listeAcademies).selectByValue(webElement.getAttribute("value"));
				break;
			}
		}
	}

	public void selectionneCategorieEtablissementParCode(final String code) {
		listeTypesEtablissement.selectByValue(code);

	}

	public void selectionneSecteur(final String secteur) {
		List < WebElement > options = listeSecteur.getOptions();
		for (WebElement webElement : options) {
			String aux = webElement.getText().toLowerCase();
			if (aux.contains(secteur.toLowerCase())) {
				listeSecteur.selectByValue(webElement.getAttribute("value"));
				break;
			}
		}

	}

	public void selectionneDepartement(final String nomDepartement) {
		List < WebElement > options = listeDepartements.getOptions();
		for (WebElement webElement : options) {
			String aux = webElement.getText().toLowerCase();
			if (aux.contains(nomDepartement.toLowerCase())) {
				Graphene.guardAjax(listeDepartements).selectByValue(webElement.getAttribute("value"));
				break;
			}
		}
	}

	public void selectionCommuneParCode(final String codeCommune) {
		Graphene.guardAjax(listeCommunes).selectByValue(codeCommune);
	}

	public void lancerRecherche() {
		Graphene.guardHttp(boutonRecherche).click();
	}

}
