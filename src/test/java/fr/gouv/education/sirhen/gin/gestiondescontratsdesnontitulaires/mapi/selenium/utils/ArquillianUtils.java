package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils;

import java.io.InputStream;

import org.jboss.arquillian.config.descriptor.api.ArquillianDescriptor;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;

public class ArquillianUtils {

	public static InputStream loadArquillianConfiguration(final String fileName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResourceAsStream(fileName);
	}

	public static ArquillianDescriptor createArquillianDescriptor(final String fileName) {
		return Descriptors.importAs(ArquillianDescriptor.class).fromStream(loadArquillianConfiguration(fileName));
	}

}
