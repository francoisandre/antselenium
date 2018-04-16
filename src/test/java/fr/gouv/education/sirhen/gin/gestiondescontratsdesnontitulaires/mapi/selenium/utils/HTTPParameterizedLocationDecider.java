package fr.gouv.education.sirhen.gin.gestiondescontratsdesnontitulaires.mapi.selenium.utils;

import java.net.URI;

import org.jboss.arquillian.config.descriptor.api.ArquillianDescriptor;
import org.jboss.arquillian.config.descriptor.api.ExtensionDef;
import org.jboss.arquillian.core.api.Instance;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.graphene.location.decider.HTTPLocationDecider;
import org.jboss.arquillian.graphene.spi.location.Scheme;
import org.jboss.arquillian.test.spi.context.TestContext;

public class HTTPParameterizedLocationDecider extends HTTPLocationDecider {

	public static String ROOT_CONTEXT = null;

	@Inject
	private Instance < TestContext > testContext;

	@Override
	public Scheme canDecide() {
		return new Scheme.HTTP();
	}

	@Override
	public String decide(final String location) {
		if (ROOT_CONTEXT == null) {
			loadRootContext();
		}
		String aux = location;
		try {
			URI uri = new URI(aux);
			if (!uri.isAbsolute()) {
				aux = ROOT_CONTEXT + aux;
			}
		} catch (Exception e) {

		}
		return super.decide(aux);

	}

	private static void loadRootContext() {
		ArquillianDescriptor loadArquillianConfiguration = ArquillianUtils.createArquillianDescriptor("arquillian.xml");
		ExtensionDef extension = loadArquillianConfiguration.extension("configuration");
		ROOT_CONTEXT = extension.getExtensionProperties().get("contextRoot");
	}

}