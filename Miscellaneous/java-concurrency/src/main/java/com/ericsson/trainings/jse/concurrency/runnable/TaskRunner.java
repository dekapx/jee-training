package com.ericsson.trainings.jse.concurrency.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskRunner implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskRunner.class);

	@Override
	public void run() {
		LOGGER.info("executing {}...", Thread.currentThread().getName());
	}

}
