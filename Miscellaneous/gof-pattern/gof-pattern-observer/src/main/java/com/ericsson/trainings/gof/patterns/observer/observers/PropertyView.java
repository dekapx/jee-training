package com.ericsson.trainings.gof.patterns.observer.observers;

import com.ericsson.trainings.gof.patterns.observer.event.Event;

public class PropertyView implements IView {
	private static final String VIEW_NAME = "PropertyView";

	@Override
	public void refresh(final Event event) {
		System.out.println("PropertyView new event [ " + event.getEventType() + " ]");
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof PropertyView) {
			if (((PropertyView) obj).toString().equals(VIEW_NAME)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return VIEW_NAME.hashCode();
	}

	@Override
	public String toString() {
		return VIEW_NAME;
	}

}
