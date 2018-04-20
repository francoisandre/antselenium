package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import java.util.Iterator;
import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.GraphicalTestUtil;

public class PageSelectionUnite extends AbstractPage {

	public static final String ACADEMIE_TOULOUSE="16";
	public static final String DEPARTEMENT_HAUTE_GARONNE = "031";

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

	@FindBy(css = "a.btAjouter")
	private WebElement boutonCreerUCM;

	@Drone
	WebDriver driver;

	@Page
	PageCreationUCM pageCreationUCM;
	
	@Page 
	PageCreationSupport pageCreationSupport;

	private WebElement ucmSelectionnee;

	public void selectionneAcademieParCode(final String codeAcademie) {
		Graphene.guardAjax(listeAcademies).selectByValue(codeAcademie);
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

	public void selectionneDepartementParCode(final String codeDepartement) {
		Graphene.guardAjax(listeDepartements).selectByValue(codeDepartement);
	}

	public void selectionCommuneParCode(final String codeCommune) {
		Graphene.guardAjax(listeCommunes).selectByValue(codeCommune);
	}

	private void selectionnePremiereUs() {

		WebElement element = driver.findElement(By.cssSelector("tr[id^=formTree]"));
		element.click();
		GraphicalTestUtil.attente(2);

	}

	public void lancerRecherche() {
		ucmSelectionnee = null;
		Graphene.guardHttp(boutonRecherche).click();
	}

	public void creerUcm() {
		selectionnePremiereUs();
		Graphene.guardHttp(boutonCreerUCM).click();
		pageCreationUCM.setDateDebut("26/06/2017");
		pageCreationUCM.selectionnePremiereLigneBudgetaire();
		pageCreationUCM.selectionneActionLolf(PageCreationUCM.A02_1D_PUB_ENS_ELEMENTAIRE_YC_EDUC_PRIORIT);
		pageCreationUCM.valider();
	}

	public boolean premiereUsAUneUcm() {
		List<WebElement> findElements = driver.findElements(By.cssSelector("#formTree .rich-tree-node-handle img.rich-tree-node-handleicon-collapsed"));
		if ((findElements != null) && (findElements.size()>0)) {
			WebElement findElement = findElements.get(0);
			if (findElement.isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	public void deploiePremiereUS() {
		WebElement findElement = driver.findElement(By.cssSelector("#formTree .rich-tree-node-handle img.rich-tree-node-handleicon-collapsed"));
		findElement.click();
	}

	public boolean ucmSelectionneAUnSupport() {
		List<WebElement> findElements = driver.findElements(By.cssSelector("#formTree .rich-tree-node-handle img.rich-tree-node-handleicon-collapsed"));

		for (WebElement webElement : findElements) {
			if (webElement.isDisplayed()) {
				return true;
			}
		}
		return false;
	}
	
	public void deploiePremierUCM() {
		List<WebElement> findElements = driver.findElements(By.cssSelector("#formTree .rich-tree-node-handle img.rich-tree-node-handleicon-collapsed"));

		for (WebElement webElement : findElements) {
			if (webElement.isDisplayed()) {
				webElement.click();
				return;
			}
		}
	}
	
	public void selectionnePremierSupport() {
		List<WebElement> findElements = driver.findElements(By.cssSelector(".rich-tree-node-text"));
		for (WebElement webElement : findElements) {
			String text = webElement.getText();
			if (text.toLowerCase().startsWith("support")) {
				webElement.click();
				return;
			}
		}
	}
	
	public void selectionnePremiereUcm() {
		List<WebElement> findElements = driver.findElements(By.cssSelector(".rich-tree-node-text"));
		for (WebElement webElement : findElements) {
			String text = webElement.getText();
			if (text.toLowerCase().startsWith("ucm")) {
				webElement.click();
				return;
			}
		}
	}

	public PageCreationSupport creerSupport() {
		selectionnePremiereUcm();
		List<WebElement> findElements = driver.findElements(By.cssSelector(".btAjouter"));
		for (WebElement webElement : findElements) {
			if (webElement.getText().toLowerCase().contains("support")) {
				Graphene.guardHttp(webElement).click();
				return pageCreationSupport;
			}
		}
		return null;
	}



}
