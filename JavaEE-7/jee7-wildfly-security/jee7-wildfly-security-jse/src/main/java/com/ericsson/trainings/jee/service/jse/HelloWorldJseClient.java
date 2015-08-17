package com.ericsson.trainings.jee.service.jse;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ericsson.trainings.jee.service.api.HelloWorldRemote;

public class HelloWorldJseClient {

	public static void main(String[] args) {
		try {
			HelloWorldRemote remote = getHelloWorldRemote();

			final String output = remote.sayHello("Kapx");
			System.out.println("------------------------ Output from HelloWorld EJB [" + output + " ] ------------------------");

			final Future<String> future = remote.sayHelloAsync("Async Kapx");

			System.out.println("------------------------ Invoking Async HelloWorld EJB " + future.get() + " ] ------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static HelloWorldRemote getHelloWorldRemote() throws Exception {
		final Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
		jndiProps.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
		jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");

		jndiProps.put("jboss.naming.client.remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "true");
		jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SSL_STARTTLS", "true");

		defineSystemProperties();

		final Context context = new InitialContext(jndiProps);

		final HelloWorldRemote remote = (HelloWorldRemote) context.lookup(createRemoteJndiName());
		context.close();
		return remote;
	}

	private static String createRemoteJndiName() {
		final String appName = "jee7-wildfly-security-war";
		final String beanName = "HelloWorldBean";
		return appName + "/" + beanName + "!" + HelloWorldRemote.class.getName();
	}

	public static void defineSystemProperties() {
		final File clientKeyStore = new File("src/main/resources/client-keystore.jks");
		System.setProperty("javax.net.ssl.keyStore", clientKeyStore.getAbsolutePath());
		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

		final File clientTrustStore = new File("src/main/resources/client-truststore.jks");
		System.setProperty("javax.net.ssl.trustStore", clientTrustStore.getAbsolutePath());
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}
}
