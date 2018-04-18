package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils;

import org.apache.commons.lang.StringUtils;

public class GraphicalTestUtil {

	private static final String GRAPHICAL = "graphical";
	private static final String CONTEXT = "context";

	public static boolean isGraphicalTestContext() {
		String context = StringUtils.trimToEmpty(System.getProperty(CONTEXT));
		if (context.toLowerCase().equals(GRAPHICAL)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void attente(final int dureeEnSeconde) {
		try {
			Thread.sleep(1000 * dureeEnSeconde);
		} catch (InterruptedException e) {

		}
	}
	
}
