package com.ericsson.trainings.jee6.timer.service;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class EventScheduler {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventScheduler.class);

	@Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
	public void runEvery5Seconds() {
		LOGGER.info("----------------- executing timer every 5 second -----------------");
	}

	@Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
	public void runEveryMinute() {
		LOGGER.info("----------------- executing timer every 1 minute -----------------");
	}
}
