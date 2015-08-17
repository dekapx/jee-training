package com.ericsson.trainings.jee.service.impl;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.service.api.HelloWorldLocal;
import com.ericsson.trainings.jee.service.api.HelloWorldRemote;

@Stateless
@Local(HelloWorldLocal.class)
@Remote(HelloWorldRemote.class)
@SecurityDomain("x509-security-domain")
public class HelloWorldBean implements HelloWorldLocal, HelloWorldRemote {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldBean.class);

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
		return "Hello !!! " + arg;
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
