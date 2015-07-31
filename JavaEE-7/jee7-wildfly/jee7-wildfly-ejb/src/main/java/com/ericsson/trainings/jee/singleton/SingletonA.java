package com.ericsson.trainings.jee.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@DependsOn("SingletonB")
public class SingletonA {
	private static final Logger LOGGER = LoggerFactory.getLogger(SingletonA.class);

	@PostConstruct
	public void init() throws Exception {
		LOGGER.info("SingletonA initialized...");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("SingletonA destroyed...");
	}
}
