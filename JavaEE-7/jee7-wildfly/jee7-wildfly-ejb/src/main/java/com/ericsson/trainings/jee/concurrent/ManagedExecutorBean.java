package com.ericsson.trainings.jee.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.singleton.BeanStates;

@Stateless
public class ManagedExecutorBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(ManagedExecutorBean.class);

	@Resource
	private ManagedExecutorService executorService;

	public void executeTasks() {

		final Collection<Callable<BeanStates>> tasks = new ArrayList<>();
		final Callable<BeanStates> writerOne = new WriterThreadOne();
		tasks.add(writerOne);

		final Callable<BeanStates> writerTwo = new WriterThreadTwo();
		tasks.add(writerTwo);

		final Callable<BeanStates> writerThree = new WriterThreadThree();
		tasks.add(writerThree);

		final Callable<BeanStates> reader = new ReaderThread();
		tasks.add(reader);

		try {
			executorService.invokeAll(tasks);
		} catch (InterruptedException e) {
			LOGGER.error("Exception while running reader/writer threads...", e);
		}
	}
}
