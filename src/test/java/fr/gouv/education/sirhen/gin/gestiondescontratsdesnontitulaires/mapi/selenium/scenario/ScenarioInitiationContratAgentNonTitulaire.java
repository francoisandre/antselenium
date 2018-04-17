package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.scenario;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageAccueilPortailGestionnaire;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageAuthentification;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageIdentificationAgentARecruter;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageSelectionCorpsGrade;

@RunWith(Arquillian.class)
public class ScenarioInitiationContratAgentNonTitulaire {

	@Drone
	WebDriver driver;

	@Test
	public void testInitiationContratAgentNonTitulaire(@InitialPage final PageAuthentification pageAuthentification) {

		String suffixe = RandomStringUtils.randomAlphabetic(10);
		Assert.assertTrue(pageAuthentification.estChargee());
		pageAuthentification.setIdentifiant("cboquet");
		pageAuthentification.setChampMotDePasse("logica");
		PageAccueilPortailGestionnaire pageAccueilPortailGestionnaire = pageAuthentification.connexion();
		Assert.assertTrue(pageAccueilPortailGestionnaire.estChargee());
		PageIdentificationAgentARecruter pageIdentificationAgentARecruter = pageAccueilPortailGestionnaire.recruterAgentTitulaire();
		Assert.assertTrue(pageIdentificationAgentARecruter.estChargee());
		pageIdentificationAgentARecruter.setCivilite(PageIdentificationAgentARecruter.MONSIEUR);
		pageIdentificationAgentARecruter.setNomUsage("Dupond"+suffixe);
		pageIdentificationAgentARecruter.setPrenom("Martin");
		pageIdentificationAgentARecruter.setNomPatronymique("dupond"+suffixe);
		pageIdentificationAgentARecruter.setDateNaissance("01/01/1970");
		pageIdentificationAgentARecruter.valider();
		List<String> messages = pageIdentificationAgentARecruter.getMessagesErreur();
		Assert.assertEquals("La liste des messages d'erreurs doit contenir 1 unique élément", 1, messages.size());
		Assert.assertEquals("Le message doit correspondre", "Aucune personne ayant le même nom n'a été trouvée, confirmez la création de l'agent.", messages.get(0));
		PageSelectionCorpsGrade pageSelectionCorpsGrade = pageIdentificationAgentARecruter.creerDossier();
		Assert.assertTrue(pageSelectionCorpsGrade.estChargee());

		System.out.println("Pause....");
	}

}
