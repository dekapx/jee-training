package com.ericsson.trainings.jse.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jse.proxy.HelloWorldProxy;
import com.ericsson.trainings.jse.subject.HelloWorld;
import com.ericsson.trainings.jse.subject.HelloWorldImpl;

public class HelloWorldJseClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldJseClient.class);

	public static void main(String[] args) {
		LOGGER.info("Invoking call on proxy...");
		HelloWorld foo = (HelloWorld) HelloWorldProxy.newInstance(new HelloWorldImpl());
		String str = foo.sayHello("Kapx");
		LOGGER.info("Outout from proxy {}", str);
	}
}
