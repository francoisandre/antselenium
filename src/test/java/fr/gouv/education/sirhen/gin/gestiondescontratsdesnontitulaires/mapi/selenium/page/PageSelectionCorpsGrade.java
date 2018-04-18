package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.GraphicalTestUtil;
import fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils.SeleniumUtils;

public class PageSelectionCorpsGrade extends AbstractPage{
	
	public static final String PERSONNEL_PREMIER_DEGRE = "1 - PERSONNELS DU 1ER DEGRE";
	public static final String PERSONNEL_ATOSS = "3 - PERSONNELS A.T.O.S.S.";
	public static final String CORPS_F00616 = "F00616 - MEN-PROF DES ECOLES ENS PRIVE EDUC NAT";
	public static final String CONCOURS_EXTERNE= "AC101 - Concours externe";
	public static final String SANS_SPECIALITE = "R0000 - SPE: sans spécialité";
	public static final String RECRUTEMENT_DANS_CORPS = "AG01 - Recrutement dans corps";
	public static final String GRADE_F01417 = "F01417 - Professeur des écoles enseignement privé classe normale";

	@FindBy(css = "input[name$='enregistrementForm:dateAccesGradeInputDate']")
	private WebElement champDateEntree;
	
	@FindBy(id = "enregistrementForm:typePersonnelInput")
	private WebElement champTypePersonnel;
	
	@FindBy(id = "enregistrementForm:selectCorpsInput")
	private WebElement champCorps;
	
	@FindBy(id = "enregistrementForm:selectModeInput")
	private WebElement champModeAccesCorps;
	
	@FindBy(id = "enregistrementForm:selectSpeInput")
	private WebElement champSpecialiteRecrutement;
	
	@FindBy(id = "enregistrementForm:sessionConcours")
	private WebElement champSession;
	
	@FindBy(id = "enregistrementForm:classement")
	private WebElement champClassement;
	
	@FindBy(id = "enregistrementForm:modeAccesGradeInput")
	private WebElement champModeAccesGrade;
	
	@FindBy(id = "enregistrementForm:selectGradeInput")
	private WebElement champGrade;
	
	@FindBy(css = "a[title='valider']")
	private WebElement boutonValider;
	
	@Page
	PageGenerationArreteRecrutementAgentTitulaire pageGenerationArreteRecrutementAgentTitulaire;
	
	@Drone
	WebDriver driver;
	
	public void setDateEntree(String date) {
		SeleniumUtils.setAttribute(driver, champDateEntree, "value", date);
	}
	
	public void setTypePersonnel(String typePersonnel) {
		champTypePersonnel.click();
		WebElement findElement = driver.findElement(By.xpath("//span[text() = '"+typePersonnel+"']"));
		findElement.click();
		GraphicalTestUtil.attente(3);
	}
	
	public void setCorps (String corps) {
		//La liste des items est grande ainsi l'élément voulu n'est pas forcément visible. Afin de s'ssurer de sa présence, on tape sa valeur.
		champCorps.sendKeys(debut(corps));
		champCorps.click();
		WebElement findElement = driver.findElement(By.xpath("//span[text() = '"+corps+"']"));
		findElement.click();
		GraphicalTestUtil.attente(3);
	}
	
	public void setModeAccesCorps(String modeAccesCorps) {
		champModeAccesCorps.click();
		WebElement findElement = driver.findElement(By.xpath("//span[text() = '"+modeAccesCorps+"']"));
		findElement.click();
	}

	public void setSpecialiteRecrutement(String specialite) {
		//La liste des items est grande ainsi l'élément voulu n'est pas forcément visible. Afin de s'ssurer de sa présence, on tape sa valeur.
		champSpecialiteRecrutement.sendKeys(debut(specialite));
		champSpecialiteRecrutement.click();
		WebElement findElement = driver.findElement(By.xpath("//span[text() = '"+specialite+"']"));
		findElement.click();
	}
	
	private CharSequence debut(String valeur) {
		return valeur.substring(0, 4);
	}

	public void setClassement(String classement) {
		champClassement.sendKeys(classement);
	}
	
	public void setSession(String session) {
		champSession.sendKeys(session);
	}
	
	public void setModeAccesGrade(String modeAcces) {
		//La liste des items est grande ainsi l'élément voulu n'est pas forcément visible. Afin de s'ssurer de sa présence, on tape sa valeur.
		champModeAccesGrade.sendKeys(debut(modeAcces));
		champModeAccesGrade.click();
		WebElement findElement = driver.findElement(By.xpath("//span[text() = '"+modeAcces+"']"));
		findElement.click();
	}
	
	public void setGrade(String grade) {
		champGrade.click();
		WebElement findElement = driver.findElement(By.xpath("//span[text() = '"+grade+"']"));
		findElement.click();
	}
	
	public PageGenerationArreteRecrutementAgentTitulaire valider() {
		Graphene.guardHttp(boutonValider).click();
		return pageGenerationArreteRecrutementAgentTitulaire;
	}
	
	
	

}
