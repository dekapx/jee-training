package com.ericsson.trainings.jee.service.jse;

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
			System.out.println("#################### Output from HelloWorld EJB [" + output + " ] ####################");

			final Future<String> future = remote.sayHelloAsync("Async Kapx");

			System.out.println("################## Invoking Async HelloWorld EJB " + future.get() + " ] ####################");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static HelloWorldRemote getHelloWorldRemote() throws Exception {
		final Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProps);

		final HelloWorldRemote remote = (HelloWorldRemote) context.lookup(getJndiName());
		context.close();
		return remote;
	}

	private static String getJndiName() {
		final String appName = "jee7-wildfly-war";
		final String beanName = "HelloWorldBean";
		return appName + "/" + beanName + "!" + HelloWorldRemote.class.getName();
	}
}
