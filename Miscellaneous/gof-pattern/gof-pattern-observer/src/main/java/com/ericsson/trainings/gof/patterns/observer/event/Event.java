package com.ericsson.trainings.gof.patterns.observer.event;

public class Event {
	private String eventType;

	public Event(final String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return eventType;
	}
}
