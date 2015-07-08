package com.ericsson.trainings.jee.service.impl;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.service.api.HelloWorldLocal;
import com.ericsson.trainings.jee.service.util.TextMessageFormatter;

@Stateless
@Local(HelloWorldLocal.class)
public class HelloWorldBean implements HelloWorldLocal {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldBean.class);

	@Inject
	private TextMessageFormatter formatter;

	@PostConstruct
	public void setup() {
		LOGGER.info("HelloWorldBean initialized...");
	}

	@PreDestroy
	public void teardown() {
		LOGGER.info("HelloWorldBean destroyed...");
	}

	@Override
	public String sayHello(final String arg) {
		if (arg == null || arg.isEmpty()) {
			throw new IllegalArgumentException("Argument must not be null of empty.");
		}

		LOGGER.info("sayHello() method invoked with parameter \"{}\"", arg);
		return formatter.format(arg);
	}

	@Override
	@Asynchronous
	public Future<String> sayHelloAsync(final String arg) {
		if (arg == null || arg.isEmpty()) {
			throw new IllegalArgumentException("Argument must not be null of empty.");
		}

		LOGGER.info("sayHelloAsync() method invoked with parameter \"{}\"", arg);
		return new AsyncResult<String>("Hello !!! " + arg);
	}
}
