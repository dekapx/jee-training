package com.ericsson.trainings.jee.concurrent;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.singleton.BeanStates;
import com.ericsson.trainings.jee.singleton.SingletonThreadSafeBean;

public class WriterThreadThree implements Callable<BeanStates> {
	private static final Logger LOGGER = LoggerFactory.getLogger(WriterThreadThree.class);

	@Inject
	private SingletonThreadSafeBean threadSafeBean;

	@Override
	public BeanStates call() throws Exception {
		BeanStates beanStates = BeanStates.PASSIVE;
		LOGGER.info("Setting bean state to {}", beanStates);
		threadSafeBean.setBeanStates(beanStates);
		return beanStates;
	}

}
