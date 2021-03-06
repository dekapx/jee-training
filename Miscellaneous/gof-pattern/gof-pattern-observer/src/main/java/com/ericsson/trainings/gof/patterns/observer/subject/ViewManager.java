package com.ericsson.trainings.gof.patterns.observer.subject;

import com.ericsson.trainings.gof.patterns.observer.event.Event;
import com.ericsson.trainings.gof.patterns.observer.observers.IView;

public interface ViewManager {
	void addView(IView iView);

	void removeView(IView iView);

	void setEvent(Event event);
}
