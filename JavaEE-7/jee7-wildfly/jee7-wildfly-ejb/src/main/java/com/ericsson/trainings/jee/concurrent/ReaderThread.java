package com.ericsson.trainings.jee.concurrent;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.singleton.BeanStates;
import com.ericsson.trainings.jee.singleton.SingletonThreadSafeBean;

public class ReaderThread implements Callable<BeanStates> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReaderThread.class);

	@Inject
	private SingletonThreadSafeBean threadSafeBean;

	@Override
	public BeanStates call() throws Exception {
		BeanStates beanStates = threadSafeBean.getBeanStates();
		LOGGER.info("Bean state is: {}", beanStates);
		return beanStates;
	}

}
