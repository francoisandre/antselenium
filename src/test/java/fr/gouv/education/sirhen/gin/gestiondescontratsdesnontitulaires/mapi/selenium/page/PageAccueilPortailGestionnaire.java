package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAccueilPortailGestionnaire extends AbstractPage {

	@Page
	PageRechercheAgent pageRechercheAgent;

	@Page
	PageSelectionUnite pageSelectionUnite;

	@FindBy(css = "a#A1")
	private WebElement lienMenuGestionIndividualisee;

	@FindBy(css = "a#A2")
	private WebElement lienMenuGestionCarriere;

	@FindBy(css = "a#A7")
	private WebElement lienCalibrageMoyens;

	@FindBy(css = "#smenu1 .menunv2 li:nth-child(1) a")
	private WebElement lienConsulterDossierAgent;

	@FindBy(css = "a[href$='mapi-gin-gestion-des-contrats-prive']")
	private WebElement lienGererContratsAgentPrive;

	@FindBy(css = "a[href$='mapi-cdm-mngh-gsm']")
	private WebElement lienImplementerSupportsAffectation;

	private void deplieMenuGestionIndividualisee() {
		lienMenuGestionIndividualisee.click();
		attente(1);
	}

	private void deplieMenuCalibrageMoyens() {
		lienCalibrageMoyens.click();
		attente(1);

	}

	public PageRechercheAgent consulterDossierAgent() {
		deplieMenuGestionIndividualisee();
		Graphene.guardHttp(lienConsulterDossierAgent).click();
		return pageRechercheAgent;
	}

	public PageRechercheAgent gererContratsAgentPrive() {
		deplieMenuGestionIndividualisee();
		Graphene.guardHttp(lienConsulterDossierAgent).click();
		return pageRechercheAgent;
	}

	public PageSelectionUnite implementerSupportsAffectation() {
		deplieMenuCalibrageMoyens();
		Graphene.guardHttp(lienImplementerSupportsAffectation).click();
		attente(2);
		return pageSelectionUnite;

	}

	public void attente(final int dureeEnSeconde) {
		try {
			Thread.sleep(1000 * dureeEnSeconde);
		} catch (InterruptedException e) {

		}
	}
}
