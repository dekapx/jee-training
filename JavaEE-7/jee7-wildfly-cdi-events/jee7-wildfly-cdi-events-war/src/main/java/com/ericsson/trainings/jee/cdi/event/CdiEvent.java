package com.ericsson.trainings.jee.cdi.event;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CdiEvent implements Serializable {
	private String event;

	public CdiEvent(final String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}

	@Override
	public String toString() {
		return event;
	}
}
