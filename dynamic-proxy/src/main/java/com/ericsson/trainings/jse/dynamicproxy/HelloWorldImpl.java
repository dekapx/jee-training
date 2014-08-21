package com.ericsson.trainings.jse.dynamicproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldImpl implements HelloWorld {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldImpl.class);

	@Override
	public String sayHello(final String arg) {
		LOGGER.info("method [sayHello] invoked with parameter - [{}]", arg);

		return "Hello !!! " + arg;
	}

}
