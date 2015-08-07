package com.ericsson.trainings.jee.cdi.listener;

import java.util.concurrent.TimeUnit;

import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.cdi.event.CdiEvent;

@Startup
@Singleton
public class EventListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

	@Asynchronous
	@Lock(LockType.READ)
	public void onEvent(@Observes final CdiEvent event) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("Received event [{}]", event);
	}
}
