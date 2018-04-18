package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.scenario;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageAccueilPortailGestionnaire;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageAuthentification;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageSelectionUnite;

@RunWith(Arquillian.class)
public class ScenarioCreationSupport {

	@Drone
	WebDriver driver;

	@Ignore
	@Test
	public void testRechercheParIdentifiant(@InitialPage final PageAuthentification pageAuthentification) {
		Assert.assertTrue(pageAuthentification.estChargee());
		pageAuthentification.setIdentifiant("cboquet");
		pageAuthentification.setChampMotDePasse("logica");
		PageAccueilPortailGestionnaire pageAccueilPortailGestionnaire = pageAuthentification.connexion();
		Assert.assertTrue(pageAccueilPortailGestionnaire.estChargee());
		PageSelectionUnite pageSelectionUnite = pageAccueilPortailGestionnaire.implementerSupportsAffectation();
		Assert.assertTrue(pageSelectionUnite.estChargee());
		pageSelectionUnite.selectionneAcademieParNom("toulouse");
		pageSelectionUnite.selectionneCategorieEtablissementParCode("1ORD");
		pageSelectionUnite.selectionneDepartement("haute-garonne");
		pageSelectionUnite.selectionneSecteur("prive");
		pageSelectionUnite.selectionCommuneParCode("31541"); // SEILH
		pageSelectionUnite.lancerRecherche();
		System.out.println("Pause....");
	}
}
