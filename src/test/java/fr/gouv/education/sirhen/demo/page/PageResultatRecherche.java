package fr.gouv.education.sirhen.demo.page;

import org.jboss.arquillian.graphene.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageResultatRecherche  {
	
	@FindBy (css =".nbresultats")
	WebElement libelleResultats;
	
	
	public int getNombreResultats() {
		String[] split = libelleResultats.getText().split(" ");
		return new Integer(split[4]);
	}

	
}
