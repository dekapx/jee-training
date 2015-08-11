package com.ericsson.trainings.jse.subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jse.proxy.DynamicProxy;

public enum HelloWorldFactory {
	INSTANCE;

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldFactory.class);

	public HelloWorld getHelloWorldProxy() {
		LOGGER.info("Create HelloWorld proxy object...");
		return (HelloWorld) DynamicProxy.newInstance(new HelloWorldImpl());
	}
}
