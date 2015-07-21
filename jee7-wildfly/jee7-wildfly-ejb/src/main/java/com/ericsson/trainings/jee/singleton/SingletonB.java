package com.ericsson.trainings.jee.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SingletonB {
	private static final Logger LOGGER = LoggerFactory.getLogger(SingletonB.class);

	@PostConstruct
	public void init() throws ServletException {
		LOGGER.info("SingletonB initialized...");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("SingletonB destroyed...");
	}
}
