package com.ericsson.trainings.jee.cdi.listener;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.cdi.event.CdiEvent;

@Startup
@Singleton
public class AnotherEventListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnotherEventListener.class);

	public void onEvent(@Observes final CdiEvent event) {
		LOGGER.info("Received event [{}]", event);
	}
}
