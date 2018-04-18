package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.scenario;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.domaine.Agent;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageAccueilPortailGestionnaire;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageAjouterContrat;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageAuthentification;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageGenerationArreteRecrutementAgentTitulaire;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageIdentificationAgentARecruter;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageRechercheAgent;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageRechercherContrats;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page.PageSelectionCorpsGrade;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.Graphical;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SpecializedScreenRecorder;

@RunWith(Arquillian.class)
@Category(Graphical.class)
public class ScenarioInitiationContratAgentNonTitulaire {

	private static final String DATE_NAISSANCE = "01/01/1970";
	@Drone
	WebDriver driver;
	private Agent agent;
	
	private static ScreenRecorder screenRecorder;

	@Test
	public void testInitiationContratAgentNonTitulaire(@InitialPage final PageAuthentification pageAuthentification) {
		
//		if (!GraphicalTestUtil.isGraphicalTestContext()) {
//			return;
//		}

		Assert.assertTrue(pageAuthentification.estChargee());
		pageAuthentification.setIdentifiant("cboquet");
		pageAuthentification.setChampMotDePasse("logica");
		PageAccueilPortailGestionnaire pageAccueilPortailGestionnaire = pageAuthentification.connexion();
		Assert.assertTrue(pageAccueilPortailGestionnaire.estChargee());
		
		if (!existeUniqueAgent(agent, pageAccueilPortailGestionnaire)) {
			creerAgent(agent, pageAccueilPortailGestionnaire);
		}
		selectionnerAgent(agent, pageAccueilPortailGestionnaire);
		
		gererDossierAgent(agent, pageAccueilPortailGestionnaire);
		
		System.out.println("Pause....");
	}
	
	
	private void selectionnerAgent(Agent agent, PageAccueilPortailGestionnaire pageAccueilPortailGestionnaire) {
		PageRechercheAgent pageRechercheAgent = pageAccueilPortailGestionnaire.rechercherAgent();
		pageRechercheAgent.setNomUsage(agent.getNom());
		pageRechercheAgent.setPrenom(agent.getPrenom());
		pageRechercheAgent.setAnneeNaissance(agent.getAnneeNaissance());
		pageRechercheAgent.valider();
		Assert.assertEquals("Il ne doit y avoir qu'un seul résultat", 1, pageRechercheAgent.getNombreResultats());
		pageRechercheAgent.selectionneUniqueAgent();
	}

	private boolean existeUniqueAgent(Agent agent, PageAccueilPortailGestionnaire pageAccueilPortailGestionnaire) {
		PageRechercheAgent pageRechercheAgent = pageAccueilPortailGestionnaire.rechercherAgent();
		pageRechercheAgent.setNomUsage(agent.getNom());
		pageRechercheAgent.setPrenom(agent.getPrenom());
		pageRechercheAgent.setAnneeNaissance(agent.getAnneeNaissance());
		pageRechercheAgent.valider();
		int nombreResultats = pageRechercheAgent.getNombreResultats();
		pageRechercheAgent.retournePortailGestionnaire();
		if (nombreResultats == 1) {
			return true;
		} else {
			return false;
		}
	}

	private void gererDossierAgent(Agent agent, PageAccueilPortailGestionnaire pageAccueilPortailGestionnaire) {
		PageRechercherContrats pageRechercherContrats = pageAccueilPortailGestionnaire.gererContratsAgentPrive();
		PageAjouterContrat pageAjouterContrat = pageRechercherContrats.ajouterContrat();
	}

