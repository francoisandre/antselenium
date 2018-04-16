package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils;

import org.jboss.arquillian.core.spi.LoadableExtension;
import org.jboss.arquillian.graphene.location.decider.HTTPLocationDecider;
import org.jboss.arquillian.graphene.spi.location.LocationDecider;

public class MyArquillianExtension implements LoadableExtension {

	@Override
	public void register(final ExtensionBuilder builder) {
		builder.override(LocationDecider.class, HTTPLocationDecider.class, HTTPParameterizedLocationDecider.class);
	}

}