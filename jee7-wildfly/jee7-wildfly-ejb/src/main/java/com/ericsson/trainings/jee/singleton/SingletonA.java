package com.ericsson.trainings.jee.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@DependsOn("SingletonB")
public class SingletonA {
	private static final Logger LOGGER = LoggerFactory.getLogger(SingletonA.class);

	@PostConstruct
	public void init() throws ServletException {
		LOGGER.info("SingletonA initialized...");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("SingletonA destroyed...");
	}
}