	private void creerAgent(Agent agent, PageAccueilPortailGestionnaire pageAccueilPortailGestionnaire) {
		PageIdentificationAgentARecruter pageIdentificationAgentARecruter = pageAccueilPortailGestionnaire.recruterAgentTitulaire();
		Assert.assertTrue(pageIdentificationAgentARecruter.estChargee());
		pageIdentificationAgentARecruter.setCivilite(PageIdentificationAgentARecruter.MONSIEUR);
		pageIdentificationAgentARecruter.setNomUsage(agent.getNom());
		pageIdentificationAgentARecruter.setPrenom(agent.getPrenom());
		pageIdentificationAgentARecruter.setNomPatronymique(agent.getNom());
		pageIdentificationAgentARecruter.setDateNaissance(agent.getDateNaissance());
		pageIdentificationAgentARecruter.valider();
		List<String> messages = pageIdentificationAgentARecruter.getMessagesErreur();
		Assert.assertEquals("La liste des messages d'erreurs doit contenir 1 unique élément", 1, messages.size());
		Assert.assertEquals("Le message doit correspondre", "Aucune personne ayant le même nom n'a été trouvée, confirmez la création de l'agent.", messages.get(0));
		PageSelectionCorpsGrade pageSelectionCorpsGrade = pageIdentificationAgentARecruter.creerDossier();
		Assert.assertTrue(pageSelectionCorpsGrade.estChargee());
		pageSelectionCorpsGrade.setDateEntree("01/09/2017");
		pageSelectionCorpsGrade.setTypePersonnel(PageSelectionCorpsGrade.PERSONNEL_PREMIER_DEGRE);
		pageSelectionCorpsGrade.setCorps(PageSelectionCorpsGrade.CORPS_F00616);
		pageSelectionCorpsGrade.setModeAccesCorps(PageSelectionCorpsGrade.CONCOURS_EXTERNE);
		pageSelectionCorpsGrade.setSpecialiteRecrutement(PageSelectionCorpsGrade.SANS_SPECIALITE);
		pageSelectionCorpsGrade.setSession("2017");
		pageSelectionCorpsGrade.setClassement("10");
		pageSelectionCorpsGrade.setModeAccesGrade(PageSelectionCorpsGrade.RECRUTEMENT_DANS_CORPS);
		pageSelectionCorpsGrade.setGrade(PageSelectionCorpsGrade.GRADE_F01417);
		PageGenerationArreteRecrutementAgentTitulaire pageGenerationArreteRecrutementAgentTitulaire = pageSelectionCorpsGrade.valider();
		Assert.assertTrue(pageGenerationArreteRecrutementAgentTitulaire.estChargee());
		Assert.assertTrue(pageGenerationArreteRecrutementAgentTitulaire.isBoutonGenererArreteVisible());
		pageGenerationArreteRecrutementAgentTitulaire.retournePortailGestionnaire();
		
	}

	
	public void rechercherAgent() {
		
	}
	
	@BeforeClass
	public static void initialisationClasse() throws Exception {
		 File file = new File("C:\\Users\\frup72441\\Videos");
         
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         int width = screenSize.width;
         int height = screenSize.height;
                        
         Rectangle captureSize = new Rectangle(0,0, width, height);
                        
       GraphicsConfiguration gc = GraphicsEnvironment
          .getLocalGraphicsEnvironment()
          .getDefaultScreenDevice()
          .getDefaultConfiguration();

      screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
          new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
          new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
               CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
               DepthKey, 24, FrameRateKey, Rational.valueOf(15),
               QualityKey, 1.0f,
               KeyFrameIntervalKey, 15 * 60),
          new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
               FrameRateKey, Rational.valueOf(30)),
          null, file, "MyVideo");
     //screenRecorder.start();
	}
	
	@AfterClass
	public static void conclusionClasse() throws Exception{
		//screenRecorder.stop();
	}
	
	@Before
	public void initialisation() {
		//agent = createStaticAgent();
		agent = createRandomAgent();
	}

	private Agent createRandomAgent() {
		String suffixe = RandomStringUtils.randomAlphabetic(10);
		Agent resultat = new Agent();
		resultat.setNom("Dupond"+suffixe);
		resultat.setPrenom("Martin");
		resultat.setDateNaissance(DATE_NAISSANCE);
		return resultat;
	}
	
	private Agent createStaticAgent() {
		Agent resultat = new Agent();
		resultat.setNom("DUPONDATMLTOKSCR");
		resultat.setDateNaissance(DATE_NAISSANCE);
		resultat.setPrenom("Martin");
		return resultat;
	}
	

}
