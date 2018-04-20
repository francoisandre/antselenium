package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumUtils {

	private SeleniumUtils() {
	}

	public static boolean isVisible(final WebElement element) {
		if (element == null) {
			return false;
		}
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}


	public static void setAttribute(WebDriver driver, WebElement element, String attName, String attValue) {
		if (driver instanceof RemoteWebDriver) {
			((RemoteWebDriver) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", 
					element, attName, attValue);
		}
	}
	
	public static void selectOption(Select select, int optionIndex) {
		List<WebElement> options = select.getOptions();
		WebElement option = options.get(optionIndex);
		select.selectByValue(option.getAttribute("value"));
	}
}
