package com.ericsson.trainings.jee.cdi.sender;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.cdi.event.CdiEvent;

@Stateless
@LocalBean
public class EventSender {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventSender.class);

	@Inject
	private Event<CdiEvent> events;

	public void sendEvent(final CdiEvent event) {
		LOGGER.info("Sending event [{}] to event observers...", event);
		events.fire(event);
	}
}
