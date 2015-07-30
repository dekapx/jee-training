package com.ericsson.trainings.jee6.cdi.sender;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.cdi.event.CdiEvent;

@Stateless
public class EventSender {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventSender.class);

	@Inject
	private Event<CdiEvent> events;

	public void sendEvent(final CdiEvent event) {
		LOGGER.info("Sending event [{}] to observers...", event);
		events.fire(event);
	}
}
