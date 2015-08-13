package com.ericsson.trainings.jse.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jse.proxy.GenericProxyFactory;
import com.ericsson.trainings.jse.subject.HelloWorld;
import com.ericsson.trainings.jse.subject.HelloWorldImpl;

public class HelloWorldJseClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldJseClient.class);

	public static void main(String[] args) {
		LOGGER.info("Invoking call on proxy...");
		final HelloWorld proxyObject = GenericProxyFactory.INSTANCE.getObjectByClassType(HelloWorldImpl.class);
		final String output = proxyObject.sayHello("Kapx");
		LOGGER.info("Outout from proxy {}", output);
	}
}
