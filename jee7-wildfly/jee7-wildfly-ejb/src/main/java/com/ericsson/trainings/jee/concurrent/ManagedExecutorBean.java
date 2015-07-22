package com.ericsson.trainings.jee.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ManagedExecutorBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(ManagedExecutorBean.class);

	@Resource
	private ManagedExecutorService executorService;

	public void executeTasks() {
		final Callable<Void> task = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				for (int i = 0; i < 5; i++) {
					TimeUnit.SECONDS.sleep(5);
					LOGGER.info("Ping - {}", i);
				}
				return null;
			}
		};

		final Future<Void> future = executorService.submit(task);
	}
}
