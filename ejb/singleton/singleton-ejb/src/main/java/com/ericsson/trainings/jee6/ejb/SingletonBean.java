package com.ericsson.trainings.jee6.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
@DependsOn("SingletonA")
public class SingletonBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(SingletonBean.class);

	@PostConstruct
	public void init() throws ServletException {
		LOGGER.info("SingletonBean initialized...");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("SingletonBean destroyed...");
	}

	public String sayHello(final String arg) {
		LOGGER.info("SingletonBean.sayHello() called with argument [{}]...", arg);
		return "Hello !!! " + arg;
	}
}
