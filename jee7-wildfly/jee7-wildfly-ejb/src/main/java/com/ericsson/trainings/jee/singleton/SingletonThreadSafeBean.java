package com.ericsson.trainings.jee.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SingletonThreadSafeBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(SingletonThreadSafeBean.class);

	private BeanStates beanStates;

	@PostConstruct
	public void init() throws ServletException {
		LOGGER.info("SingletonBean initialized...");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("SingletonBean destroyed...");
	}

	@Lock(LockType.READ)
	public BeanStates getBeanStates() {
		return beanStates;
	}

	@Lock(LockType.WRITE)
	public void setBeanStates(BeanStates beanStates) {
		this.beanStates = beanStates;
	}
}
