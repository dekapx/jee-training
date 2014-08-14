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
		LOGGER.info("----------------- executing timer every 5 seconds -----------------");
	}

	@Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
	public void runEvery2Minute() {
		LOGGER.info("----------------- executing timer every 2 minutes -----------------");
	}

	@Schedule(hour = "*/1", minute = "0", second = "0", persistent = false)
	public void runEveryHour() {
		LOGGER.info("----------------- executing timer every hour -----------------");
	}
}
