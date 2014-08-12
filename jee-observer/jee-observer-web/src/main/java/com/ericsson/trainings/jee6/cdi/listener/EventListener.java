package com.ericsson.trainings.jee6.cdi.listener;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.cdi.event.CdiEvent;

@Startup
@Singleton
public class EventListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

	public void onEvent(@Observes final CdiEvent event) {
		LOGGER.info("Received event [{}]", event);
	}
}
