package com.ericsson.trainings.gof.patterns.observer.observers;

import com.ericsson.trainings.gof.patterns.observer.event.Event;

public interface IView {
	void refresh(Event event);
}
