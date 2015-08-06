package com.ericsson.trainings.gof.patterns.observer.observers;

import com.ericsson.trainings.gof.patterns.observer.event.Event;

public class OutlineView implements IView {
	private static final String VIEW_NAME = "OutlineView";

	@Override
	public void refresh(final Event event) {
		System.out.println("OutlineView new event [ " + event.getEventType() + " ]");
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof OutlineView) {
			if (((OutlineView) obj).toString().equals(VIEW_NAME)) {
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
