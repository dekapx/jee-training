package com.ericsson.trainings.jee.concurrent;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.singleton.BeanStates;
import com.ericsson.trainings.jee.singleton.SingletonThreadSafeBean;

public class WriterThreadOne implements Callable<BeanStates> {
	private static final Logger LOGGER = LoggerFactory.getLogger(WriterThreadOne.class);

	@Inject
	private SingletonThreadSafeBean threadSafeBean;

	@Override
	public BeanStates call() throws Exception {
		BeanStates beanStates = BeanStates.DO_NOT_EXISTS;
		LOGGER.info("Setting bean state to {}", beanStates);
		threadSafeBean.setBeanStates(beanStates);
		return beanStates;
	}

}
